package br.com.gmfonseca.bot.commandmanager

/**
 * Created by Gabriel Fonseca on 14/11/2020.
 */
object ClassMapper {

    inline fun <reified T> mapClasses(classesName: List<String>): List<T> {
        return classesName.mapNotNull { Class.forName(it).createInstance() as? T }
    }

    fun <T : Any> Class<T>.createInstance(): T? {
        return try {
            getDeclaredConstructor().newInstance()
        } catch (e: IllegalAccessException) {
            null
        }
    }

}