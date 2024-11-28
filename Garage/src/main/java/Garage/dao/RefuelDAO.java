package Garage.dao;

import Garage.entity.tech.Refuel;

import java.util.List;

public interface RefuelDAO {


    public void deleteRefuel(long refuelId);

    public void deleteAllRefuelForCar(long carId);

    public List<Refuel> getAllCarRefuel(long carId);

}
