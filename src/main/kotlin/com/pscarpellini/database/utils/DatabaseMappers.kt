package com.pscarpellini.database.utils

import com.pscarpellini.database.daos.*
import com.pscarpellini.enums.comissao.StatusPromocoesEnum
import com.pscarpellini.models.vos.*
import java.time.LocalDate
import java.time.LocalDateTime

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

fun contaDaoToModel(dao: ContaDAO) = ContaVO(
    cliente = clienteDaoToModel(dao.clienteId),
    tipoConta = dao.tipoConta,
    nome = dao.nome,
    cpf = dao.cpf,
    endereco = dao.endereco,
    email = dao.email,
    telefone = dao.telefone,
    saldo = dao.saldo,
    status = dao.status,
    usuario = dao.usuario
)

fun promocaoDaoToModel(dao: PromocaoDAO) = PromocaoVO(
    clientId = dao.id.value,
    titulo = dao.titulo,
    subtitulo = dao.subtitulo,
    conteudo = dao.conteudo,
    imagem = dao.imagem,
    dataValidade = dao.dataValidade,
    dataCriacao = dao.dataCriacao,
    dataDisponivel = dao.dataDisponivel,
    duracaoIndeterminada = dao.duracaoIndeterminada,
    exibirPreco = dao.exibirPreco,
    valorAnterior = dao.valorAnterior,
    valorAtual = dao.valorAtual,
)