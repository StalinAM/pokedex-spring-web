package ec.edu.uce.pokedex.controller;

import ec.edu.uce.pokedex.entities.Pokemon;
import ec.edu.uce.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class PokedexController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/pokemons")
    public String getPokemons(@RequestParam(required = false) String query, Model model) {
        List<Pokemon> pokemons;
        if (query == null || query.isEmpty()) {
            pokemons = pokemonService.getAllPokemonsSorted("asc");
        } else {
            pokemons = pokemonService.getPokemonsByType(query);
        }
        model.addAttribute("pokemons", pokemons);
        return "pokemons";
    }
}
