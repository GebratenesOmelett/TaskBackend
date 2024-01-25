package backend.task.taskbackend.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

interface SqlTaskRepository extends JpaRepository<TaskSnapshot, Integer> {
    Optional<List<TaskSnapshot>> findAllBy
}
@Repository
class TaskRepositoryImpl implements TaskRepository{
    private final SqlTaskRepository sqlTaskRepository;
    TaskRepositoryImpl(SqlTaskRepository sqlTaskRepository) {
        this.sqlTaskRepository = sqlTaskRepository;
    }

    @Override
    public Task save(Task task) {
        return Task.restore(sqlTaskRepository.save(task.getSnapshot()));
    }
}
