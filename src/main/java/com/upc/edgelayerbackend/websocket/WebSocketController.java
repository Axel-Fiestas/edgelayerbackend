package com.upc.edgelayerbackend.websocket;

import com.upc.edgelayerbackend.sensordata.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;


@Component
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RestTemplate restTemplate;

    //private static final String CLOUD_BACKEND_URL = "http://localhost:8081/api/v1/sensor-data";
    private static final String CLOUD_BACKEND_URL = "https://sensordatacloudbackend.azurewebsites.net/api/v1/sensor-data";
    
    public void sendSensorData(SensorData sensorData) {
        // Enviar datos a los clientes conectados mediante WebSockets
        messagingTemplate.convertAndSend("/topic/sensor-data", sensorData);

        // Enviar datos al backend cloud
        restTemplate.postForObject(CLOUD_BACKEND_URL, sensorData, SensorData.class);
    }
}