package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.utils.contadoresViewToVisaoGeralVO
import com.pscarpellini.database.views.ContadoresDashboardView
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.VisaoGeralVO
import com.pscarpellini.repositories.interfaces.ContadoresDashboardViewRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.*
import kotlin.Int

class ContadoresDashboardViewRepositoryPostgres : ContadoresDashboardViewRepository {

    override suspend fun carregarVisaoGeral(clienteId: Int): DbResponse<VisaoGeralVO> = suspendTransaction {
        val contadores = ContadoresDashboardView
            .selectAll().where { ContadoresDashboardView.clienteId eq clienteId }
            .map { row -> contadoresViewToVisaoGeralVO(row) }
            .firstOrNull()

        return@suspendTransaction if(contadores != null) DbResponse.Successo(contadores)
        else DbResponse.Erro(data = null, message = "Ocorreu um erro ao recuperar os contadores")
    }
}