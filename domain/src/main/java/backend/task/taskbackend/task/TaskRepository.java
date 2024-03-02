package backend.task.taskbackend.task;


interface TaskRepository {
    Task save(Task task);
    void delete(int id);
}
