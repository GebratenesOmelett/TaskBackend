package backend.task.taskbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

interface SqlCustomerRepository extends JpaRepository<CustomerSnapshot, Integer> {
}
@Repository
class CustomerRepositoryImpl implements CustomerRepository{

    private final SqlCustomerRepository sqlCustomerRepository;

    CustomerRepositoryImpl(SqlCustomerRepository sqlCustomerRepository) {
        this.sqlCustomerRepository = sqlCustomerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return Customer.restore(sqlCustomerRepository.save(customer.getSnapshot()));
    }
}
