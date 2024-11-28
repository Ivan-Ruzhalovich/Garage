package Garage.dao;

import Garage.entity.Car;
import Garage.entity.tech.TechCar;
import Garage.entity.tech.Works;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class WorksDAOImpl implements WorksDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Map<Integer,String> getAllWorks() {
        return sessionFactory.getCurrentSession().createQuery("from Works",Works.class).
                getResultList().stream()
                .collect(Collectors.toMap(Works::getId,Works::getDescription));
    }

    @Override
    public void saveTech(TechCar techCar) {
        Session session = sessionFactory.getCurrentSession();
        Car car = session.get(Car.class,techCar.getCarId());
        if (techCar.getKilometrage() > car.getKilometrage())
            car.setKilometrage(techCar.getKilometrage());
        car.addTechToListTech(techCar);
        session.saveOrUpdate(car);
    }

    @Override
    public Works getWork(int id) {
        return sessionFactory.getCurrentSession().get(Works.class,id);
    }

    @Override
    public List<TechCar> getAllTechForCar(long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from TechCar where carId = :id",TechCar.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public void deleteWork(long workId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(TechCar.class,workId));
    }

    @Override
    public List<TechCar> getAllCarTech(long carId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from TechCar where carId = :carId", TechCar.class)
                .setParameter("carId", carId).getResultList();
    }
}
