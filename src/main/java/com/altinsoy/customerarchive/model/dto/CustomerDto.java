package com.altinsoy.customerarchive.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    @NotEmpty(message = "Can't be empty")
    @Size(min = 11, max = 11, message = "Identity number should be at least 11 digits")
    private String identityNumber;

    @NotEmpty(message = "Can't be empty")
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @NotEmpty(message = "Can't be empty")
    @Size(min = 2, message = "Surname should be at least 2 characters")
    private String surname;

    @Email(message = "Not a properly formatted email address!")
    private String email;
}
