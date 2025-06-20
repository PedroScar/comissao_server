package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PromocaoVO

interface PromocoesRepository {
    suspend fun editarPromocao(promocao: PromocaoVO): DbResponse<PromocaoVO>
    suspend fun listarPromocoesAtivas(clienteId: Int): DbResponse<List<PromocaoVO>>
    suspend fun carregarPromocoes(termo: String = "", clienteId: Int): DbResponse<List<PromocaoVO>>
    suspend fun carregarPromocoesMaisUtilizadas(clienteId: Int): DbResponse<List<PromocaoVO>>
    suspend fun carregarPromocao(promocaoId: Int, clienteId: Int): DbResponse<PromocaoVO>
    suspend fun contarPromocoesAtivas(clienteId: Int): DbResponse<Int>
    suspend fun criarPromocao(promocao: PromocaoVO): DbResponse<PromocaoVO>
    suspend fun encerrarPromocao(promocaoId: Int, clienteId: Int): DbResponse<PromocaoVO>
}