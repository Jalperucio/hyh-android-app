package com.hiberus.handh.data.feature.characters.paging

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