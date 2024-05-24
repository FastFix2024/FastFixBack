package fast_fix.service.interfaces;

import fast_fix.domain.dto.CustomerDto;

import java.util.List;


public interface CustomerService {

    CustomerDto save(CustomerDto dto);
    List<CustomerDto> getAllActiveCustomers();
    CustomerDto getActiveCustomerById(Long id);
    CustomerDto update(CustomerDto dto);
    void deleteById(Long id);
    void deleteByName(String name);
    void restoreById(Long id);
}