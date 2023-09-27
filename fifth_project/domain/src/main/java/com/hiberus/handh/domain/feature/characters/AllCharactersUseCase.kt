package com.hiberus.handh.domain.feature.characters

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