package fast_fix.controller;

import fast_fix.domain.dto.EmergencyContactDto;
import fast_fix.service.interfaces.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emergency-contact")
public class EmergencyContactController {

    @Autowired
    private EmergencyContactService emergencyContactService;

    @GetMapping
    public ResponseEntity<EmergencyContactDto> getEmergencyContact() {
        EmergencyContactDto contact = emergencyContactService.getEmergencyContact();
        return ResponseEntity.ok(contact);
    }
}