package br.com.gmfonseca.shared.command

/**
 * This annotation describes a command class, its name and alias (if should have any)
 *
 * @property name the main command name
 * @property aliases the list of aliases for a command
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class CommandHandler(val name: String, val aliases: Array<String> = [])