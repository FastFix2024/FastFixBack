package fast_fix.service;

import fast_fix.service.interfaces.EmergencyContactService;
import org.springframework.stereotype.Service;

@Service
public class EmergencyContactServiceImpl implements EmergencyContactService {

    private static final String EMERGENCY_CONTACT_NUMBER = "+49 89 222222";

    @Override
    public String getEmergencyContactNumber() {
        return EMERGENCY_CONTACT_NUMBER;
    }
}