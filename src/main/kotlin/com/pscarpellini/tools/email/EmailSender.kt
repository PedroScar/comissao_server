package com.pscarpellini.tools.email

import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailSender(
    private val host: String,
    private val port: Int,
    private val username: String,
    private val password: String,
    private val fromEmail: String,
) {

    fun enviarEmail(destinatario: String, assunto: String, corpo: String, isHtml: Boolean = true) {
        val props = System.getProperties().apply {
            put("mail.smtp.host", host)
            put("mail.smtp.port", port.toString())
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true") // Usar TLS
        }

        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(fromEmail))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario))
                subject = assunto
                if (isHtml) setContent(corpo, "text/html")
                else setText(corpo)
            }

            Transport.send(message)
            println("E-mail enviado com sucesso para $destinatario")
        } catch (e: MessagingException) {
            e.printStackTrace()
            throw RuntimeException("Erro ao enviar e-mail", e)
        }
    }
}