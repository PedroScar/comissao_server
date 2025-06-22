package com.pscarpellini.frontend.fragments.geral.inputs

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import kotlinx.html.*

fun FlowContent.inputImage(
    imagem64: String = "",
    nomeDoCampo: String,
    textoBotao: String = "Enviar imagem"
) {
    val previewId = "imagem-preview-$nomeDoCampo"
    val frameId = "imagem-frame-$nomeDoCampo"

    div(classes = "flex flex-col w-full col-span-2") {
        label(classes = "${CoresEnum.LOW_PURE.text} block text-base font-semibold") { +"Imagem de exibição" }
        span(classes = CoresEnum.LOW_LIGHT.text) {
            +"Tamanho máximo do arquivo é de 500kb. Os tipos suportados são .jpg e .png"
        }

        div(classes = "self-start mt-4 w-60 h-60 border-2 border-dashed flex items-center justify-center relative overflow-hidden") {
            attributes["id"] = frameId

            img(
                classes = "absolute inset-0 w-full h-full object-cover ${if (imagem64.isNotBlank()) "opacity-100" else "opacity-0"} transition-opacity duration-500",
                src = if (imagem64.isNotBlank()) "data:image/png;base64, $imagem64" else ""
            ) {
                attributes["id"] = previewId
            }

            if (imagem64.isBlank()) {
                span(classes = "text-low-light text-sm text-center px-2") { +"Nenhuma imagem selecionada" }
            }
        }

        inputFileUpload(
            classes = "self-start mt-4",
            nomeDoCampo = nomeDoCampo
        ) {
            +textoBotao
        }
    }

    script {
        unsafe {
            +"""
                document.getElementById('$nomeDoCampo').addEventListener('change', function(event) {
                    const input = event.target;
                    const file = input.files[0];
                    const maxSize = 500 * 1024; // 500KB
                    const validTypes = ['image/jpeg', 'image/png'];

                    if (file.size > maxSize) {
                        exibirToast('O arquivo não pode ser maior que 500KB.', 'error');
                        input.value = '';
                        return;
                    }

                    if (!validTypes.includes(file.type)) {
                        exibirToast('Apenas arquivos JPG e PNG são permitidos.', 'error');
                        input.value = '';
                        return;
                    }

                    if (file) {
                        const reader = new FileReader();
                        reader.onload = function(e) {
                            const img = document.getElementById('$previewId');
                            const frame = document.getElementById('$frameId');

                            // Troca a imagem e aplica fade-in
                            img.src = e.target.result;
                            img.style.opacity = '0';
                            setTimeout(() => { img.style.opacity = '1'; }, 50);

                            // Remove texto do frame (caso exista)
                            frame.querySelectorAll('span').forEach(span => span.remove());
                        }
                        reader.readAsDataURL(file);
                    }
                });

                function exibirToast(mensagem, tipo = 'error') {
                    const notyf = NotyfManager.getInstance();
                    notyf.open({ type: tipo, message: mensagem });
                }
            """.trimIndent()
        }
    }
}
