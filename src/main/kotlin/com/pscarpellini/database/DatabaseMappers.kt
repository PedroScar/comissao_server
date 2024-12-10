package com.pscarpellini.database

import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.daos.ContratoDAO
import com.pscarpellini.database.daos.ServicoDAO
import com.pscarpellini.models.Cliente
import com.pscarpellini.models.Conta
import com.pscarpellini.models.Contrato
import com.pscarpellini.models.Servico

fun clienteDaoToModel(dao: ClienteDAO) = Cliente(
    dao.id.value,
    dao.nome,
    dao.endereco,
    dao.cnpj,
    dao.email,
    dao.telefone,
    dao.status,
    dao.dataCriacao
)

fun servicoDaoToModel(dao: ServicoDAO) = Servico(
    dao.id.value,
    dao.nome,
    dao.dataCriacao
)

fun contratoDaoToModel(dao: ContratoDAO) = Contrato(
    dao.id.value,
    dao.preco,
    clienteDaoToModel(dao.clienteId),
    servicoDaoToModel(dao.servicoId),
    dao.dataContrato
)

fun contaDaoToModel(dao: ContaDAO) = Conta(
    dao.id.value,
    clienteDaoToModel(dao.clienteId),
    dao.tipoConta,
    dao.nome,
    dao.cpf,
    dao.endereco,
    dao.email,
    dao.telefone,
    dao.status,
    dao.usuario,
    dao.senha,
    dao.dataCriacao
)
