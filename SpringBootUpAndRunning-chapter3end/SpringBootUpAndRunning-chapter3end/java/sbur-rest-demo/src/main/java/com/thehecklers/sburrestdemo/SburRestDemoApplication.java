package com.thehecklers.sburrestdemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class SburRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SburRestDemoApplication.class, args);
    }
}

@Repository
interface CoffeeRepository extends JpaRepository<Coffee, String> {
}

@RestController
@RequestMapping("/coffees")
class RestApiDemoController {
    private final CoffeeRepository coffeeRepository;

    public RestApiDemoController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;

        if (coffeeRepository.count() == 0) {
            coffeeRepository.saveAll(List.of(
                    new Coffee("Café Cereza"),
                    new Coffee("Café Ganador"),
                    new Coffee("Café Lareño"),
                    new Coffee("Café Três Pontas")
            ));
            System.out.println("✅ Dados iniciais carregados no MySQL!");
        }
    }

    @GetMapping
    List<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return coffeeRepository.findById(id);
    }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id,
                                     @RequestBody Coffee coffee) {
        if (coffeeRepository.existsById(id)) {
            coffee.setId(id);
            return new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffeeRepository.deleteById(id);
    }
}

@Entity
@Table(name = "coffees")
class Coffee {
    @Id
    private String id;
    private String name;

    public Coffee() {}

    @JsonCreator
    public Coffee(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}