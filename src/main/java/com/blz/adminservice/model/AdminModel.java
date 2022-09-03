package com.blz.adminservice.model;

import com.blz.adminservice.dto.AdminDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "admintable")
@NoArgsConstructor
public class AdminModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String emailId;
    private String profilePath;
    private boolean status;
    private String password;
    private LocalDateTime createdStamp;
    private LocalDateTime updateStamp;

    public AdminModel(AdminDTO adminDTO) {
        this.firstName = adminDTO.getFirstName();
        this.lastName = adminDTO.getLastName();
        this.mobile = adminDTO.getMobile();
        this.emailId = adminDTO.getEmailId();
        this.profilePath = adminDTO.getProfilePath();
        this.status = adminDTO.isStatus();
        this.password = adminDTO.getPassword();
        this.createdStamp = LocalDateTime.now();
        this.updateStamp = LocalDateTime.now();
    }

}
