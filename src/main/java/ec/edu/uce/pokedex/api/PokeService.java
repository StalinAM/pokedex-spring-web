package ec.edu.uce.pokedex.api;

import ec.edu.uce.pokedex.entities.Abilities;
import ec.edu.uce.pokedex.entities.Pokemon;
import ec.edu.uce.pokedex.entities.Type;
import ec.edu.uce.pokedex.repository.PokemonRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PokeService {
    @Autowired
    private PokemonRepository pokemonRepository;

    public void fetchAndSaveAllPokemon() throws Exception {
        PokeAPI apiClient = new PokeAPI();
        List<String> pokemonUrls = apiClient.fetchAllPokemonUrls();

        for (String url : pokemonUrls) {
            // Realiza la solicitud para cada Pokémon
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject pokemonJson = new JSONObject(response.toString());

            Pokemon pokemon = new Pokemon();
            pokemon.setName(pokemonJson.getString("name"));
            pokemon.setHeight(pokemonJson.getDouble("height"));
            pokemon.setWeight(pokemonJson.getDouble("weight"));

            // Manejar potencial null en 'front_default'
            JSONObject sprites = pokemonJson.getJSONObject("sprites");
            String frontDefault = sprites.isNull("front_default") ? null : sprites.getString("front_default");
            pokemon.setImage(frontDefault);

            // Procesar habilidades
            JSONArray abilitiesArray = pokemonJson.getJSONArray("abilities");
            List<Abilities> abilities = new ArrayList<>();
            for (int i = 0; i < abilitiesArray.length(); i++) {
                String abilityName = abilitiesArray.getJSONObject(i).getJSONObject("ability").getString("name");
                Abilities ability = new Abilities();
                ability.setName(abilityName);
                abilities.add(ability);
            }
            pokemon.setAbilities(abilities);

            // Procesar tipos
            JSONArray typesArray = pokemonJson.getJSONArray("types");
            List<Type> types = new ArrayList<>();
            for (int i = 0; i < typesArray.length(); i++) {
                String typeName = typesArray.getJSONObject(i).getJSONObject("type").getString("name");
                Type type = new Type();
                type.setName(typeName);
                types.add(type);
            }

            pokemon.setTypes(types);

            // Guardar en la base de datos
            pokemonRepository.save(pokemon);
        }
    }
}
