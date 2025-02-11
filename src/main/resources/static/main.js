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

// Función para obtener Pokémon
async function getPokemonsByFilter(filterType, query) {
  let url = 'http://localhost:8080/api/pokemons/all' // Ahora usa el endpoint correcto

  if (query.trim() !== '') {
    switch (filterType) {
      case 'nombre':
        url = `http://localhost:8080/api/pokemons/${query}`
        break
      case 'tipo':
        url = `http://localhost:8080/api/pokemons/type/${query}`
        break
      case 'habilidad':
        url = `http://localhost:8080/api/pokemons/ability/${query}`
        break
    }
  }

  console.log(`Fetching: ${url}`)

  try {
    const response = await fetch(url)
    if (!response.ok) throw new Error('No se encontraron Pokémon')

    const data = await response.json()
    return Array.isArray(data) ? data : [data] // Asegurar que siempre sea un array
  } catch (error) {
    console.error(error)
    return []
  }
}

// Mostrar detalles de un Pokémon
function showPokemonDetails(pokemon) {
  pokemonName.textContent = pokemon.name || 'Desconocido'
  pokemonHeight.textContent = pokemon.height || 'N/A'
  pokemonWeight.textContent = pokemon.weight || 'N/A'
  pokemonAbilities.textContent = (pokemon.abilities || [])
      .map((ability) => ability.name)
      .join(', ')

  pokemonTypes.innerHTML = ''
  if (pokemon.types) {
    pokemon.types.forEach((type) => {
      const typeItem = document.createElement('li')
      typeItem.className = `type-${type.name.toLowerCase()}`
      typeItem.textContent = type.name
      pokemonTypes.appendChild(typeItem)
    })
  }

  pokemonImage.src = pokemon.image || 'default-image.png'
}

// Actualizar la lista de Pokémon
async function updatePokemonList() {
  try {
    const filterType = searchType.value
    const query = searchInput.value.trim()

    const pokemons = await getPokemonsByFilter(filterType, query)
    listPokemonContainer.innerHTML = ''

    if (!pokemons || pokemons.length === 0) {
      listPokemonContainer.innerHTML = '<p>No se encontraron Pokémon</p>'
      return
    }

    pokemons.forEach((pokemon) => {
      const pokemonItem = document.createElement('li')
      pokemonItem.textContent = pokemon.name
      pokemonItem.className = 'pokemon-item'
      pokemonItem.addEventListener('click', () => showPokemonDetails(pokemon))
      listPokemonContainer.appendChild(pokemonItem)
    })
  } catch (error) {
    console.error('Error al cargar Pokémon:', error)
    listPokemonContainer.innerHTML = '<p>Error al obtener datos. Inténtalo más tarde.</p>'
  }
}

// Manejar el evento de búsqueda
searchForm.addEventListener('submit', (event) => {
  event.preventDefault()
  updatePokemonList()
})

// Cargar la lista inicial
document.addEventListener('DOMContentLoaded', updatePokemonList)
