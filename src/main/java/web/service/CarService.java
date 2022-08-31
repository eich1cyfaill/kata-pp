package web.service;

import web.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getCarList(Optional<Integer> amount);
}
