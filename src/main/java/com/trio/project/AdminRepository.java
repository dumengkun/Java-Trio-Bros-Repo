package com.trio.project;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AdminRepository extends MongoRepository<Administrator, String> {

    public Administrator findByEmail(String email);
    public void deleteByEmail(String email);
    public List<Administrator> findByName(String name);

}