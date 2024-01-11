package backend.task.taskbackend.customer;

import java.util.Optional;

public interface CustomerQueryRepository {
    Optional<CustomerSnapshot> findCustomerSnapshotByEmail(String email);
}
