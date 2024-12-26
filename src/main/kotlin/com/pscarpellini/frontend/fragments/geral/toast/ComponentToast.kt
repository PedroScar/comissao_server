package com.pscarpellini.frontend.fragments.geral.toast

import com.pscarpellini.frontend.enums.TiposToastEnum
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.script
import kotlinx.html.unsafe

/**
 * Exiba uma mensagem toast na página.
 *
 * Utilize duração em segundos
 */
fun FlowContent.toast(
    texto: String,
    tipo: TiposToastEnum = TiposToastEnum.DEFAULT,
    duracao: Int? = null,
    id: String = "mensagem-${System.currentTimeMillis()}"
) {
    val duracaoFinal = (duracao ?: tipo.duracao) * 1000

    div(classes = "bg-${tipo.corFundo.cssProprio} text-${tipo.corTexto.cssProprio} px-4 py-2 rounded-lg shadow-md opacity-100 transition-all ease-out animate-slide-in") {
        attributes["id"] = id
        +texto

        script {
            unsafe {
                +"""
                    (() => {
                        const toast = document.getElementById('$id');
                        if (!toast) return;

                        let timeoutId;
                        const removerMensagem = () => {
                            toast.classList.remove('animate-slide-in');
                            toast.classList.add('animate-slide-out');
                            setTimeout(() => toast.remove(), 300);
                        };

                        const iniciarTemporizador = () => {
                            timeoutId = setTimeout(removerMensagem, ${duracaoFinal});
                        };

                        const pausarTemporizador = () => {
                            clearTimeout(timeoutId);
                        };

                        toast.addEventListener('mouseenter', pausarTemporizador);
                        toast.addEventListener('mouseleave', iniciarTemporizador);

                        iniciarTemporizador();
                    })();
                """.trimIndent()
            }
        }
    }
}