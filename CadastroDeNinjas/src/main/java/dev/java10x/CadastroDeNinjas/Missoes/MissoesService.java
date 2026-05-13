package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.MissoesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private final MissoesRepository missoesRep;

    public MissoesService(MissoesRepository missoesRep) {
        this.missoesRep = missoesRep;
    }

    public List<MissoesModel> listarMissoes(){
        return missoesRep.findAll();
    }

    public Optional<MissoesModel> buscarPorId(Long id){
        return missoesRep.findById(id);
    }

    public MissoesModel criarMissao(MissoesModel missao){
        return missoesRep.save(missao);
    }

    public MissoesModel atualizarMissao(Long id, MissoesModel missaoAtualizada){
        Optional<MissoesModel> missaoExist = missoesRep.findById(id);
        if(missaoExist.isPresent()){
            MissoesModel missao = missaoExist.get();
            missao.setNomeMissao(missaoAtualizada.getNomeMissao());
            missao.setDificuldade(missaoAtualizada.getDificuldade());
            return missoesRep.save(missao);
        }
        return null;
    }

    public void deletarMissao(Long id){
        missoesRep.deleteById(id);
    }
}