package backend.task.taskbackend.customer.dto;

public class SimpleCustomer {
    public static SimpleCustomer restore(SimpleCustomerSnapshot customer) {
        return new SimpleCustomer(customer.getId(),
                customer.getEmail());
    }

    private final int id;
    private final String email;

    private SimpleCustomer(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public SimpleCustomerSnapshot getSnapshot() {
        return SimpleCustomerSnapshot.builder()
                .id(id)
                .email(email)
                .build();
    }
}
