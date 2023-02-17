package com.configuracion.admin.controllers;

import com.configuracion.admin.models.UserModel;
import com.configuracion.admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ArrayList<UserModel> getAll(){
        return userService.listUsers();
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel userModel){
        return userService.createUser(userModel);
    }

    @PutMapping
    public UserModel updateUser(@RequestBody UserModel userModel){
        return userService.updateUser(userModel, userModel.getId());
    }

}
