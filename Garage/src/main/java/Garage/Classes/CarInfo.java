package Garage.Classes;

import Garage.entity.Car;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.time.LocalDate.parse;

@Component
public class CarInfo {

    private Car car;

    private Map<String, Integer> maintenanceRecommendations;

    private float averageFuelConsumption; //ср расход за все время

    private float averageFuelConsumptionOfYear; //ср расход за год

    private float averagePriceOfKilometer;//ср цена километра за все время

    private float averagePriceOfKilometerOfYear;//ср цена километра за год

    private TryTechDaysThread tryTechDaysThread;

    private TryTechKThread tryTechKThread;

    private ExecutorService executorService;

    //getters and setters-----------------------------------------------

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

    public TryTechDaysThread getTryTechDaysThread() {
        return tryTechDaysThread;
    }

    public void setTryTechDaysThread(TryTechDaysThread tryTechDaysThread) {
        this.tryTechDaysThread = tryTechDaysThread;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
//                "car=" + car +
//                ", maintenanceRecommendations=" + maintenanceRecommendations +
                ", averageFuelConsumption=" + averageFuelConsumption +
                ", averageFuelConsumptionOfYear=" + averageFuelConsumptionOfYear +
                ", averagePriceOfKilometer=" + averagePriceOfKilometer +
                ", averagePriceOfKilometerOfYear=" + averagePriceOfKilometerOfYear +
                ", tryTechDaysThread=" + tryTechDaysThread +
                ", tryTechKThread=" + tryTechKThread +
                '}';
    }

    public TryTechKThread getTryTechKThread() {
        return tryTechKThread;
    }

    public void setTryTechKThread(TryTechKThread tryTechKThread) {
        this.tryTechKThread = tryTechKThread;
    }

    public Map<String, Integer> getMaintenanceRecommendations() {
        return maintenanceRecommendations;
    }

    public void setMaintenanceRecommendations(Map<String, Integer> maintenanceRecommendations) {
        this.maintenanceRecommendations = maintenanceRecommendations;
    }

    public float createAverageFuelConsumptionOfYear(Car car) {
        if (getTotalFuelVolumeOfYear(car) != 0.0F) {
//            System.out.println("getTotalFuelVolumeOfYear(car) = " + getTotalFuelVolumeOfYear(car));
            return (float) (getTotalFuelVolumeOfYear(car)
                    / (getTotalRefuelKilometrageOfYear(car) * 0.01));
        }
        else return 0.0F;
    }

    public void setAverageFuelConsumptionOfYear(float averageFuelConsumptionOfYear) {
        this.averageFuelConsumptionOfYear = averageFuelConsumptionOfYear;
    }

    public float createAveragePriceOfKilometerOfYear(Car car) {
        float totalPriceOfCarOfYear = getRefuelsTotalPriceOfYear(car) + getTechsTotalPriceOfYear(car);
        return totalPriceOfCarOfYear / getTotalKilometrageOfYear(car);
    }

    public void setAveragePriceOfKilometerOfYear(float averagePriceOfKilometerOfYear) {
        this.averagePriceOfKilometerOfYear = averagePriceOfKilometerOfYear;
    }

    private float createAveragePriceOfKilometer(Car car) {
        float totalPriceOfCar = getRefuelsTotalPrice(car) + getTechsTotalPrice(car);
        return totalPriceOfCar / getTotalKilometrage(car);
    }

    public float getAveragePriceOfKilometer() {
        return averagePriceOfKilometer;
    }

    public void setAveragePriceOfKilometer(float averagePriceOfKilometer) {
        this.averagePriceOfKilometer = averagePriceOfKilometer;
    }

    private float createAverageFuelConsumption(Car car) {
        if (getTotalFuelVolume(car) != 0.0F) {
//            System.out.println("getTotalFuelVolume(car) = " + getTotalFuelVolume(car));
            return (float) (getTotalFuelVolume(car)
                    / ((Collections.max(getRefuelKilometrageList(car)) - Collections.min(getRefuelKilometrageList(car))) * 0.01));
        }
        else return 0.0F;
    }

    public float getAverageFuelConsumptionOfYear() {
        return averageFuelConsumptionOfYear;
    }

    public float getAveragePriceOfKilometerOfYear() {
        return averagePriceOfKilometerOfYear;
    }

    //getters and setters-----------------------------------------------
    //constructors-----------------------------------------------

    public CarInfo() {
        maintenanceRecommendations = new HashMap<>();
    }

    public CarInfo(Car car) {
        this.car = car;
        this.executorService = Executors.newFixedThreadPool(5);
    }

    //Constructors-----------------------------------------------
    //Methods-----------------------------------------------

