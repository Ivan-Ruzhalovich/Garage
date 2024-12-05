package Garage.Controllers;

import Garage.Classes.CarInfo;
import Garage.Classes.MailSenderThread;
import Garage.entity.*;
import Garage.entity.tech.Refuel;
import Garage.entity.tech.TechCar;
import Garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.UUID;

@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/index-enter")
    public String index(Model model) {
        return "index-enter";
    }

    @RequestMapping("/auth")
    public String authorize(@RequestParam(name = "login") String login,
                            @RequestParam(name = "password") String password,
                            Model model) {
        long userId = userService.auth(login, password, bCryptPasswordEncoder);
        if (userId == -1) {
            model.addAttribute("Message", "Не верно введен логин или пароль!");
            return "index-enter";
        } else if (userId == 0) {
            model.addAttribute("Message", "Вы заблокированы!");
            return "index-enter";
        } else {
            model.addAttribute("UserInfo", userService.getUser(userId));
            return "redirect:/userInfo/" + userId;
        }
    }

    @RequestMapping("/userInfo/{userId}")
    public String userInfo(@PathVariable String userId,
                           Model model) {
        model.addAttribute("User", userService.getUser(Long.parseLong(userId)));
        return "user-info";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("NewUser", new User());
        return "AddNewUser";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute(name = "UserInfo") User user,
                           BindingResult bindingResult,
                           @RequestParam(name = "login") String login,
                           @RequestParam(name = "password") String password,
                           @RequestParam("email") String email,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("NewUser", new User());
            model.addAttribute("Message", "Не корректный ввод имени или фамилии");
            return "AddNewUser";
        }
        if (userService.auth(login)) {
            user.setUserNameAuth(new SecurityUsers(login, bCryptPasswordEncoder
                    .encode(password), 0));
            user.setName(User.toUpperCaseForFirstSymbol(user.getName()));
            user.setSurname(User.toUpperCaseForFirstSymbol(user.getSurname()));
            user.setEmail(email);
            user.setActivationCode(UUID.randomUUID().toString());
            userService.saveUser(user, new Authorities(login, "ROLE_USER"));
            MailSenderThread mailSenderThread = new MailSenderThread(user);
            mailSenderThread.setPassword(password);
            new Thread(mailSenderThread).start();
            return "InfoForActivatedAccountView";
        } else {
            model.addAttribute("Message", "Пользователь с таким логином уже существует");
            model.addAttribute("NewUser", new User());
            return "AddNewUser";
        }
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") long userId) {
        userService.deleteUser(userId);
        return "index-enter";
    }

    @RequestMapping("/addCar")
    public String addCar(@RequestParam(name = "userId") long userId,
                         Model model) {
        model.addAttribute("NewCar", new Car(userId));
        return "addCarView";
    }

    @RequestMapping( "/saveCar")
    public String saveCar(@ModelAttribute(name = "NewCar") Car car,
                          @RequestParam(name = "brand") String brand,
                          @RequestParam(name = "model") String carModel,
                          @RequestParam(name = "year") int year,
                          @RequestParam(name = "kilometrage") int kilometrage,
                          Model model) {
        if (car.getEngine().isEmpty()) {
            model.addAttribute("NewCar", new Car(car.getUserId()));
            model.addAttribute("Message", "Не выбран двигатель");
            return "addCarView";
        }
        if (car.getTransmission().isEmpty()) {
            model.addAttribute("NewCar", new Car(car.getUserId()));
            model.addAttribute("Message", "Не выбрана коробка передач");
            return "addCarView";
        }
        car.setKilometrage(kilometrage);
        car.setYear(year);
        car.setBrand(brand);
        car.setModel(carModel);
        userService.saveCar(car);
        return "redirect:/userInfo/" + car.getUserId();
    }

    @RequestMapping("/deleteCar")
    private String deleteCar(@RequestParam(name = "carId") long carId,
                             @RequestParam(name = "userId") long userId) {
        userService.deleteCar(carId);
        return "redirect:/userInfo/"+userId;
    }

    @RequestMapping("/userAccountInfo")
    public String userAccountInfo(@RequestParam(name = "userId") int userId, Model model) {
        model.addAttribute("userAccountInfo", userService.getAccountInformation(userId));
        return "userAccountInfo";
    }

    @RequestMapping("/saveTechCar")
    public String saveTechCar(@ModelAttribute(name = "NewTechCar") TechCar techCar,
                              @RequestParam(name = "date") String date,
                              @RequestParam(name = "kilometrage") int kilometrage,
                              @RequestParam(name = "price") int price,
                              @RequestParam(name = "carId") long carId,
                              Model model,RedirectAttributes redirectAttributes) {
        if (techCar.getWork().getId()==0){
            TechCar newTechCar = new TechCar(carId,userService.getAllWorks());
            newTechCar.setCarId(carId);
            newTechCar.setWorks(userService.getAllWorks());
            model.addAttribute("NewTechCar",newTechCar);
            model.addAttribute("Message","Не выбран вид работ!");
            return "addTechCar";
        }
        techCar.setWork(userService.getWork(techCar.getWork().getId()));
        techCar.setDate(date);
        techCar.setKilometrage(kilometrage);
        techCar.setPrice(price);
        Car car = userService.getCar(carId);
        car.addTechToListTech(techCar);
        if (techCar.getKilometrage() > car.getKilometrage())
            car.setKilometrage(techCar.getKilometrage());
        userService.saveCar(car);
        return "redirect:/carInfo/"+carId;
    }

    @RequestMapping("/saveRefuelCar")
    public String saveRefuelCat(@ModelAttribute(name = "NewRefuel") Refuel refuel,
                                @RequestParam(name = "date") String date,
                                @RequestParam(name = "volume") float volume,
                                @RequestParam(name = "kilometrage") int kilometrage,
                                @RequestParam(name = "price") float price,
                                @RequestParam(name = "carId") long carId,
                                Model model, RedirectAttributes redirectAttributes) {
        if (refuel.getTypeFuel().equals("null")) {
            model.addAttribute("NewRefuel", refuel);
            model.addAttribute("Message", "Не выбран тип топлива");
            return "refuel";
        }
        Car car = userService.getCar(carId);
        refuel.setDate(date);
        refuel.setVolume(volume);
        refuel.setKilometrage(kilometrage);
        refuel.setPrice(price);
        car.addRefuelToListRefuel(refuel);
        if (refuel.getKilometrage() > car.getKilometrage())
            car.setKilometrage(refuel.getKilometrage());
        userService.saveCar(car);
        return "redirect:/carInfo/"+carId;
    }

    @RequestMapping("/carInfo/{id}")
    public String carInfoId(@PathVariable String id, Model model) {
        model.addAttribute("carInfo", new CarInfo(userService.getCar(Long.parseLong(id)))
                .getCarInfo());
        return "carInfoView";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        long id = userService.activateUserAccount(code);
        if (id !=-1) {
            return "redirect:/index-enter";
        }
        else{
            model.addAttribute("Message", "Ошибка активации аккаунта! Попробуйте еще раз.");
            return "index-enter";
        }
    }
}