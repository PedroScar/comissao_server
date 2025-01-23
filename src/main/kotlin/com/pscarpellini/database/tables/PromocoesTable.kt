package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object PromocoesTable : IntIdTable("promocoes") {
    val clienteId = reference("cliente_id", ClientesTable)
    val titulo = varchar("titulo", 255)
    val subtitulo = varchar("subtitulo", 255)
    val conteudo = text("conteudo")
    val imagem = text("imagem")
    val dataValidade = datetime("data_validade").clientDefault { LocalDateTime.now() }
    val dataCriacao = datetime("data_criacao").clientDefault { LocalDateTime.now() }
    val dataDisponivel = datetime("data_disponivel").clientDefault { LocalDateTime.now() }
    val duracaoIndeterminada = bool("duracao_indeterminada")
    val exibirPreco = bool("exibir_preco")
    val valorAnterior = double("valor_anterior").nullable()
    val valorAtual = double("valor_atual").nullable()
}