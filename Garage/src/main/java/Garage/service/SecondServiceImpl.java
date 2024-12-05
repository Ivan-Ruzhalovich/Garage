package Garage.service;


import Garage.dao.RefuelDAO;
import Garage.dao.WorksDAO;
import Garage.entity.tech.Refuel;
import Garage.entity.tech.TechCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SecondServiceImpl implements SecondService{

    @Autowired
    RefuelDAO refuelDAO;

    @Autowired
    WorksDAO worksDAO;

    @Override
    @Transactional
    public List<Refuel> getAllCarRefuel(long carId) {
        return refuelDAO.getAllCarRefuel(carId);
    }

    @Override
    @Transactional
    public void deleteRefuel(long refuelId) {
        refuelDAO.deleteRefuel(refuelId);
    }

    @Override
    @Transactional
    public void deleteWork(long workId) {
        worksDAO.deleteWork(workId);
    }

    @Override
    @Transactional
    public List<TechCar> getAllCarTech(long carId) {
        return worksDAO.getAllCarTech(carId);
    }
}
