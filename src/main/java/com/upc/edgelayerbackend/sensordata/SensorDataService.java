package com.upc.edgelayerbackend.sensordata;

import com.upc.edgelayerbackend.task.Task;
import com.upc.edgelayerbackend.task.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    private SensorData addSensorDataInformation(String pulse){
        SensorData sensorData= SensorData.builder()
                .pulse(pulse)
                .build();

        return sensorDataRepository.save(sensorData);
    }
}
