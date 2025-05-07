package cat.itacademy.s04.t02.n01.controllers;

import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.services.FruitsServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@RestController()
@RequestMapping("/fruits")
public class FruitsController {

  @Autowired
  private FruitsServices fruitsServices;

  @PostMapping("/add")
  public ResponseEntity<String> addingFruit(@RequestParam String name,@RequestParam double kg){
    fruitsServices.saveFruit(name, kg);
    return ResponseEntity.status(HttpStatus.OK).body("Fruit added successfully");
  }


  @GetMapping("/getAll")
  public ResponseEntity<String> getAll() throws SQLException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      String jsonFruit = mapper.writeValueAsString(fruitsServices.getAllFruits());
      return ResponseEntity.ok(jsonFruit);
    } catch (JsonProcessingException e) {
      throw new ResponseStatusException(
              HttpStatus.INTERNAL_SERVER_ERROR, "Error showing data", e);
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> delete(@RequestParam int id){
    if (fruitsServices.deleteFruitById(id)) {
      return ResponseEntity.status(HttpStatus.OK).body("Fruit with ID " + id + " deleted successfully.");
    }else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fruit with ID " + id + " not found.");
    }
  }

  @GetMapping("/getOne")
  public ResponseEntity<String> getOne(@RequestParam int id){
    try{
      Fruit fruit = fruitsServices.getFruitById(id);
      if(fruit!=null){
        ObjectMapper mapper = new ObjectMapper();
        String jsonFruit = mapper.writeValueAsString(fruit);
        return ResponseEntity.ok(jsonFruit);
      }else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Fruit id not found");
      }

    } catch (JsonProcessingException e) {
      throw new ResponseStatusException(
              HttpStatus.INTERNAL_SERVER_ERROR, "Error showing data", e);
    }
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateFruit(@RequestParam int id, String name, Double kg){
    if(fruitsServices.updateFruit(id, name, kg)){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Fruit updated");
    }else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Fruit id not found");
    }
  }

}
