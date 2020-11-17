package br.com.gmfonseca.shared.util

import br.com.gmfonseca.DiscordApp
import br.com.gmfonseca.shared.util.ext.createInstance
import br.com.gmfonseca.shared.util.ext.equalsIgnoreCase
import br.com.gmfonseca.shared.util.ext.mapFileToClassPath
import br.com.gmfonseca.shared.util.ext.substringBetween
import java.io.File
import java.io.FileInputStream
import java.util.jar.JarInputStream

/**
 * Created by Gabriel Fonseca on 14/11/2020.
 */
object ClassMapper {

    inline fun <reified T> mapClasses(classesRootPath: String, suffix: String): List<T> {
        val resUrl = DiscordApp::class.java.getResource("")

        return if (resUrl.protocol equalsIgnoreCase "jar") {
            mapJar(resUrl.file, classesRootPath, suffix)
        } else {
            mapProject(resUrl.file, classesRootPath, suffix)
        }
    }

    inline fun <reified T> mapJar(res: String, classesRootPackage: String, suffix: String): List<T> {
        val classes = mutableListOf<T>()
        val classesRootPath = classesRootPackage.replace(".", "/")
        val jarInputStream = JarInputStream(
            FileInputStream(
                res.substringBetween("file:", "!").substring(1)
            )
        )

        var jarEntry = jarInputStream.nextJarEntry
        while (jarEntry != null) {
            val fileName = jarEntry.name
            if (fileName.startsWith(classesRootPath) && fileName.endsWith("$suffix.class")) {
                val className = fileName.substringBefore(".class").replace("/", ".")

                Class.forName(className).createInstance()?.let {
                    if (it is T) {
                        classes.add(it)
                    }
                }
            }

            jarEntry = jarInputStream.nextJarEntry
        }

        return classes
    }

    inline fun <reified T> mapProject(res: String, classesRootPath: String, suffix: String): List<T> {
        val classes = mutableListOf<T>()

        File(res).walk().forEach { file ->
            val name = file.nameWithoutExtension
            if (name.endsWith(suffix) && name != suffix) {
                Class.forName(classesRootPath.mapFileToClassPath(file)).createInstance()?.let {
                    if (it is T) {
                        classes.add(it)
                    }
                }
            }
        }

        return classes
    }

}