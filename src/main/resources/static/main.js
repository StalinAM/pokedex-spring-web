const myAtropos = Atropos({
  el: '.my-atropos',
  activeOffset: 40,
  shadowScale: 0
})

const typeColors = {
  normal: '#A8A878',
  fighting: '#C03028',
  flying: '#A890F0',
  poison: '#A040A0',
  ground: '#E0C068',
  rock: '#B8A038',
  bug: '#A8B820',
  ghost: '#705898',
  steel: '#B8B8D0',
  fire: '#F08030',
  water: '#6890F0',
  grass: '#78C850',
  electric: '#F8D030',
  psychic: '#F85888',
  ice: '#98D8D8',
  dragon: '#7038F8',
  dark: '#705848',
  fairy: '#EE99AC'
}
// Referencias a elementos del DOM
const pokemonImage = document.getElementById('pokemon-image')
const pokemonName = document.getElementById('pokemon-name')
const pokemonHeight = document.getElementById('pokemon-height')
const pokemonWeight = document.getElementById('pokemon-weight')
const pokemonAbilities = document.getElementById('pokemon-abilities')
const pokemonTypes = document.getElementById('pokemon-types')
const searchForm = document.getElementById('search-form')
const searchInput = document.getElementById('search-input')
const pokemonButtons = document.getElementById('pokemon-buttons')
// Función para cargar un Pokémon específico

async function getAllPokemons() {
  const url = 'http://localhost:8080/api/pokemons?order=ascendente';

  try {
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error('Error al obtener los Pokémon');
    }
    const data = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error(error);
    return [];
  }
}

// Usage of the function
getAllPokemons().then(pokemons => {
  // Handle the Pokémon data here
  console.log(pokemons);
});

// Usage of the function
getAllPokemons().then(pokemons => {
  // Handle the Pokémon data here
  console.log(pokemons);
});
// Uso de la función
getAllPokemons()
async function loadPokemon(nameOrId) {
  try {
    const response = await fetch(
      `https://pokeapi.co/api/v2/pokemon/${nameOrId.toLowerCase()}`
    )
    const data = await response.json()

    // Actualizar la imagen
    pokemonImage.src = data.sprites.other['official-artwork'].front_default
    pokemonImage.alt = data.name

    // Actualizar información básica
    pokemonName.textContent = data.name
    pokemonHeight.textContent = data.height
    pokemonWeight.textContent = data.weight

    // Actualizar habilidades
    const abilities = data.abilities.map((a) => a.ability.name).join(', ')
    pokemonAbilities.textContent = abilities

    // Actualizar tipos
    pokemonTypes.innerHTML = ''
    data.types.forEach((t) => {
      const typeSpan = document.createElement('span')
      typeSpan.className = 'type-badge'
      typeSpan.textContent = t.type.name
      typeSpan.style.backgroundColor = typeColors[t.type.name] || '#A8A878'
      pokemonTypes.appendChild(typeSpan)
    })
  } catch (error) {
    console.error('Error al cargar el Pokémon:', error)
  }
}
// Función para cargar la lista de Pokémon
async function loadPokemonList() {
  try {
    const response = await fetch('https://pokeapi.co/api/v2/pokemon?limit=151')
    const data = await response.json()

    pokemonButtons.innerHTML = ''
    data.results.forEach((pokemon) => {
      const button = document.createElement('button')
      button.textContent = pokemon.name
      button.onclick = () => {
        searchInput.value = pokemon.name
        loadPokemon(pokemon.name)
      }
      pokemonButtons.appendChild(button)
    })
  } catch (error) {
    console.error('Error al cargar la lista de Pokémon:', error)
  }
}
// Event listeners
searchForm.onsubmit = (e) => {
  e.preventDefault()
  if (searchInput.value.trim()) {
    loadPokemon(searchInput.value.trim())
  }
}
// Cargar la lista inicial de Pokémon
loadPokemonList()
getAllPokemons()
