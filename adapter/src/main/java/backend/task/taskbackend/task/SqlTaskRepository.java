package backend.task.taskbackend.task;

import backend.task.taskbackend.customer.dto.SimpleCustomerSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

interface SqlTaskRepository extends JpaRepository<TaskSnapshot, Integer> {
    Optional<List<TaskSnapshot>> findAllByCustomerOrderByCreationDate(SimpleCustomerSnapshot customer);
}
@Repository
class TaskRepositoryImpl implements TaskRepository, TaskQueryRepository{
    private final SqlTaskRepository sqlTaskRepository;
    TaskRepositoryImpl(SqlTaskRepository sqlTaskRepository) {
        this.sqlTaskRepository = sqlTaskRepository;
    }

    @Override
    public Task save(Task task) {
        return Task.restore(sqlTaskRepository.save(task.getSnapshot()));
    }

    @Override
    public Optional<List<TaskSnapshot>> findAllByCustomerOrderByCreationDate(SimpleCustomerSnapshot customer) {
        return sqlTaskRepository.findAllByCustomerOrderByCreationDate(customer);
    }
}
