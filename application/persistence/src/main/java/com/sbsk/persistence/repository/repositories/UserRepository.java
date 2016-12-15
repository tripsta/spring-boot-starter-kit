package com.sbsk.persistence.repository.repositories;

import com.sbsk.persistence.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  List<UserEntity> findByFirstName(String reader);

}

