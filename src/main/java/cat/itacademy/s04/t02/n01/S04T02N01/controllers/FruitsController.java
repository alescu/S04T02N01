package cat.itacademy.s04.t02.n01.S04T02N01.controllers;

import cat.itacademy.s04.t02.n01.S04T02N01.config.DatabaseConnectionManager;
import cat.itacademy.s04.t02.n01.S04T02N01.model.CustomError;
import cat.itacademy.s04.t02.n01.S04T02N01.model.Fruit;
import cat.itacademy.s04.t02.n01.S04T02N01.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/fruits")
public class FruitsController {

  @PostMapping("/add")
  public ResponseEntity<String> addingFruit(@RequestParam String name,@RequestParam double kg) throws SQLException {
    final String queryInsert="INSERT INTO fruits (name,kg) VALUES (?, ?)";
    try{
      Connection conn = DatabaseConnectionManager.getConnection();
      PreparedStatement ps = conn.prepareStatement(queryInsert);
      ps.setString(1, name);
      ps.setDouble(2, kg);
      ps.executeUpdate();
      return ResponseEntity.status(HttpStatus.OK).body("Fruit added correctly");

    }catch (SQLException ex) {
      System.out.println(ex);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error adding fruit");
    }
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateFruit(@RequestParam int id,String name,Double kg){
    StringBuilder querySb = new StringBuilder("UPDATE fruits SET ");
    try{
      List<Object> params = new ArrayList<>();
      Connection conn = DatabaseConnectionManager.getConnection();

      if(!Utils.isReallyEmpty(name)) {
        querySb.append("name = ?,");
        params.add(name);
      }

      if(kg!=null && !kg.isNaN()) {
        querySb.append("kg = ?, ");
        params.add(kg);
      }

      if(params.isEmpty()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no data to update");
      }else{
        params.add(id);
        querySb.delete(querySb.length() - 2, querySb.length());
        querySb.append(" WHERE id = ?");
        PreparedStatement ps = conn.prepareStatement(querySb.toString());
        for (int i = 0; i < params.size(); i++) {
          ps.setObject(i + 1, params.get(i));
        }

        int rowsUpdated = ps.executeUpdate();

        if (rowsUpdated > 0) {
          return ResponseEntity.status(HttpStatus.OK).body("Fruit with ID " + id + " updated successfully.");
        } else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fruit with ID " + id + " not found.");
        }

      }

    } catch (SQLException ex) {
      throw new ResponseStatusException(
              HttpStatus.INTERNAL_SERVER_ERROR, "Error updating fruit with ID " + id, ex);
    }
  }

  @GetMapping("/getAll")
  public ResponseEntity<String> getAll() throws SQLException {
    Statement stmt = DatabaseConnectionManager.getConnection().createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM fruits");

    List<Fruit> fruitList = new ArrayList<>();
    while(rs.next()){
      Fruit fruit = new Fruit(rs.getInt(1),rs.getString(2),rs.getDouble(3));
      fruitList.add(fruit);
    }
      try {
        ObjectMapper mapper = new ObjectMapper();
        String jsonFruit = mapper.writeValueAsString(fruitList);
          return ResponseEntity.ok(jsonFruit);
      } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
      }
  }

  @PutMapping("/delete")
  public ResponseEntity<String> delete(@RequestParam int id){
    try {
      ResponseEntity<String> result = getOne(id);
      if(result.getStatusCode().equals(HttpStatus.OK)){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fruit with ID " + id + " not found.");
      }

      Statement stmt = DatabaseConnectionManager.getConnection().createStatement();
      int res = stmt.executeUpdate("DELETE FROM fruits WHERE id=?");
      if(res>0){
        return ResponseEntity.status(HttpStatus.OK).body("Fruit with ID " + id + " updated successfully.");
      }else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fruit with ID " + id + " not found.");
      }

    } catch (SQLException ex) {
      throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "Error deleting fruit id: " + id, ex);
    }
  }

  @GetMapping("/getOne")
  public ResponseEntity<String> getOne(@RequestParam int id){
      try {
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement("SELECT * FROM fruits WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Fruit fruit = null;
        while(rs.next()){
          fruit = new Fruit(rs.getInt(1),rs.getString(2),rs.getDouble(3));
        }

        try {
          ObjectMapper mapper = new ObjectMapper();
          String jsonFruit = mapper.writeValueAsString(fruit);
          return ResponseEntity.ok(jsonFruit);

        } catch (JsonProcessingException e) {
          throw new RuntimeException("Error converting fruit to JSON", e);
        }

      } catch (SQLException ex) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Error deleting fruit id: " + id, ex);
      }
    }

}
