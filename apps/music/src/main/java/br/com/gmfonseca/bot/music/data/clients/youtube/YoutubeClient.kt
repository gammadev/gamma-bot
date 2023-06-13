package br.com.gmfonseca.bot.music.data.clients.youtube

/**
 * Created by Gabriel Fonseca on 19/09/2020.
 */
interface YoutubeClient {

    fun search(identifier: String)

    fun download(identifier: String)
}
