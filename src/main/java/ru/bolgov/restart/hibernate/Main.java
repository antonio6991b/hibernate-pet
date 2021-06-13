package ru.bolgov.restart.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bolgov.restart.hibernate.dao.CarDao;
import ru.bolgov.restart.hibernate.dao.CarDaoException;
import ru.bolgov.restart.hibernate.pojo.Car;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args){

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        CarDao carDao = new CarDao(sessionFactory);

        carDao.addCar(new Car("Mercedes", "Anton", Date.valueOf("2019-05-21"), 10000));
        carDao.addCar(new Car("Honda", "Ivan", Date.valueOf("2015-10-01"), 150000));
        carDao.addCar(new Car("Lada Vesta", "Vladimir", Date.valueOf("2017-10-10"), 120000));
        carDao.addCar(new Car("Datsun", "Evgeni", Date.valueOf("2020-03-25"), 3000));
        carDao.addCar(new Car("Opel", "Ksenia", Date.valueOf("2012-11-03"), 180000));
        carDao.addCar(new Car("Volkswagen", "Anna", Date.valueOf("2005-07-27"), 250000));
        carDao.addCar(new Car("Kia", "Sergey", Date.valueOf("2018-09-24"), 80000));

        List<Car> cars = null;
        try {
            cars = carDao.getAllCars();
        } catch (CarDaoException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        System.out.println(cars);

        try {
            System.out.println(carDao.getCar(2));
        } catch (CarDaoException e) {
            e.printStackTrace();
        }

        carDao.updateOwner(3, "Boris");
        try {
            System.out.println(carDao.getCar(3));
        } catch (CarDaoException e) {
            e.printStackTrace();
        }

        carDao.removeCar(6);
        try {
            System.out.println(carDao.getAllCars());
        } catch (CarDaoException e) {
            e.printStackTrace();
        }
    }

}
