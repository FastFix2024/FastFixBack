package fast_fix.controller;

import fast_fix.domain.dto.InsuranceCompanyDto;
import fast_fix.exception_handling.exceptions.ResourceNotFoundException;
import fast_fix.service.interfaces.InsuranceCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance-companies")
public class InsuranceCompanyController {

    private InsuranceCompanyService service;

    public InsuranceCompanyController(InsuranceCompanyService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<InsuranceCompanyDto> getAllCompanies() {
        return service.getAllCompanies();
    }

    @GetMapping
    public ResponseEntity<InsuranceCompanyDto> getCompanyById(@RequestParam Long id) {
        InsuranceCompanyDto company = service.getCompanyById(id);
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<InsuranceCompanyDto> saveCompany(@RequestBody InsuranceCompanyDto company) {
        InsuranceCompanyDto savedCompany = service.saveCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @PutMapping("/update")
    public ResponseEntity<InsuranceCompanyDto> updateCompany(@RequestParam Long id, @RequestBody InsuranceCompanyDto company) {
        company.setId(id);
        try {
            InsuranceCompanyDto updatedCompany = service.updateCompany(company);
            return ResponseEntity.ok(updatedCompany);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCompany(@RequestParam Long id) {
        try {
            service.deleteCompanyById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

