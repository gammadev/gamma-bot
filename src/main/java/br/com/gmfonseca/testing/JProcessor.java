package br.com.gmfonseca.testing;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("br.com.gmfonseca.shared.command.CommandHandler")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
class JProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> a = new HashSet<String>();
        a.add("br.com.gmfonseca.shared.command.CommandHandler");
        return a;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        System.out.println("[CommandProcessor] starting init...");
        super.init(processingEnv);
        System.out.println("[CommandProcessor] finished init!");
    }

    @Override
    public boolean process(Set<? extends javax.lang.model.element.TypeElement> annotations, RoundEnvironment roundEnv) {

        System.out.println("[CommandProcessor] starting process...");
        if (annotations.isEmpty() || roundEnv.errorRaised()) return false;
        Set<? extends Element> annotatedElements = new HashSet<>();

        List<TypeElement> b = new ArrayList<>();
        System.out.println("[CommandProcessor] started mapping");
        annotatedElements.forEach(it -> {
            TypeElement element = ((TypeElement) it);
            boolean assignable = processingEnv.getTypeUtils().isAssignable(it.asType(), processingEnv.getElementUtils().getTypeElement("br.com.gmfonseca.shared.command.Command").asType());
            if (assignable) {
                b.add(element);
            } else {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.MANDATORY_WARNING,
                        "Did you forgot to extends Command() in your class ${it.qualifiedName}? Annotated classes with @CommandHandler should extends from it."
                );
            }
        });

        System.out.println("[CommandProcessor] finished mapping");

//        val basePath = DiscordApp::class.java.canonicalName.substringBeforeLast(".").replace(".", File.separator)

        write();
        System.out.println("[CommandProcessor] finished process!");

        return true;
    }

    private void write() {
        System.out.println("[CommandProcessor] starting writing...");
        ClassName hoverboard = ClassName.get("br.com.gmfonseca.shared.command", "Command");
        ClassName list = ClassName.get("kotlin.collections", "List");
        TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);

        MethodSpec.methodBuilder("values")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(listOfHoverboards)
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        TypeSpec commandsClass = TypeSpec.classBuilder("Commands")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(listOfHoverboards, "COMMANDS", Modifier.PUBLIC, Modifier.STATIC)
                .build();

        JavaFile javaFile = JavaFile.builder("br.com.gmfonseca.generated.commands", commandsClass)
                .build();

        try {
            javaFile.writeTo(new File("D:\\Projects\\gamma\\gamma-bot\\src\\main\\generatedJava"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[CommandProcessor] finished writing!");
    }

}