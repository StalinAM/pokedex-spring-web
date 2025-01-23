package ec.edu.uce.pokedex.service;

import ec.edu.uce.pokedex.entities.Pokemon;
import ec.edu.uce.pokedex.repository.AbilitiesRepository;
import ec.edu.uce.pokedex.repository.PokemonRepository;
import ec.edu.uce.pokedex.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private AbilitiesRepository abilitiesRepository;

    @Autowired
    private TypeRepository typeRepository;

    // Obtener todos los Pokémon ordenados por nombre
    @Transactional(readOnly = true)
    public List<Pokemon> getAllPokemonsSorted(String order) {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons.stream()
                .sorted((p1, p2) -> order.equals("asc") ? p1.getName().compareTo(p2.getName()) : p2.getName().compareTo(p1.getName()))
                .collect(Collectors.toList());
    }

    // Buscar Pokémon por nombre
    public Pokemon getPokemonByName(String name) {
        return pokemonRepository.findByName(name).orElse(null);
    }

    // Buscar Pokémon por habilidad utilizando Streams
    public List<Pokemon> getPokemonsByAbility(String abilityName) {
        return pokemonRepository.findByAbilities_Name(abilityName).stream()
                .filter(pokemon -> pokemon.getAbilities().stream()
                        .anyMatch(ability -> ability.getName().equalsIgnoreCase(abilityName)))
                .collect(Collectors.toList());
    }

    // Buscar Pokémon por tipo utilizando Streams
    public List<Pokemon> getPokemonsByType(String typeName) {
        return pokemonRepository.findByTypes_Name(typeName).stream()
                .filter(pokemon -> pokemon.getTypes().stream()
                        .anyMatch(type -> type.getName().equalsIgnoreCase(typeName)))
                .collect(Collectors.toList());
    }
}

