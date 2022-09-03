package com.blz.adminservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AdminDTO {
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Admin FirstName Invalid")
    private String firstName;
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Admin LastName Invalid")
    private String lastName;
    @Pattern(regexp = "^[1-9]{2}\\s{1}[1-9]{1}[0-9]{9}$", message = "Invalid Mobile Number")
    private String mobile;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email Id")
    private String emailId;
    @NotBlank(message = "ProfilePath cannot be Empty")
    private String profilePath;
    private boolean status;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%!]).{8,}$", message = "Invalid Password")
    private String password;
}
