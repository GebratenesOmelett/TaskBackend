package backend.task.taskbackend.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Getter
@Setter
public class TaskDto {
    private int id;
    private String title;
    private String importance;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date creationDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deadLine;

    public TaskDto(int id,String title, String importance, String description, Date creationDate, Date deadLine) {
        this.id = id;
        this.title = title;
        this.importance = importance;
        this.description = description;
        this.creationDate = creationDate;
        this.deadLine = deadLine;
    }

}
