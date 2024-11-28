package Garage.dao;

import Garage.Classes.UserAccountInfo;
import Garage.entity.Authorities;
import Garage.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public void saveUser(User user,Authorities authorities) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        session.saveOrUpdate(authorities);
    }

    @Override
    public User getUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class,id);
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(User.class,id));
    }

    @Override
    public void saveSecurityOfNewUser(Authorities authorities) {
        Session session = sessionFactory.getCurrentSession();
        session.save(authorities);
    }

    @Override
    public UserAccountInfo getAccountInformation(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class,id);
        return new UserAccountInfo(id, user.getName(),
                user.getSurname(),
                user.getUserNameAuth().getUsername(),
                user.getUserNameAuth().getPassword(),
                user.getUserNameAuth().getEnabled(),
                user.getPhoneNumber());
    }

    @Override
    public long auth(String login, String password, BCryptPasswordEncoder bCryptPasswordEncoder) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User where userNameAuth.username = :login"
                        ,User.class).setParameter("login",login).getResultList();
        if (users.isEmpty()) {
            return -1;}
        User user = users.get(0);
        if (bCryptPasswordEncoder.matches(password,user.getUserNameAuth().getPassword())) {
            if (user.getUserNameAuth().getEnabled()==1){
                return user.getId();}
            else return 0;}
        else {
            return -1;}
    }
}