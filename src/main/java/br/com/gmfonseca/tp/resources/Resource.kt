package br.com.gmfonseca.tp.resources

import br.com.gmfonseca.tp.process.Process
import br.com.gmfonseca.tp.util.RandomNode
import java.util.concurrent.ConcurrentLinkedDeque
import kotlin.math.ceil
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Created by Gabriel Fonseca on 18/11/2020.
 */
abstract class Resource(
    val name: String,
    private val serviceTimeInterval: IntRange,
    processes: List<Process> = emptyList()
) {

    private val processes = ConcurrentLinkedDeque(processes)

    var serviceTime: Long = 0; protected set
    var visits: Int = 0; protected set
    var use: Int = 0; protected set
    private var processesCount = 1.0

    fun doWork(): Process? {
        // Do work only if there is any pending process
        return processes.poll()?.also {
            val sleepTime = Random.nextInt(serviceTimeInterval)
            serviceTime += sleepTime
            Thread.sleep(sleepTime.toLong())
            visits++
        }
    }

    fun addProcess(process: Process, processesCount: Int? = null) {
        processesCount?.let {
            this.processesCount = it.toDouble()
        }
        processes.add(process)
    }

    fun remove(process: Process) {
        processes.remove(process)
    }

    fun reset() {
        serviceTime = 0
        visits = 0
        use = 0
    }

    override fun toString(): String {
        return """${name}:
    serviceTime: ${serviceTime / 3.0 / processesCount}
    visits: ${ceil((ceil(visits / 3.0) / processesCount)).toInt()}
    use: ${ceil(ceil(use / 3.0) / processesCount).toInt()}
    """
    }

    data class Node(val resource: Resource, val randomResource: RandomNode<Node>) {
        val next: Node; get() = randomResource.take()
    }

}