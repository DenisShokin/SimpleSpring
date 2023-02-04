package d.shokin.demo.repository;

import d.shokin.demo.entity.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {
}
