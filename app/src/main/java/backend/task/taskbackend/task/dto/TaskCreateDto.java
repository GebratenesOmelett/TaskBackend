package backend.task.taskbackend.task.dto;

import backend.task.taskbackend.task.validation.DateDeadlineValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.Date;
@Getter
public class TaskCreateDto {
    @NotBlank(message = "title must not be empty")
    private final String title;
    @NotBlank(message = "importance must not be empty")
    private final String importance;
    private final String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateDeadlineValidation
    private final Date deadLine;
    private final String email;
    public TaskCreateDto(String title, String importance, String description, Date deadLine, String email) {
        this.title = title;
        this.importance = importance;
        this.description = description;
        this.deadLine = deadLine;
        this.email = email;
    }
}
