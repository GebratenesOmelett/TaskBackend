package backend.task.taskbackend.customer.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
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
