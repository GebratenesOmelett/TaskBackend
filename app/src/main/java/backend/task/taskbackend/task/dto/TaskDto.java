package backend.task.taskbackend.task.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Getter
@Setter
public class TaskDto {
    private String title;
    private String importance;
    private String description;
    private Date creationDate;
    private Date deadLine;

    public TaskDto(String title, String importance, String description, Date creationDate, Date deadLine) {
        this.title = title;
        this.importance = importance;
        this.description = description;
        this.creationDate = creationDate;
        this.deadLine = deadLine;
    }

}
