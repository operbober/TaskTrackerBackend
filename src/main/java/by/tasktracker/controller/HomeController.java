package by.tasktracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api")
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}
