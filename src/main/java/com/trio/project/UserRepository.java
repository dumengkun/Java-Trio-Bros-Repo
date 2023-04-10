package com.trio.project;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmail(String email);
    public void deleteByEmail(String email);
    public List<User> findByName(String name);

}