package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PromocaoVO

interface PromocoesRepository {
    suspend fun carregarPromocoes(clienteId: Int): DbResponse<List<PromocaoVO>>
}