 package CSI2132.ehotel.entity;

import jakarta.persistence.Column;
// renamed from javax
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee{
    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    public Long employee_id = (long) 0;
 
    @Column(name = "address")
    public String address;
    
    @Column(name = "manager_id")
    public int manager_id;

    @Column(name = "name")
    public String name;
   
    @Column(name = "role")
    public String role;
    
    @Column(name = "sin")
    public String sin;
    public Employee(){}
    
    public Employee(String name, String address, String sin, int id) {
        super();
        this.name = name;
        this.address = address;
        this.sin = sin;
        this.manager_id = id;
    }
    public Long getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(Long id) {
        this.employee_id = id;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSin() {
        return sin;
    }
    public void setSin(String sin) {
        this.sin = sin;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public int getManager_id() {
        return manager_id;
    }
    public void setManager_id(int id) {
        this.manager_id = id;
    }

}
