package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.PerfilDeAcessoDAO
import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.tables.PerfisDeAcessoTable
import com.pscarpellini.database.utils.promocaoDaoToModel
import com.pscarpellini.database.tables.PromocoesTable
import com.pscarpellini.database.utils.perfilDeAcessoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PerfilDeAcessoVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.repositories.interfaces.PerfisDeAcessoRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.suspendTransaction

class PerfisDeAcessoRepositoryPostgres : PerfisDeAcessoRepository {
    override suspend fun carregarPerfis(clienteId: Int): DbResponse<List<PerfilDeAcessoVO>> = suspendTransaction {
        val listaPerfisDeAcesso = PerfilDeAcessoDAO
            .find { (PerfisDeAcessoTable.clienteId eq clienteId) }
            .map(::perfilDeAcessoDaoToModel)

        DbResponse.Successo(listaPerfisDeAcesso)
    }
}