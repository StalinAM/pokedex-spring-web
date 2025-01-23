package ec.edu.uce.pokedex.swing;

import ec.edu.uce.pokedex.entities.Pokemon;
import ec.edu.uce.pokedex.service.PokemonService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PokedexSwing extends JFrame {

    private PokemonService pokemonService;
    private JList<String> pokemonList;
    private DefaultListModel<String> pokemonListModel;
    private JTextArea pokemonDetailsArea;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> filterTypeComboBox;

    public PokedexSwing(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
        pokemonListModel = new DefaultListModel<>();
        pokemonList = new JList<>(pokemonListModel);
        pokemonDetailsArea = new JTextArea(10, 30);
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        filterTypeComboBox = new JComboBox<>(new String[]{"All Types", "Fire", "Water", "Grass", "Electric"}); // Add more types as needed

        setTitle("Pokedex");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for search and list display
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search Pokémon by Name:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(filterTypeComboBox);

        // Scroll pane for Pokémon list
        JScrollPane listScrollPane = new JScrollPane(pokemonList);

        // Text area to display Pokémon details
        JScrollPane detailsScrollPane = new JScrollPane(pokemonDetailsArea);
        pokemonDetailsArea.setEditable(false);

        // Add components to the frame
        add(topPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);
        add(detailsScrollPane, BorderLayout.SOUTH);

        // Action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                String selectedType = (String) filterTypeComboBox.getSelectedItem();
                if (selectedType != null && !selectedType.equals("All Types")) {
                    searchPokemonsByType(selectedType);
                } else {
                    searchPokemonByName(query);
                }
            }
        });

        // Action listener for when a Pokémon is clicked in the list
        pokemonList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedPokemonName = pokemonList.getSelectedValue();
                if (selectedPokemonName != null) {
                    showPokemonDetails(selectedPokemonName);
                }
            }
        });
    }

    // Método para cargar Pokémon desde la base de datos
    public void loadPokemons() {
        try {
            List<Pokemon> pokemons = pokemonService.getAllPokemonsSorted("asc");
            pokemonListModel.clear();
            for (Pokemon pokemon : pokemons) {
                pokemonListModel.addElement(pokemon.getName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading Pokémon data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para buscar un Pokémon por nombre
    private void searchPokemonByName(String name) {
        try {
            Pokemon pokemon = pokemonService.getPokemonByName(name);
            if (pokemon != null) {
                showPokemonDetails(pokemon.getName());
            } else {
                JOptionPane.showMessageDialog(this, "Pokémon not found!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching for Pokémon: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para buscar Pokémon por tipo
    private void searchPokemonsByType(String typeName) {
        try {
            List<Pokemon> pokemons = pokemonService.getPokemonsByType(typeName);
            if (!pokemons.isEmpty()) {
                pokemonListModel.clear();
                pokemons.forEach(pokemon -> pokemonListModel.addElement(pokemon.getName()));
            } else {
                JOptionPane.showMessageDialog(this, "No Pokémon found for this type.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching for Pokémon: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para mostrar los detalles del Pokémon
    private void showPokemonDetails(String pokemonName) {
        try {
            Pokemon pokemon = pokemonService.getPokemonByName(pokemonName);
            if (pokemon != null) {
                StringBuilder details = new StringBuilder();
                details.append("Name: ").append(pokemon.getName()).append("\n");
                details.append("Height: ").append(pokemon.getHeight()).append("\n");
                details.append("Weight: ").append(pokemon.getWeight()).append("\n");
                details.append("Abilities: ");
                pokemon.getAbilities().forEach(ability -> details.append(ability.getName()).append(", "));
                details.append("\nTypes: ");
                pokemon.getTypes().forEach(type -> details.append(type.getName()).append(", "));
                pokemonDetailsArea.setText(details.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error displaying details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Mocking the PokemonService (In a real scenario it will be autowired by Spring)
            PokemonService pokemonService = new PokemonService(); // Initialize with your actual service
            PokedexSwing pokedexSwing = new PokedexSwing(pokemonService);
            pokedexSwing.setVisible(true);

            // Load Pokémon data from the database after startup
            pokedexSwing.loadPokemons();
        });
    }
}
