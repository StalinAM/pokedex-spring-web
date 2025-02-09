package ec.edu.uce.pokedex.controller;

import ec.edu.uce.pokedex.entities.Pokemon;
import ec.edu.uce.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonRestController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> getAllPokemonsSorted(@RequestParam(defaultValue = "ascendente") String order) {
        return pokemonService.getAllPokemonsSorted(order);
    }

    @GetMapping("/{name}")
    public Pokemon getPokemonByName(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }

    @GetMapping("/ability/{abilityName}")
    public List<Pokemon> getPokemonsByAbility(@PathVariable String abilityName) {
        return pokemonService.getPokemonsByAbility(abilityName);
    }

    @GetMapping("/type/{typeName}")
    public List<Pokemon> getPokemonsByType(@PathVariable String typeName) {
        return pokemonService.getPokemonsByType(typeName);
    }

    @GetMapping("/load")
    public List<Pokemon> loadPokemons() {
        return pokemonService.loadPokemons();
    }

    @GetMapping("/id/{id}")
    public Pokemon findPokemonById(@PathVariable Long id) {
        return pokemonService.findPokemonById(id);
    }

    @GetMapping("/all")
    public List<Pokemon> findAllPokemons() {
        return pokemonService.findAllPokemons();
    }
}
