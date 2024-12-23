package net.javaguides.ems.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {                  // This class is used to transfer the data between client and server

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
