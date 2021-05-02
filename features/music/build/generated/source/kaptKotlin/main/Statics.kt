package br.com.gmfonseca.generated

import br.com.gmfonseca.shared.command.Command

object Statics {
    val COMMANDS = initCommands()

    private fun initCommands(): List<Command> = run {
    	val names = listOf(
    		"br.com.gmfonseca.music.application.command.ClearCommand",
    		"br.com.gmfonseca.music.application.command.JumpCommand",
    		"br.com.gmfonseca.music.application.command.LeaveCommand",
    		"br.com.gmfonseca.music.application.command.PauseCommand",
    		"br.com.gmfonseca.music.application.command.PlayCommand",
    		"br.com.gmfonseca.music.application.command.QueueCommand",
    		"br.com.gmfonseca.music.application.command.ResumeCommand",
    		"br.com.gmfonseca.music.application.command.SkipCommand",
    		"br.com.gmfonseca.music.application.command.StopCommand",
    		"br.com.gmfonseca.music.application.command.VolumeCommand",
    		"br.com.gmfonseca.shared.command.UnknownCommand"
    	)
    
    	br.com.gmfonseca.shared.util.ClassMapper.mapClasses(names)
    }
    
}