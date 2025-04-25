package dio.design_patterns.service;

import dio.design_patterns.model.Endereco;
import dio.design_patterns.model.Restaurante;
import dio.design_patterns.repository.EnderecoRepository;
import dio.design_patterns.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCep;

    public Iterable<Restaurante> buscarTodos(){
        return restauranteRepository.findAll();
    }

    public Restaurante buscarPorId(Integer id){
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        return restaurante.get();
    }

    public void inserir(Restaurante restaurante){
        salvarRestauranteComCep(restaurante);
    }

    public void atualizar(Integer id, Restaurante restaurante){
        Optional<Restaurante> restauranteBd = restauranteRepository.findById(id);
        if (restauranteBd.isPresent()){
            salvarRestauranteComCep(restaurante);
        }
    }

    public void deletar(Integer id){
        Optional<Restaurante> restauranteParaExcluir = restauranteRepository.findById(id);
        if(restauranteParaExcluir.isPresent()){
            restauranteRepository.delete(restauranteParaExcluir.get());
        }
    }

    private void salvarRestauranteComCep(Restaurante restaurante){

        String cep = restaurante.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCep.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        restaurante.setEndereco(endereco);
        restauranteRepository.save(restaurante);
    }

}
