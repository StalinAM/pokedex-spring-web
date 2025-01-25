package ec.edu.uce.pokedex.service;

import ec.edu.uce.pokedex.entities.Pokemon;
import ec.edu.uce.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Pokemon findPokemonById(Long id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        return pokemon.orElse(null);
    }
    public List<Pokemon> findAllPokemons() {
        return pokemonRepository.findAll();
    }
}

