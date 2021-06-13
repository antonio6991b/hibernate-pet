package ru.bolgov.restart.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.bolgov.restart.hibernate.pojo.Car;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * test class {@link CarDao}
 */
public class CarDaoTest {

    private List<Car> cars = new ArrayList<>();

    {
        cars.add(new Car("Mercedes", "Anton", Date.valueOf("2019-05-21"), 10000));
        cars.add(new Car("Honda", "Ivan", Date.valueOf("2015-10-01"), 150000));
        cars.add(new Car("Lada Vesta", "Vladimir", Date.valueOf("2017-10-10"), 120000));
        cars.add(new Car("Datsun", "Evgeni", Date.valueOf("2020-03-25"), 3000));
        cars.add(new Car("Opel", "Ksenia", Date.valueOf("2012-11-03"), 180000));
        cars.add(new Car("Volkswagen", "Anna", Date.valueOf("2005-07-27"), 250000));
        cars.add(new Car("Kia", "Sergey", Date.valueOf("2018-09-24"), 80000));
    }

    /**
     *test {@link CarDao#addCar(Car)}
     */
    @Test
    public void testAddCar(){

        CarDao carDao = mockDataToTest();
        Assertions.assertTrue(carDao.addCar(cars.get(0)));
    }

    /**
     *
     * test {@link CarDao#getAllCars()}
     */
    @Test
    public void testGetAllCars(){
        CarDao carDao = mockDataToTest();
        List<Car> getCars = null;
        try {
            getCars = carDao.getAllCars();
        } catch (CarDaoException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(cars.get(2).getBrand(), getCars.get(2).getBrand());
    }

    /**
     * test {@link CarDao#getCar(int)}
     */
    @Test
    public void getCarTest(){
        CarDao carDao = mockDataToTest();
        Car car = null;
        try {
            car = carDao.getCar(0);
        } catch (CarDaoException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(car.getBrand(), cars.get(0).getBrand());
    }

    /**
     * test {@link CarDao#updateOwner(int, String)}
     */
    @Test
    public void updateOwnerTest(){
        CarDao carDao = mockDataToTest();
        Assertions.assertTrue(carDao.updateOwner(0, "any"));
    }

    /**
     *test {@link CarDao#removeCar(int)}
     */
    @Test
    public void removeCarTest(){
        CarDao carDao = mockDataToTest();
        Assertions.assertTrue(carDao.removeCar(0));
    }

    private CarDao mockDataToTest(){

        SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
        Session session = Mockito.mock(Session.class);
        Transaction transaction = Mockito.mock(Transaction.class);
        Query query = Mockito.mock(Query.class);

        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        Mockito.when(session.createQuery(anyString())).thenReturn(query);
        Mockito.when(session.get(Car.class, 0)).thenReturn(cars.get(0));
        Mockito.when(query.list()).thenReturn(cars);


        return new CarDao(sessionFactory);
    }
}
