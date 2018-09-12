package com.yule.demo.web.ctrl;

import com.yule.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试ctrl、service、dao
 * Created by yule on 2018/6/28 21:37.
 */
@Controller
@RequestMapping("/demoCtrl")
public class DemoCtrl {
    @Autowired
    private DemoService demoService;

    private final Logger logger = LoggerFactory.getLogger(DemoCtrl.class);

    @RequestMapping("/index")
    public String index() {
        logger.info("sdfdsf{}", "123213");
        this.demoService.test(12);
        return "yule/demo/demo";
    }

    @RequestMapping("/testException")
    public String testException() {
        throw new RuntimeException();
    }

    @ExceptionHandler(RuntimeException.class)
    public void dealException(){
        System.out.println("hei, throw new Exception");
    }

    @RequestMapping("/test")
    @ResponseBody
    public void test(int start){
        this.demoService.test(start);
    }

}
