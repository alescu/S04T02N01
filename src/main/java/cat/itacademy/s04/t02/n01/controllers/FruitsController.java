package cat.itacademy.s04.t02.n01.controllers;

import cat.itacademy.s04.t02.n01.model.ApiResponse;
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
  public ResponseEntity<String> addingFruit(@RequestParam String name,@RequestParam double kg) throws JsonProcessingException {
    fruitsServices.saveFruit(name, kg);
    ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Fruit added successfully");
    ObjectMapper mapper = new ObjectMapper();
    return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
  }


  @GetMapping("/getAll")
  public ResponseEntity<String> getAll() throws SQLException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      String jsonFruit = mapper.writeValueAsString(fruitsServices.getAllFruits());

      ApiResponse response = new ApiResponse(HttpStatus.OK, jsonFruit);
      return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));

    } catch (JsonProcessingException e) {
      throw new ResponseStatusException(
              HttpStatus.INTERNAL_SERVER_ERROR, "Error showing data", e);
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> delete(@RequestParam int id) throws JsonProcessingException {
    String message;
    int statusCode;
    if (fruitsServices.deleteFruitById(id)) {
      message="Fruit with ID " + id + " deleted successfully.";
      statusCode=HttpStatus.OK.value();
    }else{
      message="Fruit with ID " + id + " not found.";
      statusCode=HttpStatus.NOT_FOUND.value();
    }
    ApiResponse response = new ApiResponse(statusCode, message);
    ObjectMapper mapper = new ObjectMapper();
    return ResponseEntity.status(statusCode).body(mapper.writeValueAsString(response));
  }

  @GetMapping("/getOne")
  public ResponseEntity<String> getOne(@RequestParam int id){
    String message;
    int statusCode;
    try{
      Fruit fruit = fruitsServices.getFruitById(id);
      ObjectMapper mapper = new ObjectMapper();
      if(fruit!=null){
        String jsonFruit = mapper.writeValueAsString(fruit);
        ApiResponse response = new ApiResponse(HttpStatus.OK, jsonFruit);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response));
      }
      statusCode=HttpStatus.NOT_FOUND.value();
      message="Fruit with ID " + id + " not found.";
      ApiResponse response = new ApiResponse(statusCode, message);
      return ResponseEntity.status(statusCode).body(mapper.writeValueAsString(response));

    } catch (JsonProcessingException e) {
      throw new ResponseStatusException(
              HttpStatus.INTERNAL_SERVER_ERROR, "Error showing data", e);
    }
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateFruit(@RequestParam int id, String name, Double kg) throws JsonProcessingException {
    String message;
    int statusCode;
    if(fruitsServices.updateFruit(id, name, kg)){
      message="Fruit updated";
      statusCode=HttpStatus.OK.value();
    }else{
      message="Fruit id not found";
      statusCode=HttpStatus.OK.value();
    }
    ApiResponse response = new ApiResponse(statusCode, message);
    ObjectMapper mapper = new ObjectMapper();
    return ResponseEntity.status(statusCode).body(mapper.writeValueAsString(response));
  }

}
