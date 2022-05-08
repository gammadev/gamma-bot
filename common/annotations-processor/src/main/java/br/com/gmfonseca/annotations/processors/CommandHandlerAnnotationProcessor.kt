package br.com.gmfonseca.annotations.processors

import br.com.gmfonseca.annotations.CommandHandler
import org.yanex.takenoko.INTERNAL
import org.yanex.takenoko.KoType
import org.yanex.takenoko.PRIVATE
import org.yanex.takenoko.PrettyPrinter
import org.yanex.takenoko.PrettyPrinterConfiguration
import org.yanex.takenoko.kotlinFile
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedOptions
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("br.com.gmfonseca.annotations.CommandHandler")
@SupportedOptions(CommandHandlerAnnotationProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class CommandHandlerAnnotationProcessor : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        val annotatedElements = roundEnv.getElementsAnnotatedWith(CommandHandler::class.java)

        if (annotatedElements.isEmpty()) return false

        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
            processingEnv.messager.printMessage(
                Diagnostic.Kind.ERROR,
                "Can't find the target directory for generated Kotlin files."
            )
            return false
        }

        val packageName = annotatedElements.firstOrNull()?.let {
            if (it is TypeElement) {
                val name = it.qualifiedName.toString()
                val packageName = name.substringAfter("$BASE_PACKAGE.").substringBefore(".")

                "$BASE_PACKAGE.$packageName.generated"
            } else {
                null
            }
        }

        val generatedKtFile = kotlinFile(packageName = packageName ?: "br.com.gmfonseca.generated") {
            objectDeclaration(name = "Statics", modifiers = INTERNAL) {
                val initCommands = "initCommands"
                val commandListType = KoType.Companion.parseType("List<$COMMAND_TYPE_NAME>")

                property(name = "COMMANDS", type = commandListType) {
                    initializer("$initCommands()")
                }

                function(name = initCommands, modifiers = PRIVATE) {
                    returnType(commandListType)

                    val names = annotatedElements.mapNotNull {
                        if (it is TypeElement) {
                            "\t\t\"${it.qualifiedName}\""
                        } else {
                            null
                        }
                    }.joinToString(separator = ",\n")

                    body(expressionBody = true) {
                        appendln("run {")
                        appendln("\tval names = listOf(")
                        append(names)
                        appendln("\n\t)")
                        appendln("\n\t$CLASS_MAPPER_MAPPING_METHOD_NAME(names)")
                        appendln("}")
                    }
                }
            }
        }

        File(kaptKotlinGeneratedDir, "Statics.kt").apply {
            parentFile.mkdirs()
            writeText(generatedKtFile.accept(PrettyPrinter(PrettyPrinterConfiguration())))
        }

        return true
    }

    internal companion object {
        const val BASE_PACKAGE = "br.com.gmfonseca.bot"
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        const val COMMAND_TYPE_NAME = "${BASE_PACKAGE}.commandmanager.commands.Command"
        const val CLASS_MAPPER_MAPPING_METHOD_NAME = "${BASE_PACKAGE}.commandmanager.ClassMapper.mapClasses"
    }
}
