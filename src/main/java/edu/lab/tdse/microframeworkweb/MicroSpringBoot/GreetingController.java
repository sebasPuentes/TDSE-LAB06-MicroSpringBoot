package edu.lab.tdse.microframeworkweb.MicroSpringBoot;

@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public static String greeting() {
        return "Hello, welcome to the micro framework!";
    }
}
