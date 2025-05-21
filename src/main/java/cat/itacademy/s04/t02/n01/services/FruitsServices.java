package cat.itacademy.s04.t02.n01.services;

import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.repository.FruitsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitsServices {

    private final FruitsRepository fruitsRepository;

    FruitsServices(FruitsRepository fruitsRepository){
        this.fruitsRepository = fruitsRepository;
    }

    public Fruit saveFruit(Fruit fruit){
        return fruitsRepository.save(fruit);
    }

    public List<Fruit> getAllFruits() {
       return fruitsRepository.findAll();
    }

    public void deleteFruitById(int id){
        fruitsRepository.deleteById(id);
    }

    public Fruit getFruitById( int id){
        Optional<Fruit> optFruit = fruitsRepository.findById(id);
        return optFruit.orElse(null);
    }

    public Fruit updateFruit(int id, Fruit fruit){
        Optional<Fruit> optFruit = fruitsRepository.findById(id);
        if(optFruit.isPresent()){
            if(fruit.getName()!=null) {
                optFruit.get().setName(fruit.getName());
            }
            if(fruit.getKg()!=null) {
                optFruit.get().setKg(fruit.getKg());
            }
            ObjectMapper mapper = new ObjectMapper();
            fruitsRepository.save(optFruit.get());
            return optFruit.get();
        }
        return null;
    }

}
