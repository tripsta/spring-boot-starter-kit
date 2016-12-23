package com.sbsk.persistence.repositories;

import com.sbsk.persistence.entities.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  List<UserEntity> findAll();

}

