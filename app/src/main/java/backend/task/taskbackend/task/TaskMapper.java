package backend.task.taskbackend.task;

import backend.task.taskbackend.task.dto.TaskDto;
import org.springframework.stereotype.Service;

@Service
class TaskMapper {
    TaskDto toDto(TaskSnapshot taskSnapshot){
        return new TaskDto(taskSnapshot.getTitle(),
                taskSnapshot.getImportance(),
                taskSnapshot.getDescription(),
                taskSnapshot.getCreationDate(),
                taskSnapshot.getDeadLine());
    }
}
