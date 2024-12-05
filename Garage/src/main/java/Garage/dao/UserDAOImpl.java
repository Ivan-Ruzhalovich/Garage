package Garage.dao;

import Garage.Classes.UserAccountInfo;
import Garage.entity.Authorities;
import Garage.entity.SecurityUsers;
import Garage.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void saveUser(User user, Authorities authorities) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user.getUserNameAuth());
        session.saveOrUpdate(authorities);
        session.saveOrUpdate(user);

    }

    @Override
    public User getUser(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        Authorities authorities = session.createQuery("from Authorities where username = :username",Authorities.class)
                .setParameter("username",user.getUserNameAuth().getUsername()).getSingleResult();
        SecurityUsers securityUsers = session.createQuery("from SecurityUsers where username = :username",SecurityUsers.class)
                .setParameter("username",user.getUserNameAuth().getUsername()).getSingleResult();
        session.delete(user);
        session.delete(authorities);
        session.delete(securityUsers);
    }

    @Override
    public UserAccountInfo getAccountInformation(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return new UserAccountInfo(id, user.getName(),
                user.getSurname(),
                user.getUserNameAuth().getUsername(),
                user.getUserNameAuth().getPassword(),
                user.getUserNameAuth().getEnabled(),
                user.getPhoneNumber());
    }

    @Override
    public long auth(String login, String password, BCryptPasswordEncoder bCryptPasswordEncoder) {
        User user;
        try {
            user = sessionFactory.getCurrentSession().createQuery("from User where userNameAuth.username = :login"
                    , User.class).setParameter("login", login).getSingleResult();
        }
        catch (NoResultException e){
            return -1;
        }
        if (bCryptPasswordEncoder.matches(password, user.getUserNameAuth().getPassword())) {
            if (user.getUserNameAuth().getEnabled() == 1) {
                return user.getId();
            } else return 0;
        } else {
            return -1;
        }
    }

    @Override
    public boolean auth(String login) {
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User where userNameAuth.username = :login"
                , User.class).setParameter("login", login).getResultList();
        return users.isEmpty();
    }

    @Override
    public User getUser(String code) {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createQuery("from User where activationCode = :code",User.class)
                    .setParameter("code",code).getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    @Override
    public long activateUserAccount(String code) {
        try{
        User user = getUser(code);
        user.getUserNameAuth().setEnabled(1);
        user.setActivationCode(null);
        return user.getId();
        }
        catch (Exception e)
        {
            return -1;
        }
    }
}