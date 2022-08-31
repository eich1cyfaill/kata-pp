package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.List;

@Service
public class CarService {
    public static List<Car> getLimitedCarList(List<Car> list, int amount) {
        return list.stream().limit(amount).toList();
    }
}
