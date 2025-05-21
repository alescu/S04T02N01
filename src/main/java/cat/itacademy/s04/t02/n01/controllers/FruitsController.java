package cat.itacademy.s04.t02.n01.controllers;

import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.services.FruitsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/fruits")
public class FruitsController {

  @Autowired
  private FruitsServices fruitsServices;

  @PostMapping("/add")
  public ResponseEntity<Fruit> addingFruit(@RequestBody Fruit fruit){
    return ResponseEntity.status(HttpStatus.CREATED).body(fruitsServices.saveFruit(fruit));
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<Fruit>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(fruitsServices.getAllFruits());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    fruitsServices.deleteFruitById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/getOne/{id}")
  public ResponseEntity<Fruit> getOne(@PathVariable int id){
      Fruit fruit = fruitsServices.getFruitById(id);
      return ResponseEntity.status(HttpStatus.OK).body(fruit);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Fruit> updateFruit(@PathVariable int id, @RequestBody Fruit fruit) {
    return ResponseEntity.status(HttpStatus.OK).body(fruitsServices.updateFruit(id, fruit));
  }

}
