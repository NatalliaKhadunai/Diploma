package com.minsk24.repository;

import com.minsk24.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;

/**
 * Created by khadunai on 12/2/2016.
 */
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
}
