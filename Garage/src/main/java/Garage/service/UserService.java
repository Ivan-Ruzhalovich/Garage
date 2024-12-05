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

    public User getUser(String code);

    public void deleteUser(long id);

    public void saveCar(Car car);

    public List<Car> getAllCars();

    public Car getCar(long id);

    public List<Car> getCarList(long id);

    public void deleteCar(long id);

    public UserAccountInfo getAccountInformation(long id);

    public Map<Integer,String> getAllWorks();

    public Works getWork(int id);

    public long auth(String login, String password, BCryptPasswordEncoder bCryptPasswordEncoder);

    public boolean auth(String login);

    public long activateUserAccount(String code);
}
