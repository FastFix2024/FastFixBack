package fast_fix.controller;

import fast_fix.exceptions.ResourceNotFoundException;
import fast_fix.service.interfaces.EmergencyContactService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emergency-contact")
public class EmergencyContactController {

    @Autowired
    private EmergencyContactService emergencyContactService;

    @Operation(summary = "Получить контакт экстренной службы")
    @GetMapping
    public ResponseEntity<String> getEmergencyContact() {
        try {
            String contact = emergencyContactService.getEmergencyContactNumber();
            return ResponseEntity.ok(contact);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}