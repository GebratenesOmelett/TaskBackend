package backend.task.taskbackend.task;

import backend.task.taskbackend.customer.CustomerFacade;
import backend.task.taskbackend.customer.dto.SimpleCustomerSnapshot;
import backend.task.taskbackend.task.dto.TaskCreateDto;
import backend.task.taskbackend.task.dto.TaskDto;
import backend.task.taskbackend.task.exception.TaskNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskFacade {
    private final TaskRepository taskRepository;
    private final TaskQueryRepository taskQueryRepository;
    private final TaskFactory taskFactory;
    private final TaskMapper taskMapper;
    private final CustomerFacade customerFacade;

    TaskFacade(TaskRepository taskRepository, TaskQueryRepository taskQueryRepository, TaskFactory taskFactory, TaskMapper taskMapper, CustomerFacade customerFacade) {
        this.taskRepository = taskRepository;
        this.taskQueryRepository = taskQueryRepository;
        this.taskFactory = taskFactory;
        this.taskMapper = taskMapper;
        this.customerFacade = customerFacade;
    }

    public TaskDto save(TaskCreateDto taskCreateDto) {
        return taskMapper.toDto(taskRepository.save(taskFactory.from(taskCreateDto)).getSnapshot());
    }

    public List<TaskDto> getAllTasks(String email) {
        SimpleCustomerSnapshot customer = customerFacade.getSimpleCustomerSnapshotByEmail(email);
        return taskQueryRepository.findAllByCustomerOrderByCreationDate(customer)
                .orElseThrow(() -> new UsernameNotFoundException(""))
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.reverse(list);
                            return list;
                        }));
    }
    public TaskSnapshot getTaskSnapshotById(int id){
        return taskQueryRepository.findById(id).
                orElseThrow(() -> new TaskNotFoundException(id));
    }

    public TaskDto delete(int id) {
        TaskDto taskDto = taskMapper.toDto(getTaskSnapshotById(id));
        taskRepository.delete(id);
        return taskDto;

    }
}
