package com.pscarpellini.frontend.tailwind

import com.pscarpellini.extensions.formatarNomeTailwind
import com.pscarpellini.frontend.enums.ArredondamentosEnum
import com.pscarpellini.frontend.enums.CoresEnum
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.enums.EnumEntries
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
    extend: ${extendProperties.toJSObject()},
    container: {
      center: true,
    }
  }
};
    """.trimIndent()

        // Salvar no arquivo
        val outputPath = Paths.get(outputFile)
        Files.write(outputPath, tailwindConfig.toByteArray())

        println("Tailwind config file generated: ${outputPath.toAbsolutePath()}")
    }

    private fun Map<String, Map<String, String>>.toJSObject() = Json.encodeToString(this)
}