package backend.task.taskbackend.task;

import backend.task.taskbackend.customer.CustomerFacade;
import backend.task.taskbackend.customer.dto.SimpleCustomerSnapshot;
import backend.task.taskbackend.task.dto.TaskCreateDto;
import org.springframework.stereotype.Service;

@Service
class TaskFactory {
    private final CustomerFacade customerFacade;

    TaskFactory(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    Task from(TaskCreateDto taskCreateDto) {
        return Task.restore(TaskSnapshot.builder()
                .title(taskCreateDto.getTitle())
                .importance(taskCreateDto.getImportance())
                .description(taskCreateDto.getDescription())
                .deadLine(taskCreateDto.getDeadLine())
                .customer(attachCustomer(taskCreateDto.getEmail()))
                .build());

    }
    SimpleCustomerSnapshot attachCustomer(String email){
        return customerFacade.getSimpleCustomerSnapshotByEmail(email);
    }
}
