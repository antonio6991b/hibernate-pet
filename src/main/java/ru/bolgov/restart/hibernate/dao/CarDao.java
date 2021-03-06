package ru.bolgov.restart.hibernate.dao;

import org.hibernate.*;
import ru.bolgov.restart.hibernate.pojo.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class provide CRUD operations to Database "CARS"
 *
 */
public class CarDao {
    private final SessionFactory sessionFactory;

    public CarDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;

    }

    /**
     * Add a Car to the database
     * @param car
     * @return true if the car add to database
     */
    public boolean addCar(Car car){

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();

            return true;
        }catch (HibernateException e){
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            System.out.println("Failed to insert data in the table: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get all cars from the database
     * @return all cars from the database
     * @throws CarDaoException
     */
    public List<Car> getAllCars() throws CarDaoException {
        List<Car> cars = new ArrayList<>();

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            cars = session.createQuery("from Car ").list();
            transaction.commit();

            return cars;
        }catch (HibernateException e){
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            System.out.println("Failed to insert data in the table: " + e.getMessage());
            e.printStackTrace();
            throw new CarDaoException("Could not get Cars from Database");
        }
    }

    /**
     * Get car from the database by ID
     * @param id
     * @return car
     * @throws CarDaoException
     */
    public Car getCar(int id) throws CarDaoException {
        Car car = null;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            car = session.get(Car.class, id);
            transaction.commit();

            return car;
        }catch (HibernateException e){
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            System.out.println("Failed to insert data in the table: " + e.getMessage());
            e.printStackTrace();
            throw new CarDaoException("Could not get Cars from Database");
        }
    }

    /**
     * Update owner of the car by ID
     * @param id id of the changing car
     * @param owner new value of the owner field.
     * @return true if owner updated
     */
    public boolean updateOwner(int id, String owner){
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            Car updateCar = session.get(Car.class, id);
            updateCar.setOwner(owner);
            session.update(updateCar);
            transaction.commit();

            return true;
        }catch (HibernateException e){
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            System.out.println("Failed to insert data in the table: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * remove car from the database by id
     * @param id
     * @return true if remove successfully
     */
    public boolean removeCar(int id){
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            Car carRemove = getCar(id);
            session.remove(carRemove);
            transaction.commit();

            return true;
        }catch (HibernateException | CarDaoException e){
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            System.out.println("Failed to insert data in the table: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
