package com.rutak.carRental.resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Home controller which return index and hence trigger index.html.
@Controller
public class RentalAppHomeController {
	@RequestMapping("/")
	public String welcome() {
		return "index";
	}
}
