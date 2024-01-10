package backend.task.taskbackend.task;

import backend.task.taskbackend.task.dto.TaskCreateDto;
import backend.task.taskbackend.task.dto.TaskDto;
import org.springframework.stereotype.Service;

@Service
public class TaskFacade {
    private final TaskRepository taskRepository;
    private final TaskFactory taskFactory;
    private final TaskMapper taskMapper;

    TaskFacade(TaskRepository taskRepository, TaskFactory taskFactory, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskFactory = taskFactory;
        this.taskMapper = taskMapper;
    }

    public TaskDto save(TaskCreateDto taskCreateDto){
        return taskMapper.toDto(taskRepository.save(taskFactory.from(taskCreateDto)).getSnapshot());
    }
}
