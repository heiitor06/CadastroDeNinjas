package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping
    public ResponseEntity<List<MissoesModel>> listarMissoes() {
        return ResponseEntity.ok(missoesService.listarMissoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<MissoesModel> missao = missoesService.buscarPorId(id);
        if (missao.isPresent()) {
            return ResponseEntity.ok(missao.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
    }

    @PostMapping
    public ResponseEntity<MissoesModel> criarMissao(@RequestBody MissoesModel missao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missoesService.criarMissao(missao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMissao(@PathVariable Long id, @RequestBody MissoesModel missao) {
        MissoesModel atualizada = missoesService.atualizarMissao(id, missao);
        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id) {
        missoesService.deletarMissao(id);
        return ResponseEntity.ok("Missão deletada com sucesso!");
    }
}