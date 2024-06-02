package fast_fix.service;

import fast_fix.domain.dto.EmergencyContactDto;
import fast_fix.domain.mapping.EmergencyContactMapper;
import fast_fix.service.interfaces.EmergencyContactService;
import org.springframework.stereotype.Service;

@Service
public class EmergencyContactServiceImpl implements EmergencyContactService {

    private final EmergencyContactMapper emergencyContactMapper;

    public EmergencyContactServiceImpl(EmergencyContactMapper emergencyContactMapper) {
        this.emergencyContactMapper = emergencyContactMapper;
    }

    @Override
    public EmergencyContactDto getEmergencyContact() {
        EmergencyContactDto contact = new EmergencyContactDto();
        contact.setName("ADAC");
        contact.setPhoneNumber("+49 89 222222");
        return contact;
    }
}