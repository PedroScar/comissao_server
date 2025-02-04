package com.pscarpellini.database.utils

import com.pscarpellini.database.daos.*
import com.pscarpellini.database.tables.*
import com.pscarpellini.models.vos.*
import org.jetbrains.exposed.sql.Alias
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.alias

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

fun saldoRowToModel(dao: SaldoDAO) = SaldoVO(
    conta = contaDaoToModel(dao.contaId),
    saldo = dao.saldo,
)

fun saldoRowToModel(row: ResultRow) = SaldoVO(
    conta = ContaVO(
        cliente = clienteRowToModel(row, ClientesTable.alias("cliente")),
        tipoConta = row[ContasTable.tipoConta],
        nome = row[ContasTable.nome],
        cpf = row[ContasTable.cpf],
        endereco = row[ContasTable.endereco],
        email = row[ContasTable.email],
        telefone = row[ContasTable.telefone],
        status = row[ContasTable.status],
        usuario = row[ContasTable.usuario],
    ),
    saldo = row[SaldosTable.saldo],
)

fun extratoDaoToModel(row: ResultRow): ExtratoVO {
    return ExtratoVO(
        contaResponsavel = contaRowToModel(row, ContasTable.alias("responsavel")),
        contaSaldo = contaRowToModel(row, ContasTable.alias("saldo")),
        promocao = promocaoRowToModel(row, PromocoesTable.alias("promocao")),
        dataCriacao = row[ExtratosTable.dataCriacao],
        valor = row[ExtratosTable.valor],
        isCredito = row[ExtratosTable.isCredito],
    )
}

fun contaRowToModel(row: ResultRow, alias: Alias<Table>): ContaVO {
    return ContaVO(
        cliente = null,
        nome = row[alias[ContasTable.nome]],
        foto = "",
        endereco = row[alias[ContasTable.endereco]],
        cpf = row[alias[ContasTable.cpf]],
        email = row[alias[ContasTable.email]],
        telefone = row[alias[ContasTable.telefone]],
        usuario = row[alias[ContasTable.usuario]],
        status = row[alias[ContasTable.status]],
        tipoConta = row[alias[ContasTable.tipoConta]],
    )
}

fun clienteRowToModel(row: ResultRow, alias: Alias<Table>): ClienteVO {
    return ClienteVO(
        id = row[alias[ClientesTable.id]].value,
        nome = row[alias[ClientesTable.nome]],
        endereco = row[alias[ClientesTable.endereco]],
        cnpj = row[alias[ClientesTable.cnpj]],
        email = row[alias[ClientesTable.email]],
        telefone = row[alias[ClientesTable.telefone]],
        status = row[alias[ClientesTable.status]],
    )
}

fun promocaoRowToModel(row: ResultRow, alias: Alias<Table>): PromocaoVO? {
    if(row[alias[PromocoesTable.id]] == null) return null

    return PromocaoVO(
        clientId = row[alias[PromocoesTable.id]].value,
        titulo = row[alias[PromocoesTable.titulo]],
        subtitulo = row[alias[PromocoesTable.subtitulo]],
        conteudo = row[alias[PromocoesTable.conteudo]],
        imagem = row[alias[PromocoesTable.imagem]],
        dataValidade = row[alias[PromocoesTable.dataValidade]],
        dataCriacao = row[alias[PromocoesTable.dataCriacao]],
        dataDisponivel = row[alias[PromocoesTable.dataDisponivel]],
        duracaoIndeterminada = row[alias[PromocoesTable.duracaoIndeterminada]],
        exibirPreco = row[alias[PromocoesTable.exibirPreco]],
        valorAnterior = row[alias[PromocoesTable.valorAnterior]],
        valorAtual = row[alias[PromocoesTable.valorAtual]],
    )
}