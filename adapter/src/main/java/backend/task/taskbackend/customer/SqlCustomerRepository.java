package backend.task.taskbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SqlCustomerRepository extends JpaRepository<CustomerSnapshot, Integer> {
    Optional<CustomerSnapshot> findCustomerSnapshotByEmail(String email);
}
@Repository
class CustomerRepositoryImpl implements CustomerRepository, CustomerQueryRepository{

    private final SqlCustomerRepository sqlCustomerRepository;

    CustomerRepositoryImpl(SqlCustomerRepository sqlCustomerRepository) {
        this.sqlCustomerRepository = sqlCustomerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return Customer.restore(sqlCustomerRepository.save(customer.getSnapshot()));
    }

    @Override
    public Optional<CustomerSnapshot> findCustomerSnapshotByEmail(String email) {
        return sqlCustomerRepository.findCustomerSnapshotByEmail(email);
    }

}
