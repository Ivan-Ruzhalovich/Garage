package Garage.dao;

import Garage.entity.Car;

import java.util.List;
import java.util.Map;

public interface CarDAO {
    public void saveCar(Car car);

    public List<Car> getAllCars();

    public List<Car> getCarList(long id);

    public Car getCar(long id);

    public void deleteCar(long id);

//    public Map<Integer,String> getTransmitions();
//
//    public Map<Integer,String> getEngines();
//
//    public Engine getEngine(int id);
//
//    public Transmition getTransmition(int id);
}
