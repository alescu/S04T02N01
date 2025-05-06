package cat.itacademy.s04.t02.n01.repository;

import cat.itacademy.s04.t02.n01.model.Fruit;
import org.springframework.data.repository.CrudRepository;

public interface FruitsRepository extends CrudRepository<Fruit, Integer> {

}
