package br.com.gmfonseca.bot.commandmanager

/**
 * Created by Gabriel Fonseca on 14/11/2020.
 */
object ClassMapper {

    inline fun <reified T> mapClasses(classNames: List<String>): List<T> {
        return classNames.mapNotNull { className ->
            Class.forName(className).createInstance()?.let {
                if (it is T) {
                    it
                } else {
                    null
                }
            }
        }
    }

    fun <T : Any> Class<T>.createInstance(): T? {
        return try {
            getDeclaredConstructor().newInstance()
        } catch (e: IllegalAccessException) {
            null
        }
    }

}