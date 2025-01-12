package Garage.Controllers;


import Garage.Classes.CarInfo;
import Garage.entity.tech.TechCar;
import Garage.service.SecondService;
import Garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/works")
public class WorksController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecondService secondService;

    @RequestMapping("/works/addNewTechToCar")
    public String addWork (@RequestParam(name = "carId") long carId, Model model){
        TechCar techCar = new TechCar();
        techCar.setCarId(carId);
        techCar.setWorks(userService.getAllWorks());
        model.addAttribute("NewTechCar",techCar);
        return "addTechCar";
    }

    @RequestMapping("/works/allTechCar/{carId}")
    public String getListCarWorks (@PathVariable String carId, Model model){
        model.addAttribute("WorksList",secondService.getAllCarTech(Long.parseLong(carId)));
        model.addAttribute("Car",userService.getCar(Long.parseLong(carId)));
        return "AllTechs";
    }

    @RequestMapping("/works/deleteWork")
    public String deleteWork (@RequestParam(name = "workId")long workId,
                              @RequestParam(name = "carId")long carId,
                              Model model){
        secondService.deleteWork(workId);
        model.addAttribute("Car",userService.getCar(carId));
        model.addAttribute("WorksList",secondService.getAllCarTech(carId));
        return "redirect:/works/allTechCar/" + carId;
    }
}
