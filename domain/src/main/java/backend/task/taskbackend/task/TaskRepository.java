package backend.task.taskbackend.task;


import java.util.Optional;

interface TaskRepository {
    Task save(Task task);
    void delete(int id);
}
