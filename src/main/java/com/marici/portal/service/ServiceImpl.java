package com.marici.portal.service;

import com.marici.portal.controller.CustomException;
import com.marici.portal.model.User;
import com.marici.portal.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

@Component
public class ServiceImpl implements ServiceInterface {
    @Autowired
    UserRepo repo;

    @Autowired
    User user;

    @Autowired
    private HttpSession session;
    @Override
    public Boolean login(String userName,String userPass) throws CustomException {
        try {
            user = repo.findByuserName(userName);
            System.out.println(userName + "" + userPass);
            if (user != null && user.getUserPassword().equals(userPass)) {
                session.setAttribute("role", user.getUserRole());
                return true;
            } else {
                return false;
            }
        }catch (CustomException e){
            e.printStackTrace();
            throw e;
        }catch (Exception e){
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public Boolean deleteUser(int id) {
        try {
            String role = (String) session.getAttribute("role");
            if (role.equals("ADMIN")) {
                repo.deleteById(id);
                return true;
            } else {
               return false;
            }
        } catch (CustomException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Boolean addUser(String userName, String password, String role) {
        User user = null;
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setUserRole(role);
        repo.save(user);
        return true;
    }

    @Override
    public Boolean updateUser(int id, String userName, String password, String role) {
        User user=repo.findByuserId(id);
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setUserRole(role);
        repo.save(user);
        return true;
    }
}
