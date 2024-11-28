package Garage.Controllers;

import Garage.entity.Authorities;
import Garage.entity.SecurityUsers;
import Garage.entity.User;
import Garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/registred")
public class RegisredController {
@Autowired
private UserService userService;

@RequestMapping("/registred/")
    public String registredNewUser(Model model){
    User user = new User();
    model.addAttribute("NewUser",user);
    return "username_and_password";
}
@RequestMapping("/registred/saveUser")
public String saveNewUser(@RequestParam(name = "username") String username,
                       @RequestParam(name = "password") String password,
                       @RequestParam(name = "enabled") int i,
                       @RequestParam(name = "authority") String authority,
                       @ModelAttribute("NewUser") User user,
                       Model model){
    SecurityUsers newSecurityUsers = new SecurityUsers(username,password,i);
    Authorities newAuthority = new Authorities(username,authority);
    user.setUserNameAuth(newSecurityUsers);
    userService.saveUser(user,newAuthority);
    model.addAttribute("UserInfo",user);
    return "user-info";
}
}
