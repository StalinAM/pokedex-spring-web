package ec.edu.uce.pokedex.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.*;
import java.util.*;

@RestController
@RequestMapping("/api/pokemon")
public class PockemonController {
    private static final String POKEAPI_BASE_URL = "https://pokeapi.co/api/v2/pokemon";

    @GetMapping
    public List<String> getPokemonList(@RequestParam(defaultValue = "50") int limit) {
        List<String> pokemonNames = new ArrayList<>();
        String url = POKEAPI_BASE_URL + "?limit=" + limit;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject jsonResponse = new JSONObject(response);
        JSONArray results = jsonResponse.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject pokemon = results.getJSONObject(i);
            pokemonNames.add(pokemon.getString("name"));
        }

        return pokemonNames;
    }

    @GetMapping("/{name}")
    public Map<String, Object> getPokemonDetails(@PathVariable String name) {
        String url = POKEAPI_BASE_URL + "/" + name;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject jsonResponse = new JSONObject(response);
        Map<String, Object> pokemonDetails = new HashMap<>();

        // Extraer la imagen
        String imageUrl = jsonResponse.getJSONObject("sprites").getString("front_default");
        pokemonDetails.put("image", imageUrl);

        // Extraer tipos
        JSONArray typesArray = jsonResponse.getJSONArray("types");
        List<String> types = new ArrayList<>();
        for (int i = 0; i < typesArray.length(); i++) {
            types.add(typesArray.getJSONObject(i).getJSONObject("type").getString("name"));
        }
        pokemonDetails.put("types", types);

        // Extraer habilidades
        JSONArray abilitiesArray = jsonResponse.getJSONArray("abilities");
        List<String> abilities = new ArrayList<>();
        for (int i = 0; i < abilitiesArray.length(); i++) {
            abilities.add(abilitiesArray.getJSONObject(i).getJSONObject("ability").getString("name"));
        }
        pokemonDetails.put("abilities", abilities);

        return pokemonDetails;
    }

}
