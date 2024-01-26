package backend.task.taskbackend.customer;

import backend.task.taskbackend.customer.dto.SimpleCustomerSnapshot;
import org.springframework.stereotype.Service;

@Service
class CustomerMapper {
    SimpleCustomerSnapshot toSimpleCustomerSnapshot(CustomerSnapshot customerSnapshot){
        return new SimpleCustomerSnapshot(customerSnapshot.getId(),
                customerSnapshot.getEmail());
    }
}
