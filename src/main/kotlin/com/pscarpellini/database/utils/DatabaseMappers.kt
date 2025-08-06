package com.pscarpellini.database.utils

import com.pscarpellini.database.daos.*
import com.pscarpellini.database.tables.*
import com.pscarpellini.database.views.ContadoresDashboardView
import com.pscarpellini.models.tableModels.SaldoDB
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
    dao.status,
    dao.logo
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
    id = dao.id.value,
    cliente = clienteDaoToModel(dao.clienteId),
    tipoConta = dao.tipoConta,
    nome = dao.nome,
    cpf = dao.cpf,
    endereco = dao.endereco,
    email = dao.email,
    telefone = dao.telefone,
    status = dao.status,
    usuario = dao.usuario,
    imagemDePerfil = dao.imagem
)

fun promocaoDaoToModel(dao: PromocaoDAO) = PromocaoVO(
    id = dao.id.value,
    clientId = dao.clienteId.id.value,
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
    vendas = dao.vendas,
    compras = dao.compras
)

fun videoDaoToModel(dao: VideoDAO) = VideoVO(
    id = dao.id.value,
    clientId = dao.clienteId.id.value,
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

fun saldoRowToDB(dao: SaldoDAO) = SaldoDB(
    saldo = dao.saldo
)

fun saldoRowToModel(row: ResultRow): SaldoVO {
    val cliente = runCatching {
        val aliasCliente = ClientesTable.alias("cliente")
        if (row[aliasCliente[ClientesTable.id]] == null) null else clienteRowToModel(row, aliasCliente)
    }.getOrNull()

    return SaldoVO(
        conta = ContaVO(
            id = row[ContasTable.id].value,
            cliente = cliente,
            tipoConta = row[ContasTable.tipoConta],
            nome = row[ContasTable.nome],
            cpf = row[ContasTable.cpf],
            endereco = row[ContasTable.endereco],
            email = row[ContasTable.email],
            telefone = row[ContasTable.telefone],
            status = row[ContasTable.status],
            usuario = row[ContasTable.usuario],
            imagemDePerfil = row[ContasTable.imagem]
        ),
        saldo = row[SaldosTable.saldo],
    )
}

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

fun contaESaldoToModel(row: ResultRow, alias: Alias<Table>): ContaESaldoVO {
    return ContaESaldoVO(
        id = row[ContasTable.id].value,
        nome = row[ContasTable.nome],
        foto = "",
        saldo = row[alias[SaldosTable.saldo]],
        cpf = row[ContasTable.cpf],
        email = row[ContasTable.email],
        telefone = row[ContasTable.telefone],
        usuario = row[ContasTable.usuario],
        status = row[ContasTable.status],
        tipoConta = row[ContasTable.tipoConta],
        senha = row[ContasTable.senha]
    )
}

fun contaRowToModel(row: ResultRow, alias: Alias<Table>): ContaVO {
    return ContaVO(
        id = row[alias[ContasTable.id]].value,
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
        imagemDePerfil = row[alias[ContasTable.imagem]]
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
        logo = row[alias[ClientesTable.logo]]
    )
}

fun promocaoRowToModel(row: ResultRow, alias: Alias<Table>): PromocaoVO? {
    if (row[alias[PromocoesTable.id]] == null) return null

    return PromocaoVO(
        id = row[alias[PromocoesTable.id]].value,
        clientId = row[alias[PromocoesTable.clienteId]].value,
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
        vendas = row[alias[PromocoesTable.vendas]],
        compras = row[alias[PromocoesTable.compras]],
    )
}

fun contadoresViewToVisaoGeralVO(row: ResultRow): VisaoGeralVO {
    return VisaoGeralVO(
        quantidadePromocoesAtivas = row[ContadoresDashboardView.quantidadePromocoesAtivas],
        quantidadePromotores = row[ContadoresDashboardView.quantidadePromotores],
        valorComissoesMesAtual = row[ContadoresDashboardView.valorComissoesMesAtual],
    )
}