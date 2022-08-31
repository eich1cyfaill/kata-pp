package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

public interface CarDao {
    public void add(Car car);

    public User getCarOwner(int series, String model);
}
