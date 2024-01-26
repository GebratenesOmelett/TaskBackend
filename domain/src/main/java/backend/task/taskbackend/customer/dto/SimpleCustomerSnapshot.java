package backend.task.taskbackend.customer.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "customer")
@Getter
@Builder
public class SimpleCustomerSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    protected SimpleCustomerSnapshot() {
    }
    public SimpleCustomerSnapshot(int id, String email) {
        this.id = id;
        this.email = email;
    }
}
