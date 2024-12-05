package Garage.Classes;

import Garage.entity.Car;

import java.util.stream.Collectors;


public class TryTechKThread implements Runnable{
    private Car car;
    private int idWork;
    private int kilometrage;
    private int result;

    public TryTechKThread(Car car, int idWork, int kilometrage) {
        this.car = car;
        this.idWork = idWork;
        this.kilometrage = kilometrage;
    }

    @Override
    public void run() {
        int resultK = car.getTechs().stream()
                .filter(e -> (e.getWork().getId() == idWork))
                .sorted((x, y) -> ((x.getKilometrage() - y.getKilometrage()) * (-1)))
                .collect(Collectors.toList()).get(0).getKilometrage();
        this.result = ((resultK + kilometrage) - car.getKilometrage());
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

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
