package ec.edu.uce.pokedex;

import ec.edu.uce.pokedex.api.PokeService;
import ec.edu.uce.pokedex.entities.Pokemon;
import ec.edu.uce.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokedexApplication implements CommandLineRunner {

    @Autowired
    private PokeService pokeService;

    public static void main(String[] args) {
        SpringApplication.run(PokedexApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
        pokeService.fetchAndSaveAllPokemon(); }



}
