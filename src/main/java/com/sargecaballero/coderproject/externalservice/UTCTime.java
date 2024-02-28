package com.sargecaballero.coderproject.externalservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UTCTime {
    private final RestTemplate restTemplate;
    private final String worldTimeApiUrl;
    private static final Logger logger = LoggerFactory.getLogger(UTCTime.class);

    public UTCTime(RestTemplate restTemplate, String worldTimeApiUrl) {
        this.restTemplate = restTemplate;
        this.worldTimeApiUrl = worldTimeApiUrl;
    }

    public String getUtcTime() {
        try {
            ResponseEntity<WorldTimeApiResponse> responseEntity =
                    restTemplate.getForEntity(worldTimeApiUrl, WorldTimeApiResponse.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                WorldTimeApiResponse worldTimeApiResponse = responseEntity.getBody();
                return worldTimeApiResponse.datetime;
            } else {
                return "";
            }
        } catch (Exception e) {
            logger.error("Error en el servicio {}. {}", worldTimeApiUrl, e.getMessage());
            return "";
        }
    }

    public record WorldTimeApiResponse(
            String abbreviation,
            String clientIp,
            String datetime,
            int dayOfWeek,
            int dayOfYear,
            boolean dst,
            String dstFrom,
            int dstOffset,
            String dstUntil,
            int rawOffset,
            String timezone,
            long unixtime,
            String utcDatetime,
            String utcOffset,
            int weekNumber
    ) {}
}
