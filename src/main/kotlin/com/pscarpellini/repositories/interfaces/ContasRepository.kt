package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaESaldoVO
import com.pscarpellini.models.vos.ContaVO

interface ContasRepository {
    suspend fun validarLogin(usuario: String, senha: String): DbResponse<ContaVO>
    suspend fun carregarUsuarios(nome: String = "", clienteId: Int): DbResponse<List<ContaVO>>
    suspend fun carregarPromotor(promotorId: Int, clienteId: Int): DbResponse<ContaESaldoVO>
    suspend fun listarPromotores(clienteId: Int): DbResponse<List<ContaVO>>
    suspend fun criarUsuario(conta: ContaVO): DbResponse<ContaVO>
    suspend fun editarUsuario(conta: ContaVO): DbResponse<ContaVO>
    suspend fun validarEmailEsqueciMinhaSenha(emailOuUsuario: String): Pair<String, Boolean>
    suspend fun definirSenhaProvisoria(email: String, novaSenha: String): Boolean
    suspend fun definirSenha(usuarioId: Int, novaSenha: String): Boolean
    suspend fun atualizarMeuPerfil(usuarioId: Int, telefone: String, imagemDePerfil: String): Boolean
}