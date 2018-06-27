package greeting;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static javax.measure.unit.SI.KILOGRAM;
import javax.measure.quantity.Mass;

import org.jscience.physics.amount.Amount;
import org.jscience.physics.model.RelativisticModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Hello greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Hello(counter.incrementAndGet(), String.format(template, name));
    }
    
    @RequestMapping("/jscience")
    String hello(Map<String, Object> model) {
        RelativisticModel.select();
        Amount<Mass> m = Amount.valueOf("12 GeV").to(KILOGRAM);
        model.put("science", "E=mc^2: 12 GeV = " + m.toString());
        return "hello";
    }
}