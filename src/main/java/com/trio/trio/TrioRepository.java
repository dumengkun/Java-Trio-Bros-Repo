package com.trio.trio;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrioRepository extends MongoRepository<Trio, String> 
{

}