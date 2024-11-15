package daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.LojasTable

class LojaDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LojaDAO>(LojasTable)

    val nome by LojasTable.nome
    val endereco by LojasTable.endereco
    val cnpj by LojasTable.cnpj
    val email by LojasTable.email
    val telefone by LojasTable.telefone
    val status by LojasTable.status
}
