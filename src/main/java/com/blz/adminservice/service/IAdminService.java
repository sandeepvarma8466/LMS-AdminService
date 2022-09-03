package com.blz.adminservice.service;

import com.blz.adminservice.dto.AdminDTO;
import com.blz.adminservice.model.AdminModel;
import com.blz.adminservice.util.AdminResponse;

import java.util.List;

public interface IAdminService {

    AdminModel addAdmin(AdminDTO adminDTO);

    AdminModel updateAdmin(Long id, AdminDTO adminDTO, String token);

    List<AdminModel> getAdmin(String token);

    AdminModel deleteAdmin(Long id, String token);

    AdminResponse loginAdmin(String emailId, String password);

    AdminResponse resetPassword(String emailId);

    AdminModel changePassword(String token, String password);

    AdminModel addProfilePath(Long id, String profilePath, String token);
}

