package me.ticketing_system.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("validations")
public class ValidationConstraintsController {
    ValidationConstraintService validationConstraintService;

    public ValidationConstraintsController(ValidationConstraintService validationConstraintService) {
        this.validationConstraintService = validationConstraintService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/all")
    public Map<String, ValidationConstraint> getValidationConstraints() {
        return this.validationConstraintService.loadConstraints();
    }
}
