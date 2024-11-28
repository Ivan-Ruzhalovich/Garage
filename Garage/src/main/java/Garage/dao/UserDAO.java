package Garage.dao;

import Garage.Classes.UserAccountInfo;
import Garage.entity.Authorities;
import Garage.entity.Car;
import Garage.entity.SecurityUsers;
import Garage.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
    public void saveUser(User user);
    public void saveUser(User user,Authorities authorities);
    public User getUser(long id);
    public void deleteUser(long id);
    public void saveSecurityOfNewUser(Authorities authorities);
    public UserAccountInfo getAccountInformation(long id);

    public long auth(String login, String password, BCryptPasswordEncoder bCryptPasswordEncoder);
}
