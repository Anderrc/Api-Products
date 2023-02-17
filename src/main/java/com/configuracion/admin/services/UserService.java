package com.configuracion.admin.services;

import com.configuracion.admin.models.UserModel;
import com.configuracion.admin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public ArrayList<UserModel> listUsers(){
      return (ArrayList<UserModel>) userRepository.findAll();
  }

  public UserModel createUser(UserModel userModel){
      return userRepository.save(userModel);
  }

  public UserModel updateUser(UserModel userModel, Long id){
      UserModel resultUser = userRepository.findAllById(id);
      resultUser.setName(userModel.getName());
      resultUser.setEmail(userModel.getEmail());
      resultUser.setPriority(userModel.getPriority());
      return userRepository.save(resultUser);
  }
}
