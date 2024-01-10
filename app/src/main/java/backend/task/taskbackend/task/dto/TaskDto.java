package backend.task.taskbackend.task.dto;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Getter
public class TaskDto {
    private final String title;
    private final String importance;
    private final String description;
    private final Date creationDate;
    private final Date deadLine;

    public TaskDto(String title, String importance, String description, Date creationDate, Date deadLine) {
        this.title = title;
        this.importance = importance;
        this.description = description;
        this.creationDate = creationDate;
        this.deadLine = deadLine;
    }
}
