package br.com.gmfonseca.testing

import br.com.gmfonseca.shared.command.CommandHandler
import com.google.auto.service.AutoService
import com.squareup.javapoet.*
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedAnnotationTypes("br.com.gmfonseca.shared.command.CommandHandler")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
class CommandProcessor : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): Set<String> {
        return setOf(CommandHandler::class.java.canonicalName)
    }

    override fun init(processingEnv: ProcessingEnvironment) {
        println("[CommandProcessor] starting init...")
        super.init(processingEnv)
        println("[CommandProcessor] finished init!")

    }

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        println("[CommandProcessor] starting process...")
        if (annotations.isEmpty() || roundEnv.errorRaised()) return false

        val annotatedElements = roundEnv.getElementsAnnotatedWith(CommandHandler::class.java)


        println("[CommandProcessor] started mapping")
        annotatedElements.map {
            if ((it as TypeElement).implements(processingEnv)) {
                it
            } else {
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.MANDATORY_WARNING,
                    "Did you forgot to extends Command() in your class ${it.qualifiedName}? Annotated classes with @CommandHandler should extends from it."
                )

                return false
            }
        }
        println("[CommandProcessor] finished mapping")

//        val basePath = DiscordApp::class.java.canonicalName.substringBeforeLast(".").replace(".", File.separator)

        write()
        println("[CommandProcessor] finished process!")

        return true
    }

    private fun write() {
        println("[CommandProcessor] starting writing...")
        val hoverboard = ClassName.get("br.com.gmfonseca.shared.command", "Command")
        val list = ClassName.get("kotlin.collections", "List")
        val listOfHoverboards: TypeName = ParameterizedTypeName.get(list, hoverboard)

        MethodSpec.methodBuilder("values")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(listOfHoverboards)
            .addStatement("\$T.out.println(\$S)", System::class.java, "Hello, JavaPoet!")
            .build()

        val commandsClass = TypeSpec.classBuilder("Commands")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addField(listOfHoverboards, "COMMANDS", Modifier.PUBLIC, Modifier.STATIC)
            .build()

        val javaFile = JavaFile.builder("br.com.gmfonseca.generated.commands", commandsClass)
            .build()

        javaFile.writeTo(File("D:\\Projects\\gamma\\gamma-bot\\src\\main\\generatedJava"))
        println("[CommandProcessor] finished writing!")
    }

    private fun TypeElement.implements(processingEnvironment: ProcessingEnvironment): Boolean {
        return processingEnvironment.typeUtils.isAssignable(
            this.asType(),
            processingEnvironment.elementUtils.getTypeElement("br.com.gmfonseca.shared.command.Command").asType()
        )
    }

}