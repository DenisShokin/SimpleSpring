package d.shokin.demo.controller;

import d.shokin.demo.entity.Coffee;
import d.shokin.demo.repository.CoffeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeRepository coffeeRepository;

    public CoffeeController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @GetMapping()
    public Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return coffeeRepository.findById(id);
    }

    @PostMapping()
    public Coffee postCoffee(@RequestBody Coffee coffee) {
        coffeeRepository.save(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        return (coffeeRepository.existsById(id)) ?
                new ResponseEntity<>(coffee, HttpStatus.OK):
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable String id) {
        coffeeRepository.deleteById(id);
    }
}
