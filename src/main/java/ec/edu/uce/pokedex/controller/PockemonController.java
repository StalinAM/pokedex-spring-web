//package ec.edu.uce.pokedex.controller;
//
//import ec.edu.uce.pokedex.entities.Pokemon;
//import ec.edu.uce.pokedex.repository.PokemonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.*;
//
//
//@RestController
//@RequestMapping("/api/pokemon")
//public class PockemonController {
//
//    @Autowired
//    private PokemonRepository pokemonRepository;
//
//    // Obtener todos los Pokémon
//    @GetMapping
//    public List<Pokemon> getAllPokemons() {
//        return pokemonRepository.findAll();
//    }
//
//    // Obtener Pokémon por nombre
//    @GetMapping("/{name}")
//    public Pokemon getPokemonByName(@PathVariable String name) {
//        return pokemonRepository.findByName(name);
//    }
//
//    // Obtener Pokémon por tipo
//    @GetMapping("/byType")
//    public List<Pokemon> getPokemonsByType(@RequestParam String type) {
//        return pokemonRepository.findByTypes_NameIgnoreCase(type);
//    }
//
//    // Obtener Pokémon por habilidad
//    @GetMapping("/byAbility")
//    public List<Pokemon> getPokemonsByAbility(@RequestParam String ability) {
//        return pokemonRepository.findByAbilities_NameIgnoreCase(ability);
//    }
//}
//
