package backend.task.taskbackend.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
public class TaskCreateDto {
    private final String title;
    private final String importance;
    private final String description;
    private final Date deadLine;
    TaskCreateDto(String title, String importance, String description, Date deadLine) {
        this.title = title;
        this.importance = importance;
        this.description = description;
        this.deadLine = deadLine;
    }
}
