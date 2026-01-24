package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PromocaoVO

import com.pscarpellini.models.response.PromocoesPaginacao

interface PromocoesRepository {
    suspend fun carregarTotalTransacoesPorConta(contaIds: List<Int>): DbResponse<List<Triple<Int, Int, Int>>>
    suspend fun editarPromocao(promocao: PromocaoVO): DbResponse<PromocaoVO>
    suspend fun listarPromocoesAtivas(clienteId: Int): DbResponse<List<PromocaoVO>>
    suspend fun carregarPromocoes(termo: String = "", clienteId: Int): DbResponse<List<PromocaoVO>>
    suspend fun carregarPromocoesPaginacao(clienteId: Int, pagina: Int): DbResponse<PromocoesPaginacao>
    suspend fun carregarPromocoesMaisUtilizadas(clienteId: Int): DbResponse<List<PromocaoVO>>
    suspend fun carregarPromocao(promocaoId: Int, clienteId: Int): DbResponse<PromocaoVO>
    suspend fun contarPromocoesAtivas(clienteId: Int): DbResponse<Int>
    suspend fun criarPromocao(promocao: PromocaoVO): DbResponse<PromocaoVO>
    suspend fun encerrarPromocao(promocaoId: Int, clienteId: Int): DbResponse<PromocaoVO>
    suspend fun removerPromocao(promocaoId: Int, clienteId: Int): DbResponse<Unit>
}