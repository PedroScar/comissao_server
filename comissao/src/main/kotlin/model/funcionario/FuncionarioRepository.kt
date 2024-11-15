package model.funcionario

interface FuncionarioRepository {
    suspend fun adicionarUsuario(funcionario: Funcionario): Boolean
}