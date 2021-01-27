package br.com.gmfonseca.shared.annotations

/**
 * Created by Gabriel Fonseca on 30/10/2020.
 */

/**
 * This annotation describes the name of the command and its alias, if should have any
 *
 * @property name the main command name
 * @property aliases the list of aliases for a command
 */
@Target(AnnotationTarget.CLASS)
annotation class CommandHandler(val name: String, val aliases: Array<String> = [], val enabled: Boolean = true)