    public CarInfo getCarInfo(){
        maintenanceRecommendations = getRecomendations(car);
        executorService.execute(()
                -> setAverageFuelConsumptionOfYear(createAverageFuelConsumptionOfYear(this.car)));
        executorService.execute(()
                -> setAveragePriceOfKilometerOfYear(createAveragePriceOfKilometerOfYear(this.car)));
        executorService.execute(()
                -> setAverageFuelConsumption(createAverageFuelConsumption(this.car)));
        executorService.execute(()
                -> setAveragePriceOfKilometer(createAveragePriceOfKilometer(this.car)));
        try {
            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("TIMEOUT!");
            throw new RuntimeException(e);
        }
        if (maintenanceRecommendations.isEmpty()) {
            maintenanceRecommendations.put("Ваша машина обслужена.", 0);
        }
        return this;
    }

    private Map<String, Integer> getRecomendations(Car car) {
        Map<String, Integer> rec = new HashMap<>();
        if (car.getTechs().isEmpty()) {
            rec.put("Не внесено данных о техническом обслуживании.", 99);
            return rec;
        }
        //**************MOTOR OIL******************//
        try {
            tryTechKThread = new TryTechKThread(car, 2, 7500);
            tryTechDaysThread = new TryTechDaysThread(car, 2, 365);
            tryTechKThread.run();
            tryTechDaysThread.run();
            int tryTechK = tryTechKThread.getResult();
            long tryTechDays = tryTechDaysThread.getResult();
            if (tryTechK < 0)
                rec.put("Последняя замена моторного масла была более 7500 км назад. " +
                        "Желательно заменить моторное масло и маслянный фильтр.", 1);
            else if (tryTechDays < 0)
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
            tryTechKThread = new TryTechKThread(car, 24, 60000);
            tryTechDaysThread = new TryTechDaysThread(car, 24, 1825);
            tryTechKThread.run();
            tryTechDaysThread.run();
            int tryTechK = tryTechKThread.getResult();
            long tryTechDays = tryTechDaysThread.getResult();
            if (tryTechK < 0)
                rec.put("Рекомендуется обслуживание коробки передач со сменой масла," +
                        " фильтра и прокладки.", 1);
            else if (tryTechDays < 0)
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
            tryTechKThread = new TryTechKThread(car, 25, 40000);
            tryTechDaysThread = new TryTechDaysThread(car, 25, 1825);
            tryTechKThread.run();
            tryTechDaysThread.run();
            int tryTechK = tryTechKThread.getResult();
            long tryTechDays = tryTechDaysThread.getResult();
            if (tryTechK < 0)
                rec.put("Рекомендуется обслуживание системы полного привода со" +
                        " сменой масла и фильтра.", 1);
            else if (tryTechDays < 0) {
                rec.put("Последнее обслуживание полного привода было более 5 лет назад!" +
                        "Рекомендуется обслуживание коробки передач со сменой масла," +
                        " фильтра и прокладки.", 1);
            } else rec.put("Обслуживание системы полного привода через " + tryTechK +
                    " км или " + tryTechDays + " дней.", 0);
        } catch (IndexOutOfBoundsException e) {
            rec.put("Недостаточно данных для формирования рекомендаций " +
                    "по обслуживанию системы полного привода", 99);
        }
        //**************FULL RETAIN******************//
        //**************BRAKE OIL******************//
        try {
            tryTechKThread = new TryTechKThread(car, 26, 60000);
            tryTechDaysThread = new TryTechDaysThread(car, 26, 1095);
            tryTechKThread.run();
            tryTechDaysThread.run();
            int tryTechK = tryTechKThread.getResult();
            long tryTechDays = tryTechDaysThread.getResult();
            if (tryTechK < 0)
                rec.put("Рекомендуется замена тормозной жидкости. Внимание, тормозная жидкость " +
                        "прошла более 30000 км! Это не безопасно!.", 1);
            else if (tryTechDays < 0)
                rec.put("Рекомендуется замена тормозной жидкости. Внимание, тормозная жидкость " +
                        "не менялась более 3-х лет! Это не безопасно!", 1);
            else rec.put("Обслуживание тормозной системы через " + tryTechK +
                        " км или " + tryTechDays + " дней.", 0);
        } catch (IndexOutOfBoundsException e) {
            rec.put("Недостаточно данных для формирования рекомендаций " +
                    "по обслуживанию тормозной системы", 99);
        }
        //**************BRAKE OIL******************//
        //**************FREEZER******************//
        try {
            tryTechKThread = new TryTechKThread(car, 27, 60000);
            tryTechDaysThread = new TryTechDaysThread(car, 27, 1825);
            tryTechKThread.run();
            tryTechDaysThread.run();
            int tryTechK = tryTechKThread.getResult();
            long tryTechDays = tryTechDaysThread.getResult();
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
            tryTechKThread = new TryTechKThread(car, 3, 30000);
            tryTechDaysThread = new TryTechDaysThread(car, 3, 730);
            tryTechKThread.run();
            tryTechDaysThread.run();
            int tryTechK = tryTechKThread.getResult();
            long tryTechDays = tryTechDaysThread.getResult();
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
            tryTechKThread = new TryTechKThread(car, 5, 60000);
            tryTechDaysThread = new TryTechDaysThread(car, 5, 1825);
            tryTechKThread.run();
            tryTechDaysThread.run();
            int tryTechK = tryTechKThread.getResult();
            long tryTechDays = tryTechDaysThread.getResult();
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

    private List<Integer> getRefuelKilometrageList(Car car) {
        return car.getRefuels().stream().map(e -> e.getKilometrage())
                .collect(Collectors.toList());
    }
    private List<Integer> getRefuelKilometrageListOfYear(Car car) {
        return car.getRefuels().stream()
                .filter(e -> LocalDate.parse(e.getDate()).getYear()==LocalDate.now().getYear())
                .map(e -> e.getKilometrage())
                .collect(Collectors.toList());
    }

    private int getTotalRefuelKilometrageOfYear(Car car){
        if (!car.getRefuels().isEmpty()) {
            List<Integer> refuelList = car.getRefuels().stream()
                    .filter((e) -> parse(e.getDate()).getYear() == LocalDate.now().getYear())
                    .map(e -> e.getKilometrage()).collect(Collectors.toList());
            return Collections.max(refuelList) - Collections.min(refuelList);
        }
        return 1;
    }

    private int getTotalKilometrageOfYear(Car car) {
        int min = 0;
        if (!car.getRefuels().isEmpty()) {
            List<Integer> refuelList = car.getRefuels().stream()
                    .filter((e) -> parse(e.getDate()).getYear() == LocalDate.now().getYear())
                    .map(e -> e.getKilometrage()).collect(Collectors.toList());
            min = Collections.min(refuelList);
        }
        if (!car.getTechs().isEmpty()) {
            List<Integer> techsList = car.getTechs().stream()
                    .filter(e -> LocalDate.parse(e.getDate()).getYear() == LocalDate.now().getYear())
                    .map(e -> e.getKilometrage()).collect(Collectors.toList());
            min = min == 0 ? Collections.min(techsList) : Collections.min(techsList) < min ? Collections.min(techsList) : min;
        }
        return min==0?1:(car.getKilometrage() - min);
    }

    private List<Integer> getTechsKilometrageList(Car car) {
        return car.getTechs().stream().map(e -> e.getKilometrage())
                .collect(Collectors.toList());
    }

    private int getTotalKilometrage(Car car) {
        int min = 0;
        if (!car.getRefuels().isEmpty()) {
            List<Integer> refuelList = car.getRefuels().stream().map(e -> e.getKilometrage())
                    .collect(Collectors.toList());
            min = Collections.min(refuelList);
        }
        if (!car.getTechs().isEmpty()) {
            List<Integer> techsList = car.getTechs().stream().map(e -> e.getKilometrage())
                    .collect(Collectors.toList());
            min = min == 0 ? Collections.min(techsList) : Collections.min(techsList) < min ? Collections.min(techsList) : min;
        }
        return min==0?1:(car.getKilometrage() - min);
    }


    private Float getTotalFuelVolume(Car car) {
        Optional<Float> o = car.getRefuels().stream()
                .filter(e -> e.getKilometrage() != Collections.min(getRefuelKilometrageList(car)))
                .map(e -> e.getVolume()).reduce((e1, e2) -> e1 + e2);
        return o.orElse(0.0F);
    }

    private Float getTotalFuelVolumeOfYear(Car car) {
        Optional<Float> o = car.getRefuels().stream()
                .filter(e -> e.getKilometrage() != Collections.min(getRefuelKilometrageListOfYear(car)))
                .filter((e) -> parse(e.getDate()).getYear() == LocalDate.now().getYear())
                .map(e -> e.getVolume()).reduce((e1, e2) -> e1 + e2);
        return o.orElse(0.0F);
    }

    private Integer getTechsTotalPrice(Car car) {
        Optional<Integer> techsTotalPrice = car.getTechs().stream().map(e -> e.getPrice())
                .reduce((e1, e2) -> e1 + e2);
        return techsTotalPrice.orElse(0);
    }

    private Float getRefuelsTotalPrice(Car car) {
        Optional<Float> refuelsTotalPrice = car.getRefuels().stream().map(e -> e.getPrice() * e.getVolume())
                .reduce((e1, e2) -> e1 + e2);
        return refuelsTotalPrice.orElse(0.0F);
    }

    private Integer getTechsTotalPriceOfYear(Car car) {
        Optional<Integer> techsTotalPrice = car.getTechs().stream()
                .filter(e -> LocalDate.parse(e.getDate()).getYear() == LocalDate.now().getYear())
                .map(e -> e.getPrice())
                .reduce((e1, e2) -> e1 + e2);
        return techsTotalPrice.orElse(0);
    }

    private Float getRefuelsTotalPriceOfYear(Car car) {
        Optional<Float> refuelsTotalPrice = car.getRefuels().stream()
                .filter(e -> LocalDate.parse(e.getDate()).getYear() == LocalDate.now().getYear())
                .map(e -> e.getPrice() * e.getVolume())
                .reduce((e1, e2) -> e1 + e2);
        return refuelsTotalPrice.orElse(0.0F);
    }
}