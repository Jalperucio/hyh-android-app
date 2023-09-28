# Rick & Morty Compose

En este proyecto se presenta de forma organizada cómo montar una arquitectura MVVM con separación completa
de capas, en el que utilizando la api de Rick & Morty podemos acceder a la lista de personajes implementando 
una UI basada en Jetpack Compose en la que dicha lista de personajes se gestiona con una paginación infinita
implementada utilizando la api de Paging3.

Las tecnologías utilizadas en este proyecto, son las siguientes:
- **Koin**, como sistema de inyección de dependencias.
- **Retrofit**, como sistema para obtención de datos de una api REST (Rick & Morty).
- **Paging3**, como herramienta para implementación rápida y eficiente de paginación infinita.
- **ViewModels**, como sistema para comunicación entre capas de acceso a datos y capa de UI.
- **Coil**, para pintado de imágenes asíncronas con compose.
- **JetPack Compose**, como sistema de implementación de interfaz de usuario declarativa y reactiva.

## Arquitectura

Este proyecto está organizado siguiendo una arquitectura MVVM organizada en cuatro capas totalmente
diferenciadas y separadas entre sí, siendo la dependencia similar a la que podemos ver en el siguiente 
esquema:


Como podemos ver, cada capa sólo puede ver a la inmediatamente por debajo
en su jerarquía, salvo model, que es transversal al resto.

Pasemos a explicar qué tenemos en cada capa:

- **model**, es la capa en la que definimos tanto las entidades de dominio
  (entidades que utilizaremos por todo el proyecto y que son la representación
  la nuestras entidades a utilizar, por ejemplo, en presentación). En esta
  capa además tenemos una interfaz llamada **KoinModuleLoader** mediante la cual
  podemos realizar la inyección de dependencias con Koin de una forma bastante 
  sencilla.
- **data**, en esta capa gestionamos toda la obtención de datos y su preparación en
  vistas a pasarlos a capas superiores, y por tanto, a UI. Aquí además de los accesos a 
  datos, iniciamos la clase que **gestionará la paginación infinita de items de lista
  mediante el uso de paging3**
- **domain**, en esta capa establecemos los casos de uso, que, utilizando los repositorios
definidos en la capa de data, nos va a permitir comunicar con la capa de UI los resultados 
obtenidos desde los distintos orígenes de datos.
- **app**, esta es la capa en la que establecemos la definición de viewmodels y definiciones de UI.
En esta capa definiremos cualquier elemento necesario para la implementación de la UI de la app.

## Curiosidades de la implementación (algunas propias del proyecto, otras relativos al curso)

### Curiosidades del proyecto

La principal curiosidad de este proyecto reside en que "no sigue exactamente" un patrón MVVM clásico,
por ejemplo, en un patrón clásico, en el módulo de domain se definen las interfaces de repositorio,
y en la capa de data, se implementan dichos repositorios, siendo visibles todas las capas dentre sí.

En este proyecto, la capa de data (por simplicidad) es la que contiene tanto las interfaces de repositorio
que domain ve, así como su implementación, que son clases internas, que domain no ve.
de este modo garantizamos que no se tiene acceso indebido a elementos que no deben ser utilizados
fuera de su ámbito (lo conseguimos haciendo que las clases e interfaces que no queremos que sean 
accesibles sean marcadas como internal).

Otra curiosidad del proyecto es la separación total de capas (app, por ejemplo, no sabe de la existencia de data),
con respecto a lo anterior, para poder gestionar la inyección de dependencias (la gestiona app...),
se utiliza una interfaz llamada KoinModuleLoader, que, definiendo objetos en cada capa accesibles
podemos inyectar hacia arriba **y de forma ordenada** (recordemos que Koin necesita un orden perfecto 
en el proceso de definición del árbol de dependencias) hasta llegar al módulo app.

### Curiosidades relativas al curso

En este proyecto se ha implementado la api de Rick & Morty de forma muy superficial, ya que el objetivo 
es mostrar cómo realizar la lectura de datos de internet utilizando la api de retrofit, y mostrar un ejemplo de
paginación infinita, que es donde nos vamos a centrar en este README, explicando paso a paso cómo llevarlo a cabo.

Vamos a presuponer que ya tenemos preparado el datastore que encapsula las peticiones a api, con
su parámetro page establecido (nos determinará la página a obtener). Debemos tener en cuenta que la 
api de Rick & Morty NO permite modificar el número de elementos que nos devolverá cada página,
devolviendo siempre 20 elementos. 

Veamos por pasos cómo desplegar Paginación infinita:

1. En la capa de data, crearemos una entidad que nos permita gestioanr el paginado. Esta clase,
salvo que el sistema de paginado sea muy "especial", prácticamente no habrá que tocar nada. Veamos 
el código de dicha clase (atendamos a los comentarios, ya que nos indicarán dónde debemos aplicar 
cambios):

