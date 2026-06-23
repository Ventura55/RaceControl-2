package org.control.racecontrol.infrastructure.output.adapter;

import org.control.racecontrol.infrastructure.input.rest.dto.request.GeminiRequest;
import org.control.racecontrol.infrastructure.input.rest.dto.response.GeminiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.control.racecontrol.domain.model.RaceDataSnapshot;
import org.control.racecontrol.domain.port.output.RaceReportGenerator;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.util.List;

@Component
public class GeminiRaceReportAdapter implements RaceReportGenerator {
    private final RestClient restClient;
    private final String apiKey;
    private final String apiUrl;

    public GeminiRaceReportAdapter(@Value("${gemini.api.key}") String apiKey, @Value("${gemini.api.url}") String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;

        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory();
        requestFactory.setReadTimeout(Duration.ofSeconds(15));

        this.restClient = RestClient.builder()
                .requestFactory(requestFactory)
                .build();
    }

    @Override
    public String generateRaceSummary(RaceDataSnapshot data) {
        String prompt = buildPrompt(data);

        List<GeminiRequest.SafetySetting> safetySettings = List.of(
                new GeminiRequest.SafetySetting("HARM_CATEGORY_HATE_SPEECH", "BLOCK_NONE"),
                new GeminiRequest.SafetySetting("HARM_CATEGORY_HARASSMENT", "BLOCK_NONE"),
                new GeminiRequest.SafetySetting("HARM_CATEGORY_DANGEROUS_CONTENT", "BLOCK_NONE"),
                new GeminiRequest.SafetySetting("HARM_CATEGORY_SEXUALLY_EXPLICIT", "BLOCK_NONE")
        );

        GeminiRequest requestBody = new GeminiRequest(
                List.of(new GeminiRequest.Content(List.of(new GeminiRequest.Part(prompt)))), safetySettings
        );

        java.net.URI uri = java.net.URI.create(apiUrl + "?key=" + apiKey);

        GeminiResponse response = restClient.post()
                .uri(uri)
                // 🚨 ¡ESTA LÍNEA ES LA QUE SALVARÁ EL PROYECTO! 🚨
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(GeminiResponse.class);

        // Si la razón de finalización es "SAFETY", lanzamos la excepción
        if (response != null && response.getCandidates() != null && !response.getCandidates().isEmpty()) {
            String finishReason = response.getCandidates().get(0).getFinishReason();

            if ("SAFETY".equals(finishReason)) {
                System.err.println("🚨 [ALERTA CRÍTICA] Generación bloqueada por los filtros de seguridad de Gemini.");
                // Lo ideal sería que esto fuera tu SafetyPolicyViolationException
                throw new RuntimeException("Contenido bloqueado por violación de políticas de seguridad.");
            }
        }

        return extractTextFromResponse(response);
    }

    private String buildPrompt(RaceDataSnapshot data) {
        return String.format(
                "Actúa como un periodista experto de la FIA. Basándote en el %s, " +
                        "donde el podio fue %s y se aplicaron estas sanciones: %s. " +
                        "Redacta un informe oficial de impacto de máximo 2 párrafos.",
                data.getRaceName(),
                String.join(", ", data.getPodiumPilots()),
                data.getPenalties().isEmpty() ? "Ninguna" : String.join("; ", data.getPenalties())
        );
    }

    private String extractTextFromResponse(GeminiResponse response) {
        if (response != null && response.getCandidates() != null && !response.getCandidates().isEmpty()) {
            return response.getCandidates().get(0)
                    .getContent()
                    .getParts().get(0)
                    .getText();
        }
        return "El informe no pudo ser generado en este momento.";
    }
}