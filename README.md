![](/src/main/resources/images/preview.png)

Este proyecto es una aplicación web desarrollada con Spring Boot y Spring Web que permite a los usuarios explorar y obtener información detallada sobre diferentes Pokémon. La aplicación consume datos de la [PokeAPI](https://pokeapi.co/) para proporcionar información actualizada sobre cada Pokémon.

---

## Índice

- [Índice](#índice)
- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Instalación](#instalación)

---

## Características

- Visualización de una lista de Pokémon con sus imágenes y detalles básicos.
- Búsqueda de Pokémon por nombre de Pokédex.
- Visualización de detalles específicos de cada Pokémon, incluyendo tipo y habilidades.

---

## Tecnologías Utilizadas

- **Backend:**

  - Java
  - Spring Boot
  - Spring Web

- **Frontend:**

  - HTML5 y CSS3
  - JavaScript

- **Base de Datos:**
  - PostgreSQL

---

## Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/pokedex-spring.git
   cd pokedex-spring
   ```
2. **Base de datos con docker**
   ```bash
   docker run -d --name pokedex-spring -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres
   ```
   - crear base de datos
   ```sql
   create database pokedexdb;
   ```
