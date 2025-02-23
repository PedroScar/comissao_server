package com.pscarpellini.tools.image

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.util.*
import javax.imageio.ImageIO

fun saveImageToPublicFolder(inputStream: InputStream, targetSize: Int): String {
    // Ler a imagem original
    val originalImage: BufferedImage = ImageIO.read(inputStream)

    // Determinar o tamanho do quadrado (menor lado da imagem)
    val size = minOf(originalImage.width, originalImage.height)

    // Criar uma nova imagem quadrada
    val squareImage = BufferedImage(size, size, BufferedImage.TYPE_INT_RGB)

    // Cortar a imagem para um quadrado
    val x = (originalImage.width - size) / 2
    val y = (originalImage.height - size) / 2
    squareImage.createGraphics().drawImage(originalImage, 0, 0, size, size, x, y, x + size, y + size, null)

    // Redimensionar a imagem para o tamanho desejado
    val resizedImage = BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_RGB)
    resizedImage.createGraphics().drawImage(squareImage, 0, 0, targetSize, targetSize, null)

    // Gerar um nome único para a imagem
    val uniqueName = "${UUID.randomUUID()}.jpg"

    // Caminho da pasta pública
    val publicFolder = File("src/main/resources/public/images")
    if (!publicFolder.exists()) {
        publicFolder.mkdirs() // Criar a pasta se não existir
    }

    // Salvar a imagem na pasta pública
    val imageFile = File(publicFolder, uniqueName)
    ImageIO.write(resizedImage, "jpg", imageFile)

    // Retornar o caminho relativo da imagem
    return "images/$uniqueName"
}