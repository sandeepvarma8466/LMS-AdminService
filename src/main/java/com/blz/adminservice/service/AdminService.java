package com.blz.adminservice.service;

import com.blz.adminservice.dto.AdminDTO;
import com.blz.adminservice.exception.AdminNotFoundException;
import com.blz.adminservice.model.AdminModel;
import com.blz.adminservice.repository.AdminRepository;
import com.blz.adminservice.util.AdminResponse;
import com.blz.adminservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

   /* @Autowired
    Email email;

    @Autowired
    private MessageProducer messageproducer;*/

    @Override
    public AdminModel addAdmin(AdminDTO adminDTO) {
        AdminModel adminModel = new AdminModel(adminDTO);
        adminRepository.save(adminModel);
        String body = "Admin added Successfully with admin Id" + adminModel.getAdminId();
        String subject = "Admin added successfully in LMS";
        mailService.send(adminModel.getEmailId(), subject, body);
        return adminModel;
    }

    @Override
    public AdminModel updateAdmin(Long id, AdminDTO adminDTO, String token) {
        Long decode = tokenUtil.decodeToken(token);
        Optional<AdminModel> tokenPresent = adminRepository.findById(decode);
        if (tokenPresent.isPresent()) {
            Optional<AdminModel> dataPresent = adminRepository.findById(id);
            if (dataPresent.isPresent()) {
                dataPresent.get().setFirstName(adminDTO.getFirstName());
                dataPresent.get().setLastName(adminDTO.getLastName());
                dataPresent.get().setMobile(adminDTO.getMobile());
                dataPresent.get().setEmailId(adminDTO.getEmailId());
                dataPresent.get().setProfilePath(adminDTO.getProfilePath());
                dataPresent.get().setStatus(adminDTO.isStatus());
                dataPresent.get().setPassword(adminDTO.getPassword());
                dataPresent.get().setCreatedStamp(LocalDateTime.now());
                dataPresent.get().setUpdateStamp(LocalDateTime.now());
                adminRepository.save(dataPresent.get());
                String body = "Admin updated Successfully with admin Id" + dataPresent.get().getAdminId();
                String subject = "Admin udpdated successfully in LMS";
                mailService.send(dataPresent.get().getEmailId(), subject, body);
                return dataPresent.get();
            } else
                throw new AdminNotFoundException("AdminNotfound", 400);
        } else
            throw new AdminNotFoundException("InvalidTokenId", 400);
    }

    @Override
    public List<AdminModel> getAdmin(String token) {
        Long decode = tokenUtil.decodeToken(token);
        Optional<AdminModel> tokenPresent = adminRepository.findById(decode);
        if (tokenPresent.isPresent()) {
            List<AdminModel> getdata = adminRepository.findAll();
            if (getdata.size() > 0) {
                return getdata;
            } else
                throw new AdminNotFoundException("AdminNotfound", 400);
        } else
            throw new AdminNotFoundException("InvalidTokenId", 400);
    }

    @Override
    public AdminModel deleteAdmin(Long id, String token) {
        Long decode = tokenUtil.decodeToken(token);
        Optional<AdminModel> isTokenPresent = adminRepository.findById(decode);
        if (isTokenPresent.isPresent()) {
            Optional<AdminModel> isPresent = adminRepository.findById(id);
            if (isPresent.isPresent()) {
                adminRepository.delete(isPresent.get());
                String body = "Admin deleted Successfully with admin Id" + isPresent.get().getAdminId();
                String subject = "Admin deleted successfully in LMS";
                mailService.send(isPresent.get().getEmailId(), subject, body);
                return isPresent.get();
            } else
                throw new AdminNotFoundException("AdminNotfound", 400);
        } else
            throw new AdminNotFoundException("InvalidTokenId", 400);

    }

    @Override
    public AdminResponse loginAdmin(String emailId, String password) {
        Optional<AdminModel> isEmailPresent = adminRepository.findByemailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getAdminId());
                return new AdminResponse(200, "Login Successfull", token);
            }
            throw new AdminNotFoundException("PasswordNotMatched", 400);
        }
        throw new AdminNotFoundException("EmailNOtFound", 400);
    }

    @Override
    public AdminResponse resetPassword(String emailId) {
        Optional<AdminModel> isMailPresent = adminRepository.findByemailId(emailId);
        if (isMailPresent.isPresent()) {
            String token = tokenUtil.createToken(isMailPresent.get().getAdminId());
            String url = "http://localhost:8089/adminmodule/loginAdmin" + token;
            String subject = "Admin Password reset request sent in LMS";
            mailService.send(isMailPresent.get().getEmailId(), subject, url);
            return new AdminResponse(200, "Reset password", token);
        }
        throw new AdminNotFoundException("EmailNOtFound", 400);
    }

    @Override
    public AdminModel changePassword(String token, String password) {
        Long decode = tokenUtil.decodeToken(token);
        Optional<AdminModel> isTokenPresent = adminRepository.findById(decode);
        if (isTokenPresent.isPresent()) {
            isTokenPresent.get().setPassword(password);
            adminRepository.save(isTokenPresent.get());
            String body = "Admin Password change successfully with id" + isTokenPresent.get().getAdminId();
            String subject = "Admin Password change successfully in LMS";
            mailService.send(isTokenPresent.get().getEmailId(), subject, body);
            return isTokenPresent.get();
        }
        throw new AdminNotFoundException("Token not find", 400);
    }

    @Override
    public AdminModel addProfilePath(Long id, String profilePath, String token) {
        Long decode = tokenUtil.decodeToken(token);
        Optional<AdminModel> isTokenPresent = adminRepository.findById(decode);
        if (isTokenPresent.isPresent()) {
            Optional<AdminModel> isIdPresent = adminRepository.findById(id);
            if (isIdPresent.isPresent()) {
                isIdPresent.get().setProfilePath(profilePath);
                adminRepository.save(isIdPresent.get());
                String body = "Admin Profile Path change successfully with id" + isIdPresent.get().getAdminId();
                String subject = "Admin Profile Path change successfully in LMS";
                mailService.send(isIdPresent.get().getEmailId(), subject, body);
                return isIdPresent.get();
            }
            throw new AdminNotFoundException("Admin not find", 400);
        }
        throw new AdminNotFoundException("Token not find", 400);
    }

    @Override
    public Boolean validateUser(String token) {
        Long decode = tokenUtil.decodeToken(token);
        Optional<AdminModel> isTokenPresent = adminRepository.findById(decode);
        if (isTokenPresent.isPresent())
            return true;
        throw new AdminNotFoundException("Token not find", 400);
    }

}
