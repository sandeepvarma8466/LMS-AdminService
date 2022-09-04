package com.blz.adminservice.controller;

import com.blz.adminservice.dto.AdminDTO;
import com.blz.adminservice.model.AdminModel;
import com.blz.adminservice.service.AdminService;
import com.blz.adminservice.util.AdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AdminResponse> addAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        AdminModel adminModel = adminService.addAdmin(adminDTO);
        AdminResponse adminResponse = new AdminResponse(200,"Admin added successfully",adminModel);
        return new ResponseEntity<>(adminResponse, HttpStatus.OK);
    }

    /*
     *@Purpose : Ability to update admin details into the Admin Repository
     * @Param : AdminDTO and id
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<AdminResponse> updateAdmin(@PathVariable("id") Long id, @Valid @RequestBody AdminDTO adminDTO,
                                  @RequestHeader String token) {
        AdminModel adminModel = adminService.updateAdmin(id, adminDTO, token);
        AdminResponse adminResponse = new AdminResponse(200,"Admin Updated successfully",adminModel);
        return new ResponseEntity<>(adminResponse, HttpStatus.OK);
    }

    /*
     *@Purpose : Ability to get list of admin details in the Admin Repository
     * @Param : token
     */
    @GetMapping("/getadmin")
    public ResponseEntity<AdminResponse> getAdmin(@RequestHeader String token) {
        List<AdminModel> adminModel =  adminService.getAdmin(token);
        AdminResponse adminResponse = new AdminResponse(200,"Admin Fetched successfully",adminModel);
        return new ResponseEntity<>(adminResponse, HttpStatus.OK);
    }

    /*
     *@Purpose : Ability to delete  admin details by id in the Admin Repository
     * @Param : token and id
     */
    @DeleteMapping("/deleteadmin/{id}")
    public ResponseEntity<AdminResponse> deleteAdmin(@PathVariable("id") Long id, @RequestHeader String token) {
        AdminModel adminModel =  adminService.deleteAdmin(id, token);
        AdminResponse adminResponse = new AdminResponse(200,"Admin Deleted successfully",adminModel);
        return new ResponseEntity<>(adminResponse, HttpStatus.OK);
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
    public ResponseEntity<AdminResponse> changePassword(@PathVariable("id") String token, @RequestParam String password) {
        AdminModel adminModel = adminService.changePassword(token, password);
        AdminResponse adminResponse = new AdminResponse(200,"Admin Password Changed successfully",adminModel);
        return new ResponseEntity<>(adminResponse, HttpStatus.OK);
    }

    /*
     *@Purpose : Ability to Add Profile of admin user in the Admin Repository
     * @Param : id and token
     */
    @PostMapping("/addprofilepath")
    public ResponseEntity<AdminResponse> addProfilePath(@RequestParam Long id, @RequestParam String profilePath,
                                                        @RequestHeader String token) {
        AdminModel adminModel =  adminService.addProfilePath(id, profilePath, token);
        AdminResponse adminResponse = new AdminResponse(200,"Admin Profilepath added successfully",adminModel);
        return new ResponseEntity<>(adminResponse, HttpStatus.OK);
    }

    @GetMapping("/validateuser/{token}")
    public Boolean validateUser(@PathVariable String token) {
        return adminService.validateUser(token);
    }
}
