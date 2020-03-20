package ru.severyuchin.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.severyuchin.service.CounterService;

import java.util.Collection;

@RestController
@RequestMapping("/api/counter")
public class CounterRestController {

    CounterService counterService;

    public CounterRestController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping(value = "/createCounter", produces = "application/json")
    public ResponseEntity<String> createCounter(@RequestParam String name) {
        if (counterService.createCounter(name)) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Counter created");
        }
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("It counter contains in server");
    }

    @PutMapping(value = "/setValue", produces = "application/json")
    public ResponseEntity<String> setCounterValue(@RequestParam String name,
                                                  @RequestParam int value) {
        if (counterService.setCounterValue(name, value)) {
            return ResponseEntity.ok("Value changed");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/getCounterValue/{name}")
    public ResponseEntity<Integer> getCounterValue(@PathVariable("name") String name) {
        Integer value = counterService.getCounterValue(name);
        if (value != null) {
            return ResponseEntity.ok(value);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/deleteCounter")
    public ResponseEntity<String> deleteCounter(@RequestParam String name) {
        if (counterService.deleteCounter(name)) {
            return ResponseEntity.ok("Counter deleted");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/getCounterSum")
    public ResponseEntity<Integer> getCounterSum() {
        return ResponseEntity.ok(counterService.getCounterSum());
    }

    @GetMapping(value = "/getCounterNames")
    public ResponseEntity<Collection<String>> getCounterNames() {
        return ResponseEntity.ok(counterService.getCounterNames());
    }

}
