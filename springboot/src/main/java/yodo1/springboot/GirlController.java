package yodo1.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GirlController {


    @Autowired
    private GirlResposity girlResposity;

    /**
     * mysql查询所有女生列表
     *
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        return girlResposity.findAll();
    }

    /**
     * 添加一个女生到mysql
     *
     * @param cupSize
     * @param age
     * @return
     */

    @PostMapping(value = "/girls")
    public Girl girAdd(@RequestParam("cupSize") String cupSize,
                       @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlResposity.save(girl);
    }

//    /**
//     * mysql查询一个女生
//     *
//     * @return 有问题
//     */
//    @GetMapping(value = "/girls/{id}")
//    public Girl girlOne(@PathVariable("id") Integer id) {
//        return girlResposity.getOne(id);
//    }

    /**
     * mysql更新一个女生
     *
     * @return
     */
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlResposity.save(girl);
    }

    /**
     * mysql删除一个女生
     *
     * @return
     */
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable Integer id) {
        girlResposity.deleteById(id);
    }

//    /**
//     * mysql 通过age查询数据
//     * @param age
//     * @return
//     */
//    @GetMapping(value = "/girls/age/{age}")
//    public List<Girl> girlAge(@PathVariable Integer age) {
//        return girlResposity.findOne(age);
//    }
}
