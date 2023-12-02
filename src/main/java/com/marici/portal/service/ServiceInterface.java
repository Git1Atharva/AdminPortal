package com.marici.portal.service;

import jakarta.persistence.criteria.CriteriaBuilder;

public interface ServiceInterface {
public Boolean login(String userName,String password);
public Boolean deleteUser(int id);
public Boolean addUser(String userName,String password,String role);
public Boolean updateUser(int id,String userName,String password,String role);

}
