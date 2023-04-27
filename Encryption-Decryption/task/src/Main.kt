package encryptdecrypt

import java.io.File

fun encryptUnicode(message: String, key: Int): String {
    val messageEncrypted = mutableListOf<Char>()
    var char: Char
    var encryptedMessage: igiString = ""
    for (i in message.indices) {
        char = message[i] + key
        messageEncrypted.add(char)
        encryptedMessage += messageEncrypted[i]
    }
    println(encryptedMessage)
    return encryptedMessage
}

fun encryptShift(message: String, key: Int): String {
    val messageEncrypted = mutableListOf<Char>()
    var char: Char
    var encryptedMessage: String = ""
    for (i in message.indices) {
        if (message[i] !in 97.toChar()..122.toChar() && message[i] !in 65.toChar()..90.toChar()) {
            messageEncrypted.add(message[i])
        } else if (message[i] + key > 122.toChar()) {
            char = message[i] + key - 26
            messageEncrypted.add(char)
        } else if (message[i] + key > 90.toChar() && message[i] + key < 97.toChar()) {
            char = message[i] + key - 26
            messageEncrypted.add(char)
        } else {
            char = message[i] + key
            messageEncrypted.add(char)
        }
        encryptedMessage += messageEncrypted[i]
    }
    println(encryptedMessage)
    return encryptedMessage
}

fun decryptUnicode(message: String, key: Int): String {
    val messageDecrypted = mutableListOf<Char>()
    var char: Char
    var decryptedMessage: String = ""
    for (i in message.indices) {
        char = message[i] - key
        messageDecrypted.add(char)
        decryptedMessage += messageDecrypted[i]
    }
    println(decryptedMessage)
    return decryptedMessage
}

fun decryptShift(message: String, key: Int): String {
    val messageDecrypted = mutableListOf<Char>()
    var char: Char
    var decryptedMessage: String = ""
    for (i in message.indices) {
        if (message[i] !in 97.toChar()..122.toChar() && message[i] !in 65.toChar()..90.toChar()) {
            messageDecrypted.add(message[i])
        } else if (message[i] - key < 65.toChar()) {
            char = message[i] - key + 26
            messageDecrypted.add(char)
        } else if (message[i] - key > 90.toChar() && message[i] - key < 97.toChar()) {
            char = message[i] - key + 26
            messageDecrypted.add(char)
        } else if (message[i] - key <= 90.toChar() && message[i] - key > 64.toChar()) {
                char = message[i] - key + 26
                messageDecrypted.add(char)
        } else {
            char = message[i] - key
            messageDecrypted.add(char)
        }
        decryptedMessage += messageDecrypted[i]
    }
    println(decryptedMessage)
    return decryptedMessage
}

fun options(args: Array<String>): MutableMap<String, String> {
    val fs: MutableMap<String, String> = mutableMapOf()

    val iterator = args.iterator()

    while (iterator.hasNext()) {
        val name = iterator.next()
        if (name.startsWith("-")) {
            val value = iterator.next()
            if (value.startsWith("-")) {
                throw Exception("expected a value but got another option")
            }
            fs.put(name, value)
        } else {
            fs.put(name, "")
        }
    }

    return fs
}

fun input(fileName: String): String {
    return File(fileName).readText()
}

fun output(fileName: String, value: String) {
    File(fileName).writeText(value)
}

fun main(args: Array<String>) {
    val option = options(args)

    val mode = option.get("-mode")
    val key = option.get("-key")!!.toInt()
    val input = option.get("-in")
    val message = input(input!!)
    val outputFileName = option.get("-out")!!
    val algorithm = option.get("-alg")

    output("outputFileName.txt", message)

    if (algorithm == "unicode") {
        if (mode == "enc") {
            output(outputFileName, encryptUnicode(message, key))
        } else {
            output(outputFileName, decryptUnicode(message, key))
        }
    } else {
        if (mode == "enc") {
            output(outputFileName, encryptShift(message, key))
        } else {
            output(outputFileName, decryptShift(message, key))
        }
    }

}
