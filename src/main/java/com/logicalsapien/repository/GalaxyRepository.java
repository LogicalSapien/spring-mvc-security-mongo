package com.logicalsapien.repository;

import com.logicalsapien.entity.Galaxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalaxyRepository extends MongoRepository<Galaxy, String> {

}
