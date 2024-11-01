package me.ticketing_system.config;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/configs")
public class ConfigController {

    private final ConfigRepository configRepository;

    public ConfigController(ConfigRepository configurationRepository) {
        this.configRepository = configurationRepository;
    }

    @GetMapping("/")
    public List<Configuration> getAll() {
        return configRepository.findAll();
    }

    @GetMapping("/{configId}")
    public Configuration getById(@PathVariable String configId) {
        Optional<Configuration> config = configRepository.findById(configId);

        if (config.isEmpty()) {
            throw new ConfigNotFoundException();
        }

        return config.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void create(@Valid @RequestBody Configuration newConfig) {
        configRepository.createConfig(newConfig);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping()
    public void update(@Valid @RequestBody Configuration newConfig) {
        configRepository.updateConfig(newConfig);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{configId}")
    public void delete(@PathVariable String configId) {
        configRepository.deleteConfig(configId);
    }
}
