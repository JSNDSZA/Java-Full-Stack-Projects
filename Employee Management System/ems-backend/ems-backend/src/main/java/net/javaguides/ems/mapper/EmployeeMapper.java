package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;

public class EmployeeMapper {      // This class is used to map Employee Entity to EmployeeDto and EmployeeDto to Employee Entity

    //Entities are used to represent data in the database, while DTOs are used to represent data that is sent to or received from a client.
    //To map data from an entity to a DTO, you need to define a mapping between the attributes of the two classes. This can be done manually or automatically.

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto)
    {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
