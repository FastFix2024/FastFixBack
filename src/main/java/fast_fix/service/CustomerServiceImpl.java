package fast_fix.service;

import fast_fix.domain.dto.CustomerDto;
import fast_fix.repository.CustomerRepository;
import fast_fix.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    @Override
    public CustomerDto save(CustomerDto dto) {
        return null;
    }

    @Override
    public List<CustomerDto> getAllActiveCustomers() {
        return List.of();
    }

    @Override
    public CustomerDto getActiveCustomerById(Long id) {
        return null;
    }

    @Override
    public CustomerDto update(CustomerDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void restoreById(Long id) {

    }
}