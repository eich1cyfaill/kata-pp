package web.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HelloController implements BeanFactoryAware {

	private CarServiceImpl carService;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		carService = (CarServiceImpl) beanFactory.getBean("carServiceImpl");
	}

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
	public String printCarList(@RequestParam("amount") Optional<Integer> amount, ModelMap model) {
		List<Car> carList = carService.getCarList(amount);
		model.addAttribute("cars", carList);
		return "cars";
	}
}