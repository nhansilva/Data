package vn.com.knowledge.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.knowledge.service.SaveDBService;

@RestController
public class Controller {
    @Autowired
    SaveDBService saveDBService;

    @GetMapping("/data")
    public String hi()
    {
        saveDBService.SaveDb();
        return "hello";
    }
}
