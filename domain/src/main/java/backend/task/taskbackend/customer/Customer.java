package backend.task.taskbackend.customer;

class Customer {
    static Customer restore(CustomerSnapshot customerSnapshot){
        return new Customer(customerSnapshot.getId(),
                customerSnapshot.getEmail(),
                customerSnapshot.getPassword());
    }
    private final int id;
    private final String email;
    private final String password;

    private Customer(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    CustomerSnapshot getSnapshot(){
        return CustomerSnapshot.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }
}
