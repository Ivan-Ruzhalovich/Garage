package Garage.dao;

import Garage.entity.tech.TechCar;
import Garage.entity.tech.Works;

import java.util.List;
import java.util.Map;

public interface WorksDAO {

    public Map<Integer,String> getAllWorks();

    public Works getWork(int id);

    public void deleteWork(long workId);

    public List<TechCar> getAllCarTech(long carId);
}
