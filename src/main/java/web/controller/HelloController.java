package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "index";
	}

	@GetMapping(value = "/cars")
	public String printCarList(@RequestParam("amount") int amount, ModelMap model) {
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


		if(amount <= 5) {
			model.addAttribute("cars", CarService.getLimitedCarList(carList, amount));
		} else {
			model.addAttribute("cars", carList);
		}


		return "cars";
	}
}