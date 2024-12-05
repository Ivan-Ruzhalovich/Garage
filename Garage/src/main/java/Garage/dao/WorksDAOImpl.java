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
        return sessionFactory.getCurrentSession().createQuery("from Works",Works.class)
                .getResultList().stream()
                .collect(Collectors.toMap(Works::getId,Works::getDescription));
    }

    @Override
    public Works getWork(int id) {
        return sessionFactory.getCurrentSession().get(Works.class,id);
    }

    @Override
    public void deleteWork(long workId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(TechCar.class,workId));
    }

    @Override
    public List<TechCar> getAllCarTech(long carId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from TechCar where carId = :carId order by date desc ", TechCar.class)
                .setParameter("carId", carId).getResultList();
    }
}
