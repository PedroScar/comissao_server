package daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.FuncionariosTable

class FuncionarioDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FuncionarioDAO>(FuncionariosTable)

    var idloja by FuncionariosTable.idloja
    var nome by FuncionariosTable.nome
    var endereco by FuncionariosTable.endereco
    var cpf by FuncionariosTable.cpf
    var email by FuncionariosTable.email
    var telefone by FuncionariosTable.telefone
    var status by FuncionariosTable.status
}