package spring.boot.swiggyBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.swiggyBE.database_model.Manufacturer;
import spring.boot.swiggyBE.repository.ManufacturerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }
    public Optional<Manufacturer> getManufacturerByName(String manufacturerName) {
        return manufacturerRepository.findById(manufacturerName);
    }

    public Manufacturer updateManufacturer(String manufacturerName, Manufacturer updatedManufacturer) {
        Optional<Manufacturer> existingManufacturerOptional = manufacturerRepository.findById(manufacturerName);
        if (existingManufacturerOptional.isPresent()) {
            Manufacturer existingManufacturer = existingManufacturerOptional.get();
            existingManufacturer.setManufacturerNo(updatedManufacturer.getManufacturerNo());
            existingManufacturer.setAddress(updatedManufacturer.getAddress());
            existingManufacturer.setContactNumber(updatedManufacturer.getContactNumber());
            existingManufacturer.setDescription(updatedManufacturer.getDescription());
            existingManufacturer.setCity(updatedManufacturer.getCity());
            existingManufacturer.setState(updatedManufacturer.getState());
            existingManufacturer.setCountry(updatedManufacturer.getCountry());
            existingManufacturer.setUserId(updatedManufacturer.getUserId());
            existingManufacturer.setImage(updatedManufacturer.getImage());

            return manufacturerRepository.save(existingManufacturer);
        } else {
            return null;
        }
    }

    public void deleteManufacturer(String manufacturerName) {
        manufacturerRepository.deleteById(manufacturerName);
    }

}
