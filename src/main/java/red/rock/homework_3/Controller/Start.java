package red.rock.homework_3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import red.rock.homework_3.Service.AllService;

/**
 * @author 郑煜
 * @Title: Start
 * @ProjectName homework_3
 * @Description: TODO
 * @date 2019/4/9 18:10
 */
@RestController
public class Start {

    @Autowired
    private AllService allService;

    @RequestMapping("/hello")
    public String getAll()throws Exception{
        allService.AddAllStudent();
        return null;
    }

}