```kotlin
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hiberus.handh.data.feature.characters.repository.CharactersRepository
import com.hiberus.handh.model.feature.characters.RickAndMortyCharacter
import timber.log.Timber

open class CharactersPaging(
    private val repository: CharactersRepository
): PagingSource<Int, RickAndMortyCharacter>() {
    // Este método es as is, no se toca salvo lo estrictamente necesario
    // (obtención de la clave de páginación) :D
    override fun getRefreshKey(state: PagingState<Int, RickAndMortyCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    // Aquí sí hay que tocar un poquito...
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyCharacter> =
        try {
            val page = params.key ?: 1
            val limit = params.loadSize
            Timber.tag("Paging").i("Page: $page")
            // Puede ser un flow, o una suspend que se traiga cosas de cualquier sitio,
            // es un repositorio, por tanto...
            val response = repository.getAllCharacters(
                page = page,
            )

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}
```

Evidentemente, además de los cambios que se nos indican en el código mediante comentarios, deberemos
modificar el tipo de la clase devuelta (en el ejemplo RickAndMortyCharacter, deberíamos sustituirlo por 
la clase que toque, por ejemplo, RickAndMortyLocation). 

Si nos fijamos un poco, hemos dejado (por si acaso) el campo limit (tamaño de página)

2. En la capa de domain, crearemos un caso de uso, que nos va a permitir generar un Flow que 
va a ser el responsable del sistema de paginado, a partir de ciertas configuraciones que Pager 
nos va a proporcionar.
```kotlin
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.hiberus.handh.data.feature.characters.paging.CharactersPaging

open class AllCharactersUseCase(
    private val pagingSource: CharactersPaging
) {
    operator fun invoke(limit: Int) = Pager(
        // aquí el tamaño de página se comporta de forma curiosa,
        // la primera petición (es así) multiplica el pagesize * 3
        config = PagingConfig(
            pageSize = limit,
            prefetchDistance = limit / 2 // aquí recolectamos a la mitad, asi va más "fluido"
        ),
        pagingSourceFactory = {
            pagingSource
        }
    ).flow // Si nos fijamos creamos un flow directamente desde el caso de uso.
}
```

Este caso de uso, salvo cambios que estimemos oportunos, no es preciso tocarlo.
**Como curiosidad**, debemos apreciar el parámetro prefetchDistance,
que nos va a servir para anticiparnos, en este caso, si estamos haciendo scroll
y llegamos a la mitad de la colección, pedirá más elementos, a fin de que 
todo se vea fluido y no vayamos dando saltos en la carga.

3. En la capa de presentación ya tenemos lo último, en este caso, deberemos definir
tanto un viewmodel, como ya a nivel de UI el pintado de elementos. Veámoslos cada 
uno por partes.
- **ViewModel**, en el viewmodel simplemente estableceremos una propiedad
 que será el flow encargado de proporcionar los datos a la UI, simplemente, copiamos y pegamos:
```kotlin
open class CharactersViewModel(
    allCharactersUseCase: AllCharactersUseCase
): ViewModel() {
    val allCharacters: Flow<PagingData<RickAndMortyCharacter>> = allCharactersUseCase(20)
}
```
   
- **UI**, en esta capa, concretamente en el composable que vamos a utilizar como lista
infinita, definiremos el código necesario, el cual, tan sólo incluye
la definición de la variable que vamos a recolectar y que va a ser la que
en último término va a gestionar la paginación. Luego, estableceremos la configuración
(mínima) necesaria para el LazyColumn encargado de pintar los datos paginados:

```kotlin
@Composable
fun MiListaInfinita(viewModel: CharactersViewModel = koinViewModel()){
  val characters = viewModel.allCharacters.collectAsLazyPagingItems()
 
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
  ){
    items(
      count = characters.itemCount,
      key = characters.itemKey { character -> character.id }
    ){ characterIndex ->
      characters[characterIndex]?.let { item ->
        RickAndMortyCharacterItem(
          character = item,
          modifier = Modifier.fillMaxWidth()
        )
      }
    }
  } 
}
```

Como vemos, para gestionar la paginación infinita tan solo debemos pedir al viewmodel que recolecte
utilizando el método **collectAsLazyPagingItems()**, el cual se sincroniza de forma automática con el
LazyColumn, al que, utilizando la función items del LazyListScope, tan solo debemos pasar el recuento de items (parámetro **count**) y el 
identificador único de cada item (parámetro **key**).

Luego, en el cuerpo de items, para obtener el item en cuestión, como vemos tan solo debemos acceder
a él a través de la posición que se nos proporciona, Quedando ya, para finalizar, pintar cada item
con el composable adecuado.

**Consideraciones Finales**: Como vemos, la paginación infinita en compose es bastante simple, se propone
(voluntariamente), intentar llevar a cabo dicha implementación utilizando vistas clásicas
(un ejemplo de como llevar este proceso a cabo se proporciona en [este enlace](https://innovatingideas.medium.com/endless-list-scrolling-with-recyclerview-or-listview-in-kotlin-in-4-steps-with-the-easiest-way-ad8ab1e204e7)).
