RaceControl API - Kafka Edition

Sistema de control de carreras con arquitectura de microservicios, utilizando Spring Boot, MySQL y Apache Kafka para la gestión de sanciones en tiempo real.

Levantando la Infraestructura
Para ejecutar el proyecto, es necesario tener levantados los contenedores de Docker que contienen la base de datos y el broker de mensajería.
1. Docker Compose (Base de Datos y Kafka)

Asegúrate de estar en la raíz del proyecto donde se encuentra el archivo docker-compose.yml .

# Levantar todos los servicios en segundo plano
docker-compose up -d
# Verificar que los contenedores están corriendo
docker ps

Servicios incluidos:
MySQL: Puerto 3307
Kafka: Puerto 9092

2. Gestión de Kafka desde la terminal
Si necesitas verificar los mensajes que están llegando al topic de la televisión (TV Feed):

# Ver mensajes en tiempo real desde el contenedor
docker exec -it kafka /bin/sh
kafka-console-consumer --bootstrap-server localhost:9092 --topic fia.race.events --from-beginning

Comandos de Maven
Utiliza Maven para compilar, limpiar y ejecutar la aplicación.
Compilación y ejecución

# Limpiar y compilar el proyecto
mvn clean install
# Ejecutar la aplicación
mvn spring-boot:run

Ejecución de Tests

# Ejecutar todos los tests unitarios e integración
mvn test
# Ejecutar un test específico
mvn test -Dtest=KafkaRaceEventPublisherTest

Configuración del Entorno
Asegúrate de que tu src/main/resources/application.properties coincida con los
puertos de Docker:

spring.datasource.url=jdbc:mysql://localhost:3307/race_control
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=tv-station-group

Verificación del Sistema

Acción Resultado Esperado
POST /penalties Se guarda en DB y se envía a Kafka
Consola IntelliJ Aparece el log [LIVE TV FEED]

Docker logs Confirmación de conexión de Kafka

Pro-tip: Si la consola no muestra mensajes de Kafka al arrancar, comprueba que el contenedor de Docker no esté pausado.
