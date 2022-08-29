package hiber.service;

import hiber.dao.CarDaoImp;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImp {

    @Autowired
    private CarDaoImp carDaoImp;

    public void add(Car car) {
        carDaoImp.add(car);
    }

    public User getCarOwner(int series) {
        return carDaoImp.getCarOwner(series);
    }
}
