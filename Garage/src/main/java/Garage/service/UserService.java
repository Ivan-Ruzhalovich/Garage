package Garage.service;

import Garage.Classes.UserAccountInfo;
import Garage.entity.*;
import Garage.entity.tech.Refuel;
import Garage.entity.tech.TechCar;
import Garage.entity.tech.Works;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public void saveUser(User user,Authorities authorities);

    public User getUser(long id);

    public void deleteUser(long id);

    public void saveCar(Car car);

    public List<Car> getAllCars();

    public Car getCar(long id);

    public List<Car> getCarList(long id);

    public void deleteCar(long id);

    public void saveSecurityOfNewUser(Authorities authorities);

    public UserAccountInfo getAccountInformation(long id);

//    public Map<Integer,String> getTransmitions();
//
//    public Map<Integer,String> getEngines();

//    public Engine getEngine(int id);
//
//    public Transmition getTransmition(int id);

    public Map<Integer,String> getAllWorks();

    public void saveTech(TechCar techCar);

    public Works getWork(int id);

    public List<TechCar> getAllTechForCar(long id);

    public long auth(String login, String password, BCryptPasswordEncoder bCryptPasswordEncoder);

//    public Map<Transmition,String> getTransmitions();
//
//    public Map<Engine,String> getEngines();
}
