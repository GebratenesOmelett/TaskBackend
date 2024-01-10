package backend.task.taskbackend.task;

import backend.task.taskbackend.task.dto.TaskCreateDto;
import org.springframework.stereotype.Service;

@Service
class TaskFactory {
    Task from(TaskCreateDto taskCreateDto) {
        return Task.restore(TaskSnapshot.builder()
                .title(taskCreateDto.getTitle())
                .importance(taskCreateDto.getImportance())
                .description(taskCreateDto.getDescription())
                .deadLine(taskCreateDto.getDeadLine())
                .build());

    }
}
