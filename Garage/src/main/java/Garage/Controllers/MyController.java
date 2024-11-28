package Garage.Controllers;


import Garage.Classes.CarInfo;
import Garage.Classes.UserAccountInfo;
import Garage.entity.*;
import Garage.entity.tech.Refuel;
import Garage.entity.tech.TechCar;
import Garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/index-enter")
    public String index(Model model){
        return "index-enter";
    }

    @RequestMapping("/auth")
    public String authorize(@RequestParam(name = "login")String login,
                            @RequestParam(name = "password")String password,
                            Model model){
        long userId = userService.auth(login,password,bCryptPasswordEncoder);
        if (userId==-1){
            model.addAttribute("Message","Не верно введен логин или пароль!");
            return "index-enter";
        }
        else if (userId==0){
            model.addAttribute("Message","Вы заблокированы!");
            return "index-enter";
        }
        else{
            model.addAttribute("UserInfo",userService.getUser(userId));
            return "user-info";}
    }

    @RequestMapping("/home")
    public String showAllUsers(Model model){
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return "Old/all-users";
    }
    @RequestMapping("/addNewUser")
    public String addNewUser (Model model){
        model.addAttribute("NewUser",new User());
        return "AddNewUser";
    }
    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute(name = "UserInfo") User user,
                           @RequestParam(name = "login")String login,
                           @RequestParam(name = "password")String password,
                           @RequestParam(name = "dateOfBirth") String dateOfBirth){
        user.setUserNameAuth(new SecurityUsers(login,bCryptPasswordEncoder.encode(password),1));
        user.setDateOfBirth(dateOfBirth);
        userService.saveUser(user);
        return "user-info";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") long userId){
        userService.deleteUser(userId);
        return "/index-enter";
    }

    @RequestMapping("/addCar")
    public String addCar(@RequestParam(name = "userId")long userId,
            Model model){
        model.addAttribute("NewCar",new Car(userId));
        return "addCarView";
    }
    @RequestMapping("/saveCar")
    public String saveCar(@ModelAttribute(name = "NewCar") Car car,
                          @RequestParam(name = "brand") String brand,
                          @RequestParam(name = "model") String carModel,
                          @RequestParam(name = "year") int year,
                          @RequestParam(name = "kilometrage") int kilometrage,
                          Model model){
        car.setKilometrage(kilometrage);
        car.setYear(year);
        car.setBrand(brand);
        car.setModel(carModel);
        userService.saveCar(car);
        model.addAttribute("UserInfo",userService.getUser(car.getUserId()));
        return "user-info";
    }

    @RequestMapping("/deleteCar")
    private String deleteCar(@RequestParam(name = "carId")int id,
                             @RequestParam(name = "userId")int userId,
                             Model model){
        userService.deleteCar(id);
        User user = userService.getUser(userId);
        model.addAttribute("UserInfo",user);
    return "user-info";
    }

    @RequestMapping("/userAccountInfo")
    public String userAccountInfo(@RequestParam(name = "userId")int userId, Model model){
        UserAccountInfo userAccountInfo = userService.getAccountInformation(userId);
        System.out.println(userAccountInfo);
        model.addAttribute("userAccountInfo",userAccountInfo);
        return "userAccountInfo";
    }

    @RequestMapping("/index")
    public String indexView(){
        System.out.println("dcsdcsdcsdc");
        return "Old/addCarTest";
    }

    @RequestMapping("/saveTechCar")
    public String saveTechCar(@ModelAttribute(name = "NewTechCar") TechCar techCar,
                              @RequestParam(name = "date") String date,
                              @RequestParam(name = "kilometrage")int kilometrage,
                              @RequestParam (name = "price")int price,
                              @RequestParam(name = "carId") long carId,
                              Model model){
        techCar.setWork(userService.getWork(techCar.getWork().getId()));
        techCar.setDate(date);
        techCar.setKilometrage(kilometrage);
        techCar.setPrice(price);
        Car car = userService.getCar(carId);
        car.addTechToListTech(techCar);
        if(techCar.getKilometrage()>car.getKilometrage())
            car.setKilometrage(techCar.getKilometrage());
        userService.saveCar(car);
        User user = userService.getUser(car.getUserId());
        model.addAttribute("UserInfo",user);
        return "user-info";
    }

    @RequestMapping("/saveRefuelCar")
    public String saveRefuelCat(@ModelAttribute(name = "NewRefuel") Refuel refuel,
                              @RequestParam(name = "date") String date,
                              @RequestParam(name = "type_fuel")String typeFuel,
                              @RequestParam (name = "volume")float volume,
                              @RequestParam(name = "kilometrage") int kilometrage,
                              @RequestParam (name = "price") float price,
                              @RequestParam(name = "carId") long carId,
                              Model model){
        Car car = userService.getCar(carId);
        refuel.setDate(date);
        refuel.setTypeFuel(typeFuel);
        refuel.setVolume(volume);
        refuel.setKilometrage(kilometrage);
        refuel.setPrice(price);
        car.addRefuelToListRefuel(refuel);
        if (refuel.getKilometrage()>car.getKilometrage())
            car.setKilometrage(refuel.getKilometrage());
        userService.saveCar(car);
        model.addAttribute("UserInfo",userService.getUser(car.getUserId()));
        return "user-info";
    }

    @RequestMapping("/carInfo")
    public String testWarn(@RequestParam(name = "carId")long carId,Model model){
        model.addAttribute("carInfo",new CarInfo(userService.getCar(carId)));
        return "carInfoView";
    }

}
