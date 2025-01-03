package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PerfilDeAcessoVO
import com.pscarpellini.models.vos.PromocaoVO

interface PerfisDeAcessoRepository {
    suspend fun carregarPerfis(clienteId: Int): DbResponse<List<PerfilDeAcessoVO>>
}