package com.pscarpellini.database

import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.daos.ContratoDAO
import com.pscarpellini.database.daos.ServicoDAO
import com.pscarpellini.models.vos.ClienteVO
import com.pscarpellini.models.vos.ContratoVO
import com.pscarpellini.models.vos.ServicoVO

fun clienteDaoToModel(dao: ClienteDAO) = ClienteVO(
    dao.id.value,
    dao.nome,
    dao.endereco,
    dao.cnpj,
    dao.email,
    dao.telefone,
    dao.status
)

fun servicoDaoToModel(dao: ServicoDAO) = ServicoVO(
    dao.id.value,
    dao.nome,
    dao.dataCriacao
)

fun contratoDaoToModel(dao: ContratoDAO) = ContratoVO(
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
