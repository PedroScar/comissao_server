package com.pscarpellini.enums

enum class PerfisDeAcessoEnum(
    val nome: String,
    val descricao: String,
    val papeis: ArrayList<PapeisDeAcessoEnum> = arrayListOf()
) {
    FUNCIONARIO(nome = "Funcionário", descricao = "Funcionário: gerencia promoções e saldos dos promotores com acesso limitado a relatórios"),
    PROMOTOR(nome = "Promotor", descricao = "Promotor: usa o app para visualizar saldo, extrato e promoções disponíveis para clientes"),
    ADMINISTRADOR(nome = "Administrador", descricao = "Administrador: acesso total ao sistema, gerenciamento de promoções, usuários e relatórios"),
    PERSONALIZADO(nome = "Personalizado", descricao = "Personalizado"),
}