package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object VideosTable  : IntIdTable("videos") {
    val clienteId = reference("cliente_id", ClientesTable)
    val titulo = text("titulo")
    val video_id = varchar("video_id", 255)
    val habilitado = bool("habilitado")
    val thumb = text("thumb")
}