package spring.boot.swiggyBE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.swiggyBE.database_model.Manufacturer;
import spring.boot.swiggyBE.service.ManufacturerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    // Create operation
    @PostMapping
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer) {
        Manufacturer createdManufacturer = manufacturerService.createManufacturer(manufacturer);
        return new ResponseEntity<>(createdManufacturer, HttpStatus.CREATED);
    }

    // Read operations
    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

    @GetMapping("/{manufacturerName}")
    public ResponseEntity<Manufacturer> getManufacturerByName(@PathVariable String manufacturerName) {
        Optional<Manufacturer> manufacturerOptional = manufacturerService.getManufacturerByName(manufacturerName);
        return manufacturerOptional.map(manufacturer -> new ResponseEntity<>(manufacturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete operation
    @DeleteMapping("/{manufacturerName}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable String manufacturerName) {
        manufacturerService.deleteManufacturer(manufacturerName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}