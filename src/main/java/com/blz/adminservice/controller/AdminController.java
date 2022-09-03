package com.blz.adminservice.controller;

import com.blz.adminservice.dto.AdminDTO;
import com.blz.adminservice.model.AdminModel;
import com.blz.adminservice.service.AdminService;
import com.blz.adminservice.util.AdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Purpose : AdminController to process Admin Data API's
 * @author : Sanddep Varma Dubyala
 * @version : 17.0.3+7-b469.32 amd64
 * @date : 01-09-2022
 */

@RestController
@RequestMapping("/adminmodule")
public class AdminController {
    /*
     *Introducing DTO and Model and Service Layer to Learning Management System App and created a Rest Controller
     * to demonstrate the various HTTP Methods
     */
    @Autowired
    AdminService adminService;

    /*
     *@Purpose : Ability to insert admin details into the Admin Repository
     * @Param : AdminDTO
     */
    @PostMapping("/addAdmin")
    public AdminModel addAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        return adminService.addAdmin(adminDTO);
    }

    /*
     *@Purpose : Ability to update admin details into the Admin Repository
     * @Param : AdminDTO and id
     */
    @PutMapping("/update/{id}")
    public AdminModel updateAdmin(@PathVariable("id") Long id, @Valid @RequestBody AdminDTO adminDTO,
                                  @RequestHeader String token) {
        return adminService.updateAdmin(id, adminDTO, token);
    }

    /*
     *@Purpose : Ability to get list of admin details in the Admin Repository
     * @Param : token
     */
    @GetMapping("/getadmin")
    public List<AdminModel> getAdmin(@RequestHeader String token) {
        return adminService.getAdmin(token);
    }

    /*
     *@Purpose : Ability to delete  admin details by id in the Admin Repository
     * @Param : token and id
     */
    @DeleteMapping("/deleteadmin/{id}")
    public AdminModel deleteAdmin(@PathVariable("id") Long id, @RequestHeader String token) {
        return adminService.deleteAdmin(id, token);
    }

    /*
     *@Purpose : Ability to Access  admin details by using login in the Admin Repository
     * @Param : emailId and password
     */
    @PostMapping("/login")
    public AdminResponse loginAdmin(@RequestParam String emailId, @RequestParam String password) {
        return adminService.loginAdmin(emailId, password);
    }

    /*
     *@Purpose : Ability to Reset Password of admin user in the Admin Repository
     * @Param : EmailId
     */
    @PostMapping("/resetpassword")
    public AdminResponse resetPassword(@RequestParam String emailId) {
        return adminService.resetPassword(emailId);
    }

    /*
     *@Purpose : Ability to Change Password of admin user in the Admin Repository
     * @Param : token and password
     */
    @PutMapping("/changepassword/{id}")
    public AdminModel changePassword(@PathVariable("id") String token, @RequestParam String password) {
        return adminService.changePassword(token, password);
    }

    /*
     *@Purpose : Ability to Add Profile of admin user in the Admin Repository
     * @Param : id and token
     */
    @PostMapping("/addprofilepath")
    public AdminModel addProfilePath(@RequestParam Long id, @RequestParam String profilePath, @RequestHeader String token) {
        return adminService.addProfilePath(id, profilePath, token);
    }
}
