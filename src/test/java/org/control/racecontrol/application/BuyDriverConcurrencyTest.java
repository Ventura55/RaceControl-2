package org.control.racecontrol.application;

import org.control.racecontrol.domain.model.Driver;
import org.control.racecontrol.domain.model.FantasyRoster;
import org.control.racecontrol.domain.model.UserWallet;
import org.control.racecontrol.domain.port.input.BuyDriverUseCase;
import org.control.racecontrol.domain.port.output.DriverRepository;
import org.control.racecontrol.domain.port.output.FantasyRosterRepository;
import org.control.racecontrol.domain.port.output.UserWalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BuyDriverConcurrencyTest {

    @Autowired
    private BuyDriverUseCase buyDriverUseCase;

    @Autowired
    private UserWalletRepository walletRepository;

    @Autowired
    private FantasyRosterRepository rosterRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    // Usaremos esto para extraer el ID real directamente de la BD
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String testUser = "concurrentUser";
    private int targetDriverId; // Ya no es final ni es 999

    @BeforeEach
    public void setup() {
        System.out.println("🛠️ Preparando datos para el test...");

        transactionTemplate.execute(status -> {
            rosterRepository.findByUsername(testUser).ifPresent(roster -> {
                roster.getDriverIds().clear();
                rosterRepository.save(roster);
            });

            Driver testDriver = new Driver();
            testDriver.setName("Piloto Anti-Fraude");
            testDriver.setIdTeam(1L);
            testDriver.setMarketValue(new BigDecimal("10000000"));
            driverRepository.save(testDriver);

            // 🌟 MAGIA: Leemos el ID real del último piloto insertado en MySQL
            Integer realId = jdbcTemplate.queryForObject("SELECT MAX(dri_id) FROM dri_driver", Integer.class);
            this.targetDriverId = (realId != null) ? realId : 1;
            System.out.println("🆔 El ID real del piloto generado es: " + targetDriverId);

            UserWallet wallet = walletRepository.findByUsernameLocked(testUser)
                    .orElse(new UserWallet());
            wallet.setUsername(testUser);
            wallet.setBalance(new BigDecimal("15000000"));
            walletRepository.save(wallet);

            return null;
        });
    }

    @Test
    public void testDoubleSpendingPrevention() throws InterruptedException {
        System.out.println("🚀 Lanzando ataque de 10 hilos concurrentes...");

        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        AtomicInteger successfulPurchases = new AtomicInteger(0);
        AtomicInteger failedPurchases = new AtomicInteger(0);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                try {
                    buyDriverUseCase.buyDriver(testUser, targetDriverId);
                    successfulPurchases.incrementAndGet();
                } catch (Exception e) {
                    // Ahora veremos exactamente POR QUÉ falla si algo va mal
                    System.err.println("❌ Fallo en hilo: " + e.getMessage());
                    failedPurchases.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        transactionTemplate.execute(status -> {
            UserWallet finalWallet = walletRepository.findByUsernameLocked(testUser).orElseThrow();

            // Si el Roster no existe, creamos uno vacío para que no explote el test y podamos ver los Assertions fallar
            FantasyRoster finalRoster = rosterRepository.findByUsername(testUser).orElse(new FantasyRoster());

            System.out.println("✅ Compras exitosas (Debería ser 1): " + successfulPurchases.get());
            System.out.println("❌ Compras fallidas (Debería ser 9): " + failedPurchases.get());
            System.out.println("💰 Saldo final (Debería ser 5M): " + finalWallet.getBalance());

            assertEquals(1, successfulPurchases.get(), "Solo debió permitirse 1 compra exitosa");
            assertEquals(9, failedPurchases.get(), "9 compras debieron fallar por concurrencia");
            assertTrue(finalWallet.getBalance().compareTo(BigDecimal.ZERO) >= 0, "El saldo no puede ser negativo");
            assertEquals(1, finalRoster.getDriverIds().size(), "El equipo debe tener 1 piloto");

            return null;
        });
    }
}