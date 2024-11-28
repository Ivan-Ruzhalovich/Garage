package Garage.Controllers;


import Garage.entity.tech.Refuel;
import Garage.service.SecondService;
import Garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("/refuel")
public class RefuelController {

    @Autowired
    SecondService secondService;

    @Autowired
    UserService userService;

    @RequestMapping("/refuel/newRefuel")
    public String newRefulel(@RequestParam(name = "carId") long carId,
                             Model model){
        Refuel refuel = new Refuel();
        refuel.setCarId(carId);
        model.addAttribute("NewRefuel",refuel);
        return "refuel";
    }

    @RequestMapping("/refuel/AllRefuels")
    public String allRefuels(@RequestParam(name = "carId")long carId,
                             Model model){
        model.addAttribute("RefuelsList",secondService.getAllCarRefuel(carId));
        model.addAttribute("Car",userService.getCar(carId));
        return "AllRefuels";
    }

    @RequestMapping("/refuel/deleteRefuel")
    public String deleteRefuel(@RequestParam(name = "refuelId") long refuelId,
                               @RequestParam(name = "carId") long carId,
                               Model model){
        secondService.deleteRefuel(refuelId);
        model.addAttribute("RefuelsList",secondService.getAllCarRefuel(carId));
        return "AllRefuels";
    }
}
