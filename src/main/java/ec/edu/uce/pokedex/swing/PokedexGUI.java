package ec.edu.uce.pokedex.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.ComponentOrientation;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.DebugGraphics;

public class PokedexGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PokedexGUI frame = new PokedexGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PokedexGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 873, 547);
        contentPane = new JPanel();
        contentPane.setBackground(Color.RED);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setName("POKEMON\r\nHABILIDAD\r\n");
        panel.setToolTipText("");
        panel.setBounds(23, 49, 302, 448);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("ORDENAR:");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        lblNewLabel.setBounds(10, 414, 68, 23);
        panel.add(lblNewLabel);

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setForeground(Color.BLUE);
        comboBox_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"ASCENDENTE", "DESCENDENTE"}));
        comboBox_1.setBounds(75, 414, 109, 23);
        panel.add(comboBox_1);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.setForeground(Color.BLUE);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        btnNewButton.setBounds(194, 414, 89, 23);
        panel.add(btnNewButton);

        JList list_Pokemons = new JList();
        list_Pokemons.setVisibleRowCount(20);
        list_Pokemons.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        list_Pokemons.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_Pokemons.setModel(new AbstractListModel() {
            String[] values = new String[] {};
            public int getSize() {
                return values.length;
            }
            public Object getElementAt(int index) {
                return values[index];
            }
        });
        list_Pokemons.setBounds(10, 48, 273, 355);
        panel.add(list_Pokemons);

        JLabel lblNewLabel_1 = new JLabel("POKEDEX");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBackground(Color.BLUE);
        lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblNewLabel_1.setBounds(20, 11, 242, 26);
        panel.add(lblNewLabel_1);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(347, 49, 500, 169);
        contentPane.add(panel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(346, 229, 501, 268);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        JLabel Id_text = new JLabel("ID:");
        Id_text.setBounds(30, 10, 46, 14);
        panel_2.add(Id_text);

        JLabel heigt_text = new JLabel("ALTURA:");
        heigt_text.setBounds(30, 90, 73, 14);
        panel_2.add(heigt_text);

        JLabel weigth_text = new JLabel("PESO: ");
        weigth_text.setBounds(30, 130, 73, 14);
        panel_2.add(weigth_text);

        JLabel name_text = new JLabel("NOMBRE:");
        name_text.setBounds(30, 50, 73, 14);
        panel_2.add(name_text);

        JLabel id_pokemon = new JLabel("New label");
        id_pokemon.setBounds(113, 10, 148, 14);
        panel_2.add(id_pokemon);

        JLabel name_pokemon = new JLabel("New label");
        name_pokemon.setBounds(113, 50, 148, 14);
        panel_2.add(name_pokemon);

        JLabel heigth_pokemon = new JLabel("New label");
        heigth_pokemon.setBounds(113, 90, 148, 14);
        panel_2.add(heigth_pokemon);

        JLabel weight_pokemon = new JLabel("New label");
        weight_pokemon.setBounds(113, 130, 148, 14);
        panel_2.add(weight_pokemon);

        JList list = new JList();
        list.setBounds(30, 195, 155, 62);
        panel_2.add(list);

        JLabel abilities_text = new JLabel("HABILIDADES: ");
        abilities_text.setBounds(30, 170, 108, 14);
        panel_2.add(abilities_text);

        JLabel types_text = new JLabel("TIPOS");
        types_text.setBounds(242, 170, 73, 14);
        panel_2.add(types_text);

        JList list_1 = new JList();
        list_1.setBounds(229, 195, 155, 62);
        panel_2.add(list_1);

        JButton search = new JButton("BUSCAR");
        search.setForeground(Color.BLUE);
        search.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        search.setBounds(609, 17, 89, 23);
        contentPane.add(search);

        textField = new JTextField();
        textField.setForeground(Color.BLUE);
        textField.setBounds(351, 18, 224, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JComboBox comboBox = new JComboBox();
        comboBox.setForeground(Color.BLUE);
        comboBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"POKEMON", "TIPO", "HABILIDAD"}));
        comboBox.setBounds(146, 17, 179, 23);
        contentPane.add(comboBox);

        JLabel text_search_for = new JLabel("CONSULTAR POR: ");
        text_search_for.setForeground(Color.BLUE);
        text_search_for.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        text_search_for.setBounds(23, 17, 120, 19);
        contentPane.add(text_search_for);
    }
}
