package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.VideosTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class VideoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VideoDAO>(VideosTable)

    var clienteId by ClienteDAO referencedOn VideosTable.clienteId
    var titulo by VideosTable.titulo
    var video_id by VideosTable.video_id
    var habilitado by VideosTable.habilitado
    var destaque by VideosTable.destaque
    var thumb by VideosTable.thumb
}