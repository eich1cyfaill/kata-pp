package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CarDaoImpl;
import web.model.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private CarDaoImpl carDao;

    @Autowired
    public CarServiceImpl(CarDaoImpl carDao) {
        this.carDao = carDao;
    }

    @Override
    public List<Car> getCarList(Optional<Integer> amount) {
        if(amount.isPresent() && amount.get() > 0){
            return carDao.getLimitedCarList(amount.get());
        } else {
            return carDao.getCarList();
        }
    }
}
