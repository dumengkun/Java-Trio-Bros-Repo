package com.trio.project;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface VolunRepository extends MongoRepository<Volunteer, String> {

    public Volunteer findByEmail(String email);
    public void deleteByEmail(String email);
    public List<Volunteer> findByName(String name);

}