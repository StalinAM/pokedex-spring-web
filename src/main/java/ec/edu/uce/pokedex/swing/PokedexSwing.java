package ec.edu.uce.pokedex.swing;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class PokedexSwing {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pokédex (Spring Boot Backend)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        DefaultListModel<String> pokemonListModel = new DefaultListModel<>();
        JList<String> pokemonList = new JList<>(pokemonListModel);

        JPanel detailsPanel = new JPanel(new BorderLayout());
        JLabel pokemonImageLabel = new JLabel();
        pokemonImageLabel.setHorizontalAlignment(JLabel.CENTER);
        JTextArea pokemonDetailsArea = new JTextArea();
        pokemonDetailsArea.setWrapStyleWord(true);
        pokemonDetailsArea.setLineWrap(true);
        pokemonDetailsArea.setEditable(false);

        detailsPanel.add(pokemonImageLabel, BorderLayout.NORTH);
        detailsPanel.add(new JScrollPane(pokemonDetailsArea), BorderLayout.CENTER);

        pokemonList.addListSelectionListener(e -> {
            String selectedPokemon = pokemonList.getSelectedValue();
            if (selectedPokemon != null) {
                mostrarDetallesPokemon(selectedPokemon, pokemonImageLabel, pokemonDetailsArea);
            }
        });

        frame.add(new JScrollPane(pokemonList), BorderLayout.WEST);
        frame.add(detailsPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        List<String> pokemons = obtenerListaPokemon();
        System.out.println(pokemons);
        pokemons.forEach(pokemonListModel::addElement);
    }

    private static List<String> obtenerListaPokemon() {
        ArrayList<String> pokemons = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/pokemon?limit=10"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray jsonArray = new JSONArray(response.body());

            for (int i = 0; i < jsonArray.length(); i++) {
                pokemons.add(jsonArray.getString(i));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de Pokémon: " + e.getMessage());
        }
        return pokemons;
    }

    private static void mostrarDetallesPokemon(String nombrePokemon, JLabel imageLabel, JTextArea detailsArea) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/pokemon/" + nombrePokemon))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());

            // Mostrar imagen
            String imageUrl = jsonResponse.getString("image");
            ImageIcon pokemonImage = new ImageIcon(new URI(imageUrl).toURL());
            imageLabel.setIcon(pokemonImage);

            // Mostrar tipos y habilidades
            JSONArray typesArray = jsonResponse.getJSONArray("types");
            JSONArray abilitiesArray = jsonResponse.getJSONArray("abilities");

            StringBuilder details = new StringBuilder();
            details.append("Tipos: ");
            for (int i = 0; i < typesArray.length(); i++) {
                details.append(typesArray.getString(i)).append(" ");
            }
            details.append("\nHabilidades: ");
            for (int i = 0; i < abilitiesArray.length(); i++) {
                details.append(abilitiesArray.getString(i)).append(" ");
            }

            detailsArea.setText(details.toString());

        } catch (Exception e) {
            detailsArea.setText("Error al obtener detalles del Pokémon: " + e.getMessage());
            imageLabel.setIcon(null);
        }
    }
}
