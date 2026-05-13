package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();
    }

    public Optional<NinjaModel> buscarPorId(Long id){
        return ninjaRepository.findById(id);
    }

    public NinjaModel criarNinja(NinjaModel ninja) {
        return ninjaRepository.save(ninja);
    }

    public NinjaModel atualizarNinja(Long id, NinjaModel ninjaAtualizado){
        Optional<NinjaModel> ninjaExiste = ninjaRepository.findById(id);
        if(ninjaExiste.isPresent()){
            NinjaModel ninja = ninjaExiste.get();
            ninja.setName(ninjaAtualizado.getName());
            ninja.setEmail(ninjaAtualizado.getEmail());
            ninja.setIdade(ninjaAtualizado.getIdade());
            return ninjaRepository.save(ninja);
        }
        return null;
    }

    public void deletarNinja(Long id){
        ninjaRepository.deleteById(id);
    }
}
