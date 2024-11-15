package daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.ColaboradoresTable

class ColaboradorDAO(id: EntityID<Int>) :IntEntity(id) {
    companion object : IntEntityClass<ColaboradorDAO>(ColaboradoresTable)

    var idloja by  ColaboradoresTable.idloja
    var nome by ColaboradoresTable.nome
    var endereco by ColaboradoresTable.endereco
    var cpf by ColaboradoresTable.cpf
    var email by ColaboradoresTable.email
    var telefone by ColaboradoresTable.telefone
    var status by ColaboradoresTable.status
}
