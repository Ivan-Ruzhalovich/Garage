package Garage.Classes;

import Garage.entity.Car;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;


public class TryTechDaysThread implements Runnable{
    private Car car;
    private int idWork;
    private int days;
    private long result;

    public TryTechDaysThread(Car car, int idWork, int days) {
        this.car = car;
        this.idWork = idWork;
        this.days = days;
    }

    @Override
    public void run() {
        List<LocalDate> dates = car.getTechs().stream().filter(e->e.getWork().getId()==idWork).
                map(e->e.getDate()).map(e->LocalDate.parse(e)).sorted().collect(Collectors.toList());
        LocalDate now = LocalDate.now();
        LocalDate itsTimeToService = dates.get(dates.size()-1).plusDays(days);
        if (now.isAfter(itsTimeToService))
            this.result = -1;
        this.result =  DAYS.between(now,itsTimeToService);

    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getIdWork() {
        return idWork;
    }

    public void setIdWork(int idWork) {
        this.idWork = idWork;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }
}
