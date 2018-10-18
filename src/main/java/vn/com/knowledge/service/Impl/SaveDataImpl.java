package vn.com.knowledge.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.knowledge.repository.DataRepository;
import vn.com.knowledge.service.SaveDBService;
@Service
public class SaveDataImpl implements SaveDBService {

    @Autowired
    DataRepository repository;
    @Override
    public void SaveDb() {
        System.out.print("hello");
        System.out.print(repository.findAll().size());
    }
}
