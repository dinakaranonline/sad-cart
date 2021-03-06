package io.openshift.booster;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting1")
    public Greeting1 greeting(@RequestParam(value="name", defaultValue="World") String name) {
        String prefix = System.getenv().getOrDefault("hello-world-config-key", "config1");
        String suffix = System.getenv().getOrDefault("hello-world-config-key-2", "config2");
        return new Greeting1(counter.incrementAndGet(),
                            String.format(prefix+" "+template+" "+suffix, name));
    }

    @GetMapping(path = "/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "/";
	}
}
