package com.pscarpellini.interfaces

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum

interface ISublinksRestritosEnum {
    val icone: IconesEnum
    val nome: String
    val caminho: IPaginaEnum
    val papelNecessario: PapeisDeAcessoEnum?
}