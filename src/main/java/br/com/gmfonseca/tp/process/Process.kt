package br.com.gmfonseca.tp.process

import br.com.gmfonseca.tp.resources.Pool
import br.com.gmfonseca.tp.resources.Resource

/**
 * Created by Gabriel Fonseca on 20/11/2020.
 */
class Process(val name: String) {

    var finished = false

    fun joinResourceQueue(resource: Resource): Boolean {
        if (finished) return true

        if (resource is Pool) {
            finished = true
        } else {
            resource.addProcess(this)
        }

        return !finished
    }
}