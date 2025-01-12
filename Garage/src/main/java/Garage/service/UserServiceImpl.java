package Garage.service;

import Garage.Classes.UserAccountInfo;
import Garage.dao.CarDAO;
import Garage.dao.RefuelDAO;
import Garage.dao.UserDAO;
import Garage.dao.WorksDAO;
import Garage.entity.*;
import Garage.entity.tech.Refuel;
import Garage.entity.tech.TechCar;
import Garage.entity.tech.Works;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service

public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private WorksDAO worksDAO;

    @Autowired
    private RefuelDAO refuelDAO;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public void saveUser(User user,Authorities authorities) {
        userDAO.saveUser(user,authorities);
    }

    @Override
    @Transactional
    public User getUser(long id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public void saveCar(Car car) {
        carDAO.saveCar(car);
    }

    @Override
    @Transactional
    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    @Override
    @Transactional
    public Car getCar(long id) {
        return carDAO.getCar(id);
    }

    @Override
    @Transactional
    public List<Car> getCarList(long id) {
        return carDAO.getCarList(id);
    }

    @Override
    @Transactional
    public void deleteCar(long id) {
        carDAO.deleteCar(id);
    }


    @Override
    @Transactional
    public UserAccountInfo getAccountInformation(long id) {
        return userDAO.getAccountInformation(id);
    }

    @Override
    @Transactional
    public Map<Integer,String> getAllWorks() {
        return worksDAO.getAllWorks();
    }

    @Override
    @Transactional
    public Works getWork(int id) {
        return worksDAO.getWork(id);
    }

    @Override
    @Transactional
    public long auth(String login, String password, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return userDAO.auth(login,password,bCryptPasswordEncoder);
    }

    @Override
    @Transactional
    public boolean auth(String login) {
        return userDAO.auth(login);
    }

    @Override
    @Transactional
    public User getUser(String code) {
        return userDAO.getUser(code);
    }

    @Override
    @Transactional
    public long activateUserAccount(String code) {
        return userDAO.activateUserAccount(code);
    }
}
