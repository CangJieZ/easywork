package easy.work.source.module.user.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import easy.work.source.commom.encrypt.RSAUtils;
import easy.work.source.module.user.pojo.BackUserPojo;

@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserController {

}
