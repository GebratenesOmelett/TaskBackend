package backend.task.taskbackend.task;

import backend.task.taskbackend.customer.dto.SimpleCustomerSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

interface SqlTaskRepository extends JpaRepository<TaskSnapshot, Integer> {
    Optional<List<TaskSnapshot>> findAllByCustomerOrderByCreationDateDescIdDesc(SimpleCustomerSnapshot customer);

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
    public void delete(int id) {
        sqlTaskRepository.deleteById(id);
    }

    @Override
    public Optional<List<TaskSnapshot>> findAllByCustomerOrderByCreationDate(SimpleCustomerSnapshot customer) {
        return sqlTaskRepository.findAllByCustomerOrderByCreationDateDescIdDesc(customer);
    }

    @Override
    public Optional<TaskSnapshot> findById(int id) {
        return sqlTaskRepository.findById(id);
    }
}
