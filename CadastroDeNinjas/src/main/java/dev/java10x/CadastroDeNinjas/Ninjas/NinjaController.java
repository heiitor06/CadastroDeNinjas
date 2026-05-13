package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // GET /ninjas
    @GetMapping
    public ResponseEntity<List<NinjaModel>> listarNinjas() {
        return ResponseEntity.ok(ninjaService.listarNinjas());
    }

    // GET /ninjas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<NinjaModel> ninja = ninjaService.buscarPorId(id);
        if (ninja.isPresent()) {
            return ResponseEntity.ok(ninja.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado!");
    }

    // POST /ninjas
    @PostMapping
    public ResponseEntity<NinjaModel> criarNinja(@RequestBody NinjaModel ninja) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ninjaService.criarNinja(ninja));
    }

    // PUT /ninjas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id, @RequestBody NinjaModel ninja) {
        NinjaModel atualizado = ninjaService.atualizarNinja(id, ninja);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado!");
    }

    // DELETE /ninjas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id) {
        ninjaService.deletarNinja(id);
        return ResponseEntity.ok("Ninja deletado com sucesso!");
    }
}