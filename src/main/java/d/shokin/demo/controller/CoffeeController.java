package d.shokin.demo.controller;

import d.shokin.demo.entity.Coffee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CoffeeController {

    private List<Coffee> coffees = new ArrayList<>();

    public CoffeeController(List<Coffee> coffees) {
        coffees.addAll(List.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Três Pontas")
        ));
    }

    @GetMapping("/coffees")
    public List<Coffee> getCoffees() {
        return coffees;
    }

    @GetMapping("/coffees/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return getCoffees().stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @PostMapping("/coffees")
    public Coffee postCoffee(@RequestBody Coffee coffee) {
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/coffee/{id}")
    public Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        int coffeeIndex = -1;
        for (Coffee c : coffees) {
            if (c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
    }
}
