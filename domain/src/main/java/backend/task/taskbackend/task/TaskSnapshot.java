package backend.task.taskbackend.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "Task")
@Getter
@Builder
class TaskSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String importance;
    private String description;
    @CreationTimestamp
    private Date creationDate;
    private Date deadLine;

    protected TaskSnapshot() {
    }

    TaskSnapshot(int id, String title, String importance, String description, Date creationDate, Date deadLine) {
        this.id = id;
        this.title = title;
        this.importance = importance;
        this.description = description;
        this.creationDate = creationDate;
        this.deadLine = deadLine;
    }
}
