package Garage.dao;


import Garage.entity.Car;
import Garage.entity.tech.Refuel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RefuelDAOImpl implements RefuelDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Refuel> getAllCarRefuel(long carId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Refuel where carId = :carId order by date desc ",Refuel.class)
                .setParameter("carId",carId).getResultList();
    }

    @Override
    public void deleteRefuel(long refuelId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Refuel.class,refuelId));
    }

    @Override
    public void deleteAllRefuelForCar(long carId) {
        sessionFactory.getCurrentSession().createQuery("delete from Refuel where carId = :carId")
                .setParameter("carId",carId);
    }
}
