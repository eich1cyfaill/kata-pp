package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao{

    @Override
    public List<Car> getCarList() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("TT120", 86829, 4));
        carList.add(new Car("TT121", 574, 4));
        carList.add(new Car("TT122", 84568, 4));
        carList.add(new Car("TT123", 200383, 4));
        carList.add(new Car("TT124", 845268, 4));
        carList.add(new Car("TT125", 2345, 4));
        carList.add(new Car("TT126", 723849, 4));
        carList.add(new Car("TT127", 34568734, 4));
        carList.add(new Car("TT128", 46737, 4));
        carList.add(new Car("TT129", 4344443, 4));

        return carList;
    }

    @Override
    public List<Car> getLimitedCarList(int amount) {
        return this.getCarList().stream().limit(amount).toList();
    }
}
