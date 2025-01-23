package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO

interface PromocoesRepository {
    suspend fun carregarPromocoes(clienteId: Int): DbResponse<List<PromocaoVO>>
    suspend fun carregarPromocao(promocaoId: Int, clienteId: Int): DbResponse<PromocaoVO>
    suspend fun contagemDePromocoesAtivas(clienteId: Int): DbResponse<Int>
    suspend fun criarPromocao(promocao: PromocaoVO): DbResponse<PromocaoVO>
}