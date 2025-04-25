package dio.design_patterns.controller;

import dio.design_patterns.model.Restaurante;
import dio.design_patterns.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteRestController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<Iterable> buscarTodos(){
        return ResponseEntity.ok(restauranteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> consultarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(restauranteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Restaurante> inserir(@RequestBody Restaurante restaurante){
        restauranteService.inserir(restaurante);
        return ResponseEntity.ok(restaurante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@RequestBody Restaurante restaurante, Integer id){
        restauranteService.atualizar(id, restaurante);
        return  ResponseEntity.ok(restaurante);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        restauranteService.deletar(id);
    }
    
}
