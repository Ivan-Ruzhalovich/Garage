package Garage.Classes;

import Garage.entity.Car;
import Garage.entity.tech.Refuel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;


@Component
public class CarInfo {
    private Car car;
    private Map<String, Integer> maintenanceRecommendations;
    private float averageFuelConsumption;
    private float averagePriceOfKilometer;
    //1 - замена моторного масла(масло, фильтр)
    //2 - замена воздушного фильтра двигателя
    //3 - обслуживание АКПП (Масло, фильтр, прокладка)
    //4 - обслуживание полного привода(Масло, фильтр)
    //5 - замена тормозной жидкости
    //6 - замена антифриза
    //7 - замена свечей зажигания

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public float getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    public void setAverageFuelConsumption(float averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public Map<String, Integer> getMaintenanceRecommendations() {
        return maintenanceRecommendations;
    }

    public void setMaintenanceRecommendations(Map<String, Integer> maintenanceRecommendations) {
        this.maintenanceRecommendations = maintenanceRecommendations;
    }

    public CarInfo() {
        maintenanceRecommendations = new HashMap<>();
    }

    public CarInfo(Car car) {
        this.car = car;
        maintenanceRecommendations = getRecomendations(car);
        tryDaysToOld(2,3);
        averageFuelConsumption = getAverageFuelConsumption(car);
        averagePriceOfKilometer = getAveragePriceOfKilometer(car);
        if (maintenanceRecommendations.isEmpty()) {
            maintenanceRecommendations.put("Ваша машина обслужена.", 0);
        }
    }

    private float getAveragePriceOfKilometer(Car car) {
        float totalPriceOfCar = getRefuelsTotalPrice(car) + getTechsTotalPrice(car);
        return totalPriceOfCar/getTotalKilometrage(car);
    }

    public float getAveragePriceOfKilometer() {
        return averagePriceOfKilometer;
    }

    public void setAveragePriceOfKilometer(float averagePriceOfKilometer) {
        this.averagePriceOfKilometer = averagePriceOfKilometer;
    }

    private float getAverageFuelConsumption(Car car) {
        if (getTotalFuelVolume(car) != 0.0F)
            return ((Collections.max(getKilometrageList(car))
                    - Collections.min(getKilometrageList(car))) / getTotalFuelVolume(car));
        else return 0.0F;
    }

    private Map<String, Integer> getRecomendations(Car car) {
        Map<String, Integer> rec = new HashMap<>();
        if (car.getTechs().isEmpty()) {
            rec.put("Не внесено данных о техническом обслуживании.", 99);
            return rec;
        }
        //**************MOTOR OIL******************//
        try {
            int tryTechK = tryTech(2, 7500);
            long tryTechDays = tryDaysToOld(2,365);
            if (tryTechK<0)
                rec.put("Последняя замена моторного масла была более 7500 км назад. " +
                        "Желательно заменить моторное масло и маслянный фильтр.", 1);
            else if(tryTechDays<0)
                rec.put("Последняя замена моторного масла была более года назад. " +
                        "Желательно заменить моторное масло и маслянный фильтр.", 1);
            else rec.put("Замена моторного масла и фильтра через " + tryTechK + " км" +
                        " или " + tryTechDays + " дней.", 0);
        } catch (IndexOutOfBoundsException ignored) {
            rec.put("Чтобы получать рекомендации по срокам замены моторного масла " +
                    "внесите данные по последней замене моторного масла", 99);
        }
        //**************MOTOR OIL******************//
        //**************TRANSMISSION******************//
        try {
            int tryTechK = tryTech(24, 60000);
            long tryTechDays = tryDaysToOld(24,1825);
            if (tryTechK < 0)
                rec.put("Рекомендуется обслуживание коробки передач со сменой масла," +
                        " фильтра и прокладки.", 1);
            else if (tryTechDays<0)
                rec.put("Последнее обслуживание коробки передач было более 5 лет назад!" +
                        "Рекомендуется обслуживание коробки передач со сменой масла," +
                        " фильтра и прокладки.", 1);
            else rec.put("Обслуживание коробки передач через " + tryTechK + " км или " + tryTechDays + " дней.", 0);
        } catch (IndexOutOfBoundsException e) {
            rec.put("Недостаточно данных для формирования рекомендаций по обслуживанию КПП", 99);
        }
        //**************TRANSMISSION******************//
        //**************FULL RETAIN******************//
        try {
            int tryTechK = tryTech(25, 40000);
            long tryTechDays = tryDaysToOld(25,1825);
            if (tryTechK < 0)
                rec.put("Рекомендуется обслуживание системы полного привода со" +
                        " сменой масла и фильтра.", 1);
            else if (tryTechDays<0) {
                rec.put("Последнее обслуживание полного привода было более 5 лет назад!" +
                        "Рекомендуется обслуживание коробки передач со сменой масла," +
                        " фильтра и прокладки.", 1);
            }
            else rec.put("Обслуживание системы полного привода через " + tryTechK +
                        " км или " + tryTechDays + " дней.", 0);
        } catch (IndexOutOfBoundsException e) {
            rec.put("Недостаточно данных для формирования рекомендаций " +
                    "по обслуживанию системы полного привода", 99);
        }
        //**************FULL RETAIN******************//
        //**************BRAKE OIL******************//
        try {
            int tryTechK = tryTech(26, 6000);
            long tryTechDays = tryDaysToOld(26,1095);
            if (tryTechK < 0)
                rec.put("Рекомендуется замена тормозной жидкости. Внимание, тормозная жидкость " +
                        "прошла более 30000 км! Это не безопасно!.", 1);
            else if (tryTechDays<0)
                rec.put("Рекомендуется замена тормозной жидкости. Внимание, тормозная жидкость " +
                        "не менялась более 3-х лет! Это не безопасно!.", 1);
            else rec.put("Обслуживание тормозной системы через " + tryTechK +
                        " км или " + tryTechDays + " дней.", 0);
        } catch (IndexOutOfBoundsException e) {
            rec.put("Недостаточно данных для формирования рекомендаций " +
                    "по обслуживанию тормозной системы", 99);
        }
        //**************BRAKE OIL******************//
        //**************FREEZER******************//
        try {
            int tryTechK = tryTech(27, 60000);
            long tryTechDays = tryDaysToOld(27,1825);
            if (tryTechK < 0)
                rec.put("Рекомендуется замена охлаждающей жидкости (антифриз).", 1);
            else if (tryTechDays < 0) {
                rec.put("Рекомендуется замена охлаждающей жидкости (антифриз).", 1);
            } else rec.put("Замена охлаждающей жидкости через " + tryTechK +
                    " км или " + tryTechDays + " дней", 0);
        } catch (IndexOutOfBoundsException e) {
            rec.put("Недостаточно данных для формирования рекомендаций " +
                    "по обслуживанию системы охлаждения двигателя", 99);
        }
        //**************FREEZER******************//
        //**************AIR FILTER******************//
        try {
            int tryTechK = tryTech(3, 30000);
            long tryTechDays = tryDaysToOld(3,730);
            if (tryTechK < 0)
                rec.put("Рекомендуется замена воздушного фильтра двигателя.", 1);
            else if (tryTechDays < 0)
                rec.put("Рекомендуется замена воздушного фильтра двигателя.", 1);
             else rec.put("Замена воздушного фильтра двигателя через " + tryTechK +
                        " км или " + tryTechDays + " дней.", 0);
        } catch (IndexOutOfBoundsException e) {
            rec.put("Недостаточно данных для формирования рекомендаций " +
                    "по интервалу замены воздушного фильтра двигателя", 99);
        }
        //**************AIR FILTER******************//
        //**************SVECHI******************//
        try {
            int tryTechK = tryTech(5, 60000);
            long tryTechDays = tryDaysToOld(5,1825);
            if (tryTechK < 0)
                rec.put("Рекомендуется замена свечей зажигания.", 1);
            else if (tryTechDays < 0)
                rec.put("Рекомендуется замена свечей зажигания.", 1);
            else rec.put("Замена свечей зажигания через " + tryTechK +
                        " км или " + tryTechDays + " дней.", 0);
        } catch (IndexOutOfBoundsException e) {
            rec.put("Недостаточно данных для формирования рекомендаций " +
                    "по интервалу замены свечей зажигания", 99);
        }
        //**************SVECHI******************//
        return rec;
    }

    private int tryTech(int idWork, int kilometrage) {
        int result = car.getTechs().stream()
                .filter(e -> (e.getWork().getId() == idWork))
                .sorted((x, y) -> ((x.getKilometrage() - y.getKilometrage()) * (-1)))
                .collect(Collectors.toList()).get(0).getKilometrage();
        return ((result + kilometrage) - car.getKilometrage());

    }

    private long tryDaysToOld(int idWork, int days){
        List<LocalDate> dates = car.getTechs().stream().filter(e->e.getWork().getId()==idWork).
                map(e->e.getDate()).map(e->LocalDate.parse(e)).sorted().collect(Collectors.toList());
        LocalDate now = LocalDate.now();
        LocalDate itsTimeToService = dates.get(dates.size()-1).plusDays(days);
        if (now.isAfter(itsTimeToService))
            return -1;
        return DAYS.between(now,itsTimeToService);
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "car=" + car +
                ", maintenanceRecommendations=" + maintenanceRecommendations +
                '}';
    }

    private List<Integer> getKilometrageList(Car car) {
        return car.getRefuels().stream().map(e -> e.getKilometrage())
                .collect(Collectors.toList());
    }

    private int getTotalKilometrage(Car car){
        if (car.getRefuels().isEmpty()||car.getTechs().isEmpty())
            return car.getKilometrage();
        else {
        List<Integer> refuelList = car.getRefuels().stream().map(e->e.getKilometrage())
                .collect(Collectors.toList());
        List<Integer> techsList = car.getTechs().stream().map(e->e.getKilometrage())
                .collect(Collectors.toList());
        int min = Collections.min(refuelList);
        if (Collections.min(techsList) < min)
            min = Collections.min(techsList);
        return (car.getKilometrage() - min);
        }

    }

    private Float getTotalFuelVolume(Car car) {
        Optional<Float> o = car.getRefuels().stream().
                filter(e -> e.getKilometrage() != Collections.min(getKilometrageList(car))).
                map(e -> e.getVolume()).reduce((e1, e2) -> e1 + e2);
        if (o.isPresent())
            return o.get();
        else return 0.0F;
    }

    private Integer getTechsTotalPrice(Car car) {
        Optional<Integer> techsTotalPrice = car.getTechs().stream().map(e -> e.getPrice())
                .reduce((e1, e2) -> e1 + e2);
        if (techsTotalPrice.isPresent())
            return techsTotalPrice.get();
        else return 0;
    }

    private Float getRefuelsTotalPrice(Car car) {
        Optional<Float> refuelsTotalPrice = car.getRefuels().stream().map(e -> e.getPrice()).reduce((e1, e2) -> e1 + e2);
        if (refuelsTotalPrice.isPresent())
            return refuelsTotalPrice.get();
        else return 0.0F;
    }
}