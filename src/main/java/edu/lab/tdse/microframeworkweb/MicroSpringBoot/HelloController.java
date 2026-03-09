package edu.lab.tdse.microframeworkweb.MicroSpringBoot;

@RestController
public class HelloController {

	@GetMapping("/")
	public static String index() {
		return "Greetings from Spring Boot!";
	}

    @GetMapping("/pi")
	public static String getPI() {
		return "PI: " + Math.PI;
	}
}
