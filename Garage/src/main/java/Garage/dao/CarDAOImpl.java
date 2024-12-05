package Garage.dao;

import Garage.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CarDAOImpl implements CarDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCar(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(car);
    }

    @Override
    public List<Car> getAllCars() {
        return sessionFactory.getCurrentSession().createQuery("from Car",Car.class).getResultList();
    }

    @Override
    public Car getCar(long id) {
        return sessionFactory.getCurrentSession().get(Car.class,id);
    }

    @Override
    public List<Car> getCarList(long userId) {
        return sessionFactory.getCurrentSession().createQuery("from Car where userId = :userId ",Car.class)
                .setParameter("userId",userId).getResultList();
    }

    @Override
    public void deleteCar(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Car.class,id));
    }
}
