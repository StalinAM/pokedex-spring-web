const myAtropos = Atropos({
  el: '.my-atropos',
  activeOffset: 40,
  shadowScale: 0
})

// Referencias a elementos del DOM
const listPokemonContainer = document.querySelector('.container-list-pokemon')

const pokemonName = document.querySelector('.pokemon-name')
const pokemonHeight = document.querySelector('.pokemon-height')
const pokemonWeight = document.querySelector('.pokemon-weight')
const pokemonAbilities = document.querySelector('.pokemon-abilities')
const pokemonTypes = document.querySelector('.pokemon-types')
const pokemonImage = document.querySelector('.pokemon-image')

const searchForm = document.querySelector('.form-shearch')
const searchInput = document.querySelector('.pokemon-input')
const searchType = document.querySelector('.form-shearch select')
const btnForm = document.querySelector('.btn-form')

// Función para cargar un Pokémon específico
async function getPokemonsByFilter(filterType, query) {
  let url = 'http://localhost:8080/api/pokemons'

  if (query.trim() !== '') {
    switch (filterType) {
      case 'nombre':
        url += `/${query}`
        break
      case 'tipo':
        url += `/type/${query}`
        break
      case 'habilidad':
        url += `/ability/${query}`
        break
    }
  }
  console.log(url)

  try {
    const response = await fetch(url)
    if (!response.ok) throw new Error('No se encontraron Pokémon')
    return await response.json()
  } catch (error) {
    console.error(error)
    return []
  }
}

function showPokemonDetails(pokemon) {
  pokemonName.textContent = pokemon.name
  pokemonHeight.textContent = pokemon.height
  pokemonWeight.textContent = pokemon.weight
  pokemonAbilities.textContent = pokemon.abilities
      .map((ability) => ability.name)
      .join(', ')
  pokemonTypes.innerHTML = ''
  pokemon.types.forEach((type) => {
    const typeItem = document.createElement('li')
    typeItem.className = `type-${type.name.toLowerCase()}` // Usar el tipo como clase para el color
    typeItem.textContent = type.name
    pokemonTypes.appendChild(typeItem)
  })
  pokemonImage.src = pokemon.image
}

async function updatePokemonList() {
  const filterType = searchType.value
  const query = searchInput.value.trim()

  const pokemons = await getPokemonsByFilter(filterType, query)
  listPokemonContainer.innerHTML = ''
  console.log(pokemons)

  if (pokemons.length === 0) {
    listPokemonContainer.innerHTML = '<p>No se encontraron Pokémon</p>'
    return
  }
  if (filterType === 'nombre') {
    const pokemonItem = document.createElement('li')
    pokemonItem.textContent = pokemons.name
    pokemonItem.className = 'pokemon-item'
    pokemonItem.addEventListener('click', () => showPokemonDetails(pokemons))
    listPokemonContainer.appendChild(pokemonItem)
    return
  }

  pokemons.forEach((pokemon) => {
    const pokemonItem = document.createElement('li')
    pokemonItem.textContent = pokemon.name
    pokemonItem.className = 'pokemon-item'
    pokemonItem.addEventListener('click', () => showPokemonDetails(pokemon))
    listPokemonContainer.appendChild(pokemonItem)
  })
}
// Manejar el evento de búsqueda
searchForm.addEventListener('submit', (event) => {
  event.preventDefault()
  updatePokemonList()
})

// Cargar la lista inicial
document.addEventListener('DOMContentLoaded', updatePokemonList)
