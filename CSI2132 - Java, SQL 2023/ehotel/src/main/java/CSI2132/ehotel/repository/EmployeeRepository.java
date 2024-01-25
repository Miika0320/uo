package CSI2132.ehotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import CSI2132.ehotel.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
