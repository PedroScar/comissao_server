package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.VisaoGeralVO

interface ContadoresDashboardViewRepository {
    suspend fun carregarVisaoGeral(clienteId: Int): DbResponse<VisaoGeralVO>
}