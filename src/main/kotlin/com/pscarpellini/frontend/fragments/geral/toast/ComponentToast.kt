package com.pscarpellini.frontend.fragments.geral.toast

import com.pscarpellini.frontend.enums.TiposToastEnum
import kotlinx.html.*

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

    div(classes = "fixed bottom-4 left-1/2 transform -translate-x-1/2 bg-${tipo.corFundo.cssProprio} text-${tipo.corTexto.cssProprio} px-4 py-2 rounded-pill shadow-md opacity-100 transition-all duration-300 ease-out animate-slide-in") {
        attributes["id"] = id
        +texto

        script {
            unsafe {
                +"""
                    (() => {
                        const mensagem = document.getElementById('$id');
                        if (!mensagem) return;

                        let timeoutId;
                        const removerMensagem = () => {
                            mensagem.classList.add('hidden');
                            setTimeout(() => mensagem.remove(), 300); // Aguarda transição para remover
                        };

                        const iniciarTemporizador = () => {
                            timeoutId = setTimeout(removerMensagem, ${duracaoFinal * 1000});
                        };

                        const pausarTemporizador = () => {
                            clearTimeout(timeoutId);
                        };

                        mensagem.addEventListener('mouseenter', pausarTemporizador);
                        mensagem.addEventListener('mouseleave', iniciarTemporizador);

                        // Inicia temporizador ao adicionar à tela
                        iniciarTemporizador();
                    })();
                """.trimIndent()
            }
        }
    }
}