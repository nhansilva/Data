package vn.com.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.knowledge.model.Data;

public interface DataRepository extends MongoRepository<Data, String> {
}
