package com.pscarpellini.database.utils

import com.pscarpellini.database.daos.*
import com.pscarpellini.database.tables.ClientesTable
import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.database.tables.SaldosTable
import com.pscarpellini.models.vos.*
import org.jetbrains.exposed.sql.ResultRow

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

fun videoDaoToModel(dao: VideoDAO) = VideoVO(
    clientId = dao.id.value,
    titulo = dao.titulo,
    video_id = dao.video_id,
    habilitado = dao.habilitado,
    destaque = dao.destaque,
    thumb = dao.thumb,
)

fun saldoDaoToModel(dao: SaldoDAO) = SaldoVO(
    conta = contaDaoToModel(dao.contaId),
    saldo = dao.saldo,
)

fun saldoDaoToModel(dao: ResultRow) = SaldoVO(
//    conta = contaDaoToModel(dao.conta),
    conta = ContaVO(
        cliente = ClienteVO(
            id = dao[ClientesTable.id].value,
            nome = dao[ClientesTable.nome],
            endereco = dao[ClientesTable.endereco],
            cnpj = dao[ClientesTable.cnpj],
            email = dao[ClientesTable.email],
            telefone = dao[ClientesTable.telefone],
            status = dao[ClientesTable.status],
        ),
        tipoConta = dao[ContasTable.tipoConta],
        nome = dao[ContasTable.nome],
        cpf = dao[ContasTable.cpf],
        endereco = dao[ContasTable.endereco],
        email = dao[ContasTable.email],
        telefone = dao[ContasTable.telefone],
        status = dao[ContasTable.status],
        usuario = dao[ContasTable.usuario],
    ),
    saldo = dao[SaldosTable.saldo],
)

fun extratoDaoToModel(dao: ExtratoDAO) = ExtratoVO(
    contaCriacaoId = dao.contaCriacaoId.value,
    contaDonoId = dao.contaDonoId.value,
    promocaoId = dao.promocaoId.value,
    dataCriacao = dao.dataCriacao,
    valor = dao.valor,
    isCredito = dao.isCredito,
)