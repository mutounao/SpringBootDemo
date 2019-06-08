package cn.mutounao.demo.controllers.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class ErrorPagesController {
    @RequestMapping("400")
    public String err400(){
        return "/error/400.html";
    }

    @RequestMapping("500")
    public String err500(){
        return "/error/500.html";
    }
}
