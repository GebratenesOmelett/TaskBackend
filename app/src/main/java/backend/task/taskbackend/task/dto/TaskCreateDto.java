package backend.task.taskbackend.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;
@Getter
public class TaskCreateDto {
    private final String title;
    private final String importance;
    private final String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    private final Date deadLine;
    private final String email;
    TaskCreateDto(String title, String importance, String description, Date deadLine, String email) {
        this.title = title;
        this.importance = importance;
        this.description = description;
        this.deadLine = deadLine;
        this.email = email;
    }
}
