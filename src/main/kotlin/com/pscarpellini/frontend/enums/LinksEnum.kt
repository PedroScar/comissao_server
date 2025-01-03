package com.pscarpellini.frontend.enums

import com.pscarpellini.AmbientController

enum class LinksEnum(
    val link: String
) {
    PAGINA_ANTERIOR(link = "javascript:history.back()"),
    WHATSAPP(link = "https://wa.me/${AmbientController.encaminhamentoWhatsapp}?text=Olá,%20gostaria%20de%20mais%20informações");

    override fun toString() = this.link
}