package CSI2132.ehotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import CSI2132.ehotel.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
 
//     // @Ref: https://spring.io/blog/2011/02/10/getting-started-with-spring-data-jpa
//     // Spring data JPA provides a repository programming model
    
}
