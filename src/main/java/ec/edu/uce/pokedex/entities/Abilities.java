package ec.edu.uce.pokedex.entities;


import jakarta.persistence.*;


@Entity
public class Abilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column private String name;

   //CONSTRUCTOR
    public Abilities() {
    }

    // GETTERS AND SETTERS

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
