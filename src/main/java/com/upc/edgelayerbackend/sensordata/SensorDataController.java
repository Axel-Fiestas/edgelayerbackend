package com.upc.edgelayerbackend.sensordata;

import com.upc.edgelayerbackend.task.Task;
import com.upc.edgelayerbackend.task.TaskRepository;
import com.upc.edgelayerbackend.task.TaskService;
import com.upc.edgelayerbackend.websocket.WebSocketController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/")
public class SensorDataController {

    private SensorDataService sensorDataService;
    private SensorDataRepository sensorDataRepository;
    private final WebSocketController webSocketController;

    SensorDataController(
            SensorDataService sensorDataService,
            SensorDataRepository sensorDataRepository,
            WebSocketController webSocketController){
        this.sensorDataRepository=sensorDataRepository;
        this.sensorDataService=sensorDataService;
        this.webSocketController=webSocketController;
    }

    @GetMapping("sensor-data")
    public ResponseEntity<List<SensorData>> getAllSensorData() {
        List<SensorData> sensorDatas = (List<SensorData>) sensorDataRepository.findAll();
        return ResponseEntity.ok(sensorDatas);
    }

    @PostMapping("sensor-data")
    public ResponseEntity<SensorData> createTask(@RequestBody SensorData sensorData) {
        SensorData savedSensorData = sensorDataRepository.save(sensorData);
        webSocketController.sendSensorData(savedSensorData);
        return ResponseEntity.ok(savedSensorData);
    }

}
