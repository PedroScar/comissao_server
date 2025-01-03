package com.pscarpellini.frontend.tailwind

import com.pscarpellini.extensions.formatarNomeTailwind
import com.pscarpellini.frontend.enums.ArredondamentosEnum
import com.pscarpellini.frontend.enums.CoresEnum
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.file.Files
import java.nio.file.Paths

class TailwindConfigsGenerator {
    private val outputFile = "src/main/resources/static/scripts/TailwindScript_tmp.js"

    fun generate() {
        val overrideProperties = mapOf(
            "colors" to CoresEnum.entries.associate { it.cssProprio to it.valor },
            "borderRadius" to ArredondamentosEnum.entries.associate { it.name.formatarNomeTailwind() to it.valor },
        )

        val extendProperties = mapOf<String, Map<String, String>>()

        val overridePropertiesJson = overrideProperties.toJSObject()

        // Configuração do Tailwind
        val tailwindConfig = """
tailwind.config = {
  theme: {
    ${overridePropertiesJson.substring(1, overridePropertiesJson.length - 1)},
    fontFamily: {
      sans: ['Nunito', 'sans-serif'],
    },
    extend: {
      animation: {
        'slide-in': 'slideIn 0.3s ease-out',
        'slide-out': 'slideOut 0.3s ease-in',
        spin: 'spin 2s linear infinite',
      },
      keyframes: {
        slideIn: {
          '0%': { transform: 'translateY(-100%)', opacity: '0' },
          '100%': { transform: 'translateY(0)', opacity: '1' },
        },
        slideOut: {
          '0%': { transform: 'translateY(0)', opacity: '1' },
          '100%': { transform: 'translateY(-100%)', opacity: '0' },
        },
        spin: {
          '0%': { transform: 'rotate(0deg)' },
          '100%': { transform: 'rotate(360deg)' },
        },
      },
    },
    container: {
      center: true,
    }
  },
  plugins: [
    function({ addBase }) {
      addBase({
        'h1': { fontSize: '32px', fontWeight: '600', color: '${CoresEnum.LOW_DARK.valor}' },
        'h2': { fontSize: '26px', fontWeight: '600', color: '${CoresEnum.LOW_DARK.valor}' },
        'h3': { fontSize: '22px', fontWeight: '600', color: '${CoresEnum.LOW_DARK.valor}' },
        'h4': { fontSize: '20px', fontWeight: '600', color: '${CoresEnum.LOW_DARK.valor}' },
        'h5': { fontSize: '18px', fontWeight: '600', color: '${CoresEnum.LOW_DARK.valor}' },
        'h6': { fontSize: '16px', fontWeight: '600', color: '${CoresEnum.LOW_DARK.valor}' },
      });
    }
  ],
};
    """.trimIndent()

        // Salvar no arquivo
//        val outputPath = Paths.get(outputFile)
//        Files.write(outputPath, tailwindConfig.toByteArray())

        val outputPath = Paths.get(outputFile)
        Files.createDirectories(outputPath.parent) // Garante que os diretórios existam
        Files.write(outputPath, tailwindConfig.toByteArray())

        println("Tailwind config file generated: ${outputPath.toAbsolutePath()}")
    }

    private fun Map<String, Map<String, String>>.toJSObject() = Json.encodeToString(this)
}