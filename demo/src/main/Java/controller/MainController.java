package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.User;
import service.MainService;

@Controller
@RequestMapping(value = "/main")
public class MainController {
    private static final MainService service = new MainService();

    /// ----------- View -----------------
    /**
     * 登录页
     * @return 登录页
     */
    @RequestMapping(value = "/index")
    public String indexView() {
        return "/index";
    }
    @RequestMapping(value = "/success")
    public String successView() {
        return "/success";
    }



    /// ------------ API ------------------
    /**
     * 登录
     * @return 登录
     */
    /// produces = "application/json; charset=utf-8"，解决后端到前端中文乱码
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String login(@RequestBody User user) {
        return service.login(user);
    }
}
