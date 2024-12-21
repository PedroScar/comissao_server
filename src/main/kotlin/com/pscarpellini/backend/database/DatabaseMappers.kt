package com.pscarpellini.backend.database

import com.pscarpellini.backend.database.daos.ClienteDAO
import com.pscarpellini.backend.database.daos.ContratoDAO
import com.pscarpellini.backend.database.daos.ServicoDAO
import com.pscarpellini.backend.models.vos.ClienteVO
import com.pscarpellini.backend.models.vos.Contrato
import com.pscarpellini.backend.models.vos.Servico

fun clienteDaoToModel(dao: ClienteDAO) = ClienteVO(
    dao.id.value,
    dao.nome,
    dao.endereco,
    dao.cnpj,
    dao.email,
    dao.telefone,
    dao.status
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

//fun contaDaoToModel(dao: ContaDAO) = Conta(
//    dao.id.value,
//    clienteDaoToModel(dao.clienteId),
//    dao.tipoConta,
//    dao.nome,
//    dao.cpf,
//    dao.endereco,
//    dao.email,
//    dao.telefone,
//    dao.status,
//    dao.usuario,
//    dao.senha,
//    dao.dataCriacao
//)
