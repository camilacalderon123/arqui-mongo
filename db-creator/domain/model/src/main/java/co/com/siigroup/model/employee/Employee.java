package co.com.siigroup.model.employee;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor()
public class Employee {
    private  String id;
    private  String employeeName;
    private  float employeeSalary;
    private  Integer employeeAge;
    private  String profileImage;
    private  float yearSalary;


}
