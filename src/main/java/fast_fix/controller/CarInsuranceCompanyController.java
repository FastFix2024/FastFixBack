package fast_fix.controller;

import fast_fix.domain.dto.CarInsuranceCompanyDto;
import fast_fix.service.interfaces.CarInsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-insurance-companies")
public class CarInsuranceCompanyController {

    @Autowired
    private CarInsuranceCompanyService carInsuranceCompanyService;

    @GetMapping
    public ResponseEntity<List<CarInsuranceCompanyDto>> getAllCarInsuranceCompanies() {
        List<CarInsuranceCompanyDto> companies = carInsuranceCompanyService.getAllCarInsuranceCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarInsuranceCompanyDto> getCarInsuranceCompanyById(@PathVariable Long id) {
        CarInsuranceCompanyDto company = carInsuranceCompanyService.getCarInsuranceCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarInsuranceCompanyDto> createCarInsuranceCompany(@RequestBody CarInsuranceCompanyDto carInsuranceCompanyDto) {
        CarInsuranceCompanyDto createdCompany = carInsuranceCompanyService.createCarInsuranceCompany(carInsuranceCompanyDto);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarInsuranceCompanyDto> updateCarInsuranceCompany(@PathVariable Long id, @RequestBody CarInsuranceCompanyDto carInsuranceCompanyDto) {
        CarInsuranceCompanyDto updatedCompany = carInsuranceCompanyService.updateCarInsuranceCompany(id, carInsuranceCompanyDto);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarInsuranceCompany(@PathVariable Long id) {
        carInsuranceCompanyService.deleteCarInsuranceCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}