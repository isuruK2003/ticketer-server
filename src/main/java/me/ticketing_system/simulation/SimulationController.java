package me.ticketing_system.simulation;

import jakarta.validation.ValidationException;
import me.ticketing_system.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulation")
@CrossOrigin
public class SimulationController {
    private final SimulationService simulationService;
    private final SimulationConfigurationRepository simulationConfigurationRepository;

    public SimulationController(SimulationService simulationService, SimulationConfigurationRepository simulationConfigurationRepository) {
        this.simulationService = simulationService;
        this.simulationConfigurationRepository = simulationConfigurationRepository;
        this.simulationService.subscribeToTicketPoolChanges(); // todo: make this subscription to be done from the client side
    }

    //// Response Mapping ////

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/configure")
    public void configureSimulation(@RequestBody SimulationConfiguration simulationConfiguration) {
        simulationService.configureSimulation(simulationConfiguration);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/save-configuration")
    public void saveConfiguration(@RequestBody SimulationConfiguration simulationConfiguration) {
        simulationConfigurationRepository.createConfig(simulationConfiguration);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("get-saved-configurations")
    public List<SimulationConfiguration> getSavedConfigurations() {
        return simulationConfigurationRepository.findAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/initialize-consumers")
    public void initializeConsumers() {
        simulationService.initializeConsumers();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/initialize-vendors")
    public void initializeVendors() {
        simulationService.initializeVendors();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/start-vendors")
    public void startVendors() {
        simulationService.startVendors();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("start-consumers")
    public void startConsumers() {
        simulationService.startConsumers();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("stop-vendors")
    public void stopVendors() {
        simulationService.stopVendors();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("stop-consumers")
    public void stopConsumers() {
        simulationService.stopConsumers();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("clear-vendors")
    public void clearVendors() {
        simulationService.clearVendors();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("clear-consumers")
    public void clearConsumers() {
        simulationService.clearConsumers();
    }

    //// Exception handlers ////

    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleValidationException(ValidationException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleRuntimeException(RuntimeException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}
