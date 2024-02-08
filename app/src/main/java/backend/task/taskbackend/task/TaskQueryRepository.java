package backend.task.taskbackend.task;

import backend.task.taskbackend.customer.dto.SimpleCustomerSnapshot;

import java.util.List;
import java.util.Optional;

interface TaskQueryRepository {
    Optional<List<TaskSnapshot>> findAllByCustomerOrderByCreationDate(SimpleCustomerSnapshot customer);
    Optional<TaskSnapshot> findById(int id);
}
