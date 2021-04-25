package br.com.gmfonseca.testing

import com.squareup.javapoet.*
import java.io.File
import javax.lang.model.element.Modifier

fun main() {
    generated()
}

fun generated() {

    val hoverboard = ClassName.get("br.com.gmfonseca.shared.command", "Command")
    val list = ClassName.get("java.util", "List")
    val listOfHoverboards: TypeName = ParameterizedTypeName.get(list, hoverboard)

    MethodSpec.methodBuilder("values")
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .returns(listOfHoverboards)
        .addStatement("\$T.out.println(\$S)", System::class.java, "Hello, JavaPoet!")
        .build()

    val commands = TypeSpec.classBuilder("Commands")
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addField(listOfHoverboards, "COMMANDS", Modifier.PUBLIC, Modifier.STATIC)
        .build()
    val javaFile = JavaFile.builder("br.com.gmfonseca.generated.commands", commands)
        .build()

    javaFile.writeTo(File("D:\\Projects\\gamma\\gamma-bot\\src\\main\\generatedJava"))
}