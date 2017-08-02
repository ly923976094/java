package yodo1.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController = @ResponseBody + @Controller
//@ResponseBody
//@Controller
@RestController
@RequestMapping(value = "hello")
public class hellWorldApp {
    @Value("${cupSize}")
    private String cupsize;

    @Value("${age}")
    private String age;

    @Value("${content}")
    private String content;

    @Autowired
    private GirlProperties girl;

    //    @RequestMapping(value = {"/say", "/hi"}, method = RequestMethod.POST)
//    @RequestMapping(value = {"/say/{id}", "/{id}/hi"}) //POST,GET
//    public String say(@PathVariable("id") Integer id) {
//        return "id:" + id;
//    }
//    @RequestMapping(value = "/say", method = RequestMethod.GET) //POST,GET
    @GetMapping(value = "say")
    public String say(@RequestParam(value = "id", required = true, defaultValue = "100") Integer id) {
        return "myid:" + id;
    }
}
