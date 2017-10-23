package com.sbsk.persistence.repositories;

import com.sbsk.persistence.entities.couchbase.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}

