package ec.edu.uce.pokedex.swing;

import ec.edu.uce.pokedex.entities.Pokemon;
import ec.edu.uce.pokedex.service.PokemonService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.net.URL;
import java.util.List;

public class PokedexGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchField;
    private JList<String> listPokemons;
    private DefaultListModel<String> pokemonListModel;
    private JLabel idPokemon, namePokemon, heightPokemon, weightPokemon, imagePokemon;
    private JList<String> abilitiesList, typesList;
    private PokemonService pokemonService;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PokemonService pokemonService = new PokemonService(); // Replace with your actual service initialization
                PokedexGUI frame = new PokedexGUI(pokemonService);
                frame.setVisible(true);
                frame.loadPokemons(); // Load Pokémon data on startup
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public PokedexGUI(PokemonService pokemonService) {
        this.pokemonService = pokemonService;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 873, 547);
        contentPane = new JPanel();
        contentPane.setBackground(Color.RED);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setBounds(43, 51, 302, 448);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("ORDENAR:");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        lblNewLabel.setBounds(10, 414, 68, 23);
        panel.add(lblNewLabel);

        JComboBox<String> sortComboBox = new JComboBox<>(new String[]{"ASCENDENTE", "DESCENDENTE"});
        sortComboBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        sortComboBox.setBounds(75, 414, 109, 23);
        panel.add(sortComboBox);

        JButton sortButton = new JButton("OK");
        sortButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        sortButton.setBounds(194, 414, 89, 23);
        panel.add(sortButton);

        pokemonListModel = new DefaultListModel<>();
        listPokemons = new JList<>(pokemonListModel);
        listPokemons.setVisibleRowCount(20);
        listPokemons.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listPokemons);
        scrollPane.setBounds(10, 48, 273, 355);
        panel.add(scrollPane);

        JLabel lblTitle = new JLabel("POKEDEX");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblTitle.setBounds(20, 11, 242, 26);
        panel.add(lblTitle);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBounds(391, 278, 456, 219);
        contentPane.add(detailsPanel);
        detailsPanel.setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 49, 46, 14);
        detailsPanel.add(lblId);

        JLabel lblHeight = new JLabel("ALTURA:");
        lblHeight.setBounds(30, 129, 73, 14);
        detailsPanel.add(lblHeight);

        JLabel lblWeight = new JLabel("PESO:");
        lblWeight.setBounds(30, 169, 73, 14);
        detailsPanel.add(lblWeight);

        JLabel lblName = new JLabel("NOMBRE:");
        lblName.setBounds(30, 89, 73, 14);
        detailsPanel.add(lblName);

        idPokemon = new JLabel("-");
        idPokemon.setBounds(113, 49, 148, 14);
        detailsPanel.add(idPokemon);

        namePokemon = new JLabel("-");
        namePokemon.setBounds(113, 89, 148, 14);
        detailsPanel.add(namePokemon);

        heightPokemon = new JLabel("-");
        heightPokemon.setBounds(113, 129, 148, 14);
        detailsPanel.add(heightPokemon);

        weightPokemon = new JLabel("-");
        weightPokemon.setBounds(113, 169, 148, 14);
        detailsPanel.add(weightPokemon);

        abilitiesList = new JList<>(new DefaultListModel<>());
        abilitiesList.setBounds(271, 36, 155, 62);
        detailsPanel.add(abilitiesList);

        JLabel lblAbilities = new JLabel("HABILIDADES:");
        lblAbilities.setBounds(271, 11, 108, 14);
        detailsPanel.add(lblAbilities);

        typesList = new JList<>(new DefaultListModel<>());
        typesList.setBounds(271, 140, 155, 62);
        detailsPanel.add(typesList);

        JLabel lblTypes = new JLabel("TIPOS:");
        lblTypes.setBounds(271, 115, 73, 14);
        detailsPanel.add(lblTypes);

        JButton searchButton = new JButton("BUSCAR");
        searchButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        searchButton.setBounds(609, 17, 89, 23);
        contentPane.add(searchButton);

        searchField = new JTextField();
        searchField.setBounds(351, 18, 224, 20);
        contentPane.add(searchField);
        searchField.setColumns(10);

        JComboBox<String> filterComboBox = new JComboBox<>(new String[]{"POKEMON", "TIPO", "HABILIDAD"});
        filterComboBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        filterComboBox.setBounds(146, 17, 179, 23);
        contentPane.add(filterComboBox);

        JLabel lblFilter = new JLabel("CONSULTAR POR:");
        lblFilter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        lblFilter.setBounds(23, 19, 120, 19);
        contentPane.add(lblFilter);

        // Action Listeners
        searchButton.addActionListener(e -> searchPokemon(filterComboBox));

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(508, 62, 225, 200);
        contentPane.add(panel_1);

        imagePokemon = new JLabel();
        panel_1.add(imagePokemon);
        imagePokemon.setBackground(new Color(240,240,240));
        sortButton.addActionListener(e -> sortPokemons(sortComboBox.getSelectedItem().toString()));
        listPokemons.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showPokemonDetails(listPokemons.getSelectedValue());
            }
        });
    }

    public void loadPokemons() {
        try {
            String query = searchField.getText();
            if(query.isEmpty()) {
                List<Pokemon> pokemons = pokemonService.getAllPokemonsSorted("asc");
                pokemonListModel.clear();
                pokemons.forEach(pokemon -> pokemonListModel.addElement(pokemon.getName()));
            }
            if(!query.isEmpty()){

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading Pokémon data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchPokemon(JComboBox<String> filterComboBox) {
        try {
            String selectFilter = filterComboBox.getSelectedItem().toString();
            String query = searchField.getText();
            List<Pokemon> pokemons = pokemonService.getAllPokemonsSorted("asc");
            switch (selectFilter) {
                case "POKEMON":
                    // Lógica para buscar por nombre de Pokémon
                    pokemonListModel.clear();
                    pokemons.forEach(pokemon -> {
                        if(pokemon.getName().contains(query)) {
                            pokemonListModel.addElement(pokemon.getName());
                        }
                    });
                    searchField.setText("");
                    break;
                case "TIPO":
                    // Lógica para buscar por tipo de Pokémon
                    pokemons = pokemonService.getPokemonsByType(query);
                    pokemonListModel.clear();
                    pokemons.forEach(pokemon -> {
                        System.out.println(pokemon.getName());
                        pokemonListModel.addElement(pokemon.getName());
                    });
                    searchField.setText("");
                    break;
                case "HABILIDAD":
                    // Lógica para buscar por habilidad de Pokémon
                    pokemons = pokemonService.getPokemonsByAbility(query);
                    pokemonListModel.clear();
                    pokemons.forEach(pokemon -> {
                        System.out.println(pokemon.getName());
                        pokemonListModel.addElement(pokemon.getName());
                    });
                    searchField.setText("");
                    break;
                default:
                    System.out.println("Filtro desconocido");
                    break;
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar Pokémon: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortPokemons(String order) {
        try {
            List<Pokemon> pokemons = pokemonService.getAllPokemonsSorted(order.toLowerCase());
            pokemonListModel.clear();
            pokemons.forEach(pokemon -> pokemonListModel.addElement(pokemon.getName()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al ordenar Pokémon: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showPokemonDetails(String pokemonName) {
        try {
            Pokemon pokemon = pokemonService.getPokemonByName(pokemonName);
            if (pokemon != null) {
                idPokemon.setText(String.valueOf(pokemon.getId()));
                namePokemon.setText(pokemon.getName());
                heightPokemon.setText(String.valueOf(pokemon.getHeight()));
                weightPokemon.setText(String.valueOf(pokemon.getWeight()));

                DefaultListModel<String> abilitiesModel = new DefaultListModel<>();
                pokemon.getAbilities().forEach(ability -> abilitiesModel.addElement(ability.getName()));
                abilitiesList.setModel(abilitiesModel);

                DefaultListModel<String> typesModel = new DefaultListModel<>();
                pokemon.getTypes().forEach(type -> typesModel.addElement(type.getName()));
                typesList.setModel(typesModel);

                // Cargar y mostrar la imagen del Pokémon
                ImageIcon pokemonImage = new ImageIcon(new URL(pokemon.getImage())); // getImageUrl() debe devolver la URL de la imagen
                Image image = pokemonImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon scaledImagePokemon = new ImageIcon(image);
                imagePokemon.setIcon(scaledImagePokemon);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar detalles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
