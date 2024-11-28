package Garage.service;

import Garage.entity.tech.Refuel;
import Garage.entity.tech.TechCar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SecondService {

    public List<Refuel> getAllCarRefuel(long carId);

    public void deleteRefuel(long refuelId);

    public void deleteAllRefuelForCar(long carId);

    public void deleteWork(long workId);

    public List<TechCar> getAllCarTech(long carId);
}
