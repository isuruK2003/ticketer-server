package me.ticketing_system.vendor;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/vendors")
public class VendorController {
    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GetMapping("/")
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @GetMapping("/vendorId")
    public Vendor getById(@PathVariable String vendorId) {
        Optional<Vendor> vendor = vendorRepository.findByID(vendorId);

        if (vendor.isEmpty()) {
            throw new VendorNotFoundException();
        }

        return vendor.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void create(@Valid @RequestBody Vendor newVendor) {
        vendorRepository.createVendor(newVendor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping()
    public void update(@Valid @RequestBody Vendor updatedVendor) {
        vendorRepository.updateVendor(updatedVendor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{vendorId}")
    public void delete(@PathVariable String vendorId) {
        vendorRepository.deleteVendor(vendorId);
    }
}
