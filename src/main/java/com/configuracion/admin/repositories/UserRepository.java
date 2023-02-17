package com.configuracion.admin.repositories;

import com.configuracion.admin.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel,Long > {
    UserModel findAllById(Long id);
}
