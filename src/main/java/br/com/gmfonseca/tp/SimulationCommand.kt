package br.com.gmfonseca.tp

import br.com.gmfonseca.shared.command.Command
import br.com.gmfonseca.shared.command.CommandHandler
import br.com.gmfonseca.shared.util.EmbedMessage
import br.com.gmfonseca.tp.process.Process
import br.com.gmfonseca.tp.resources.Cpu
import br.com.gmfonseca.tp.resources.Disk
import br.com.gmfonseca.tp.resources.Pool
import br.com.gmfonseca.tp.resources.Resource
import br.com.gmfonseca.tp.util.RandomNode
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User
import kotlin.concurrent.thread

/**
 * Created by Gabriel Fonseca on 18/11/2020.
 */
@CommandHandler("simulate")
class SimulationCommand : Command() {
    override fun onCommand(author: User, channel: TextChannel, args: List<String>): Boolean {
        val cpu = Cpu("Cpu#1", 5..25)
        val disk1 = Disk("Disk#1", 0..30)
        val disk2 = Disk("Disk#2", 0..30)
        val disk3 = Disk("Disk#3", 0..30)
        val pool = Pool("Awaiting#1", 500..500)

        val cpuNode = Resource.Node(cpu, RandomNode())

        val randomNodeToCpu = RandomNode.Builder<Resource.Node>().appendResource(cpuNode, 1).build()

        val poolNode = Resource.Node(pool, randomNodeToCpu.copy())
        val diskOneNode = Resource.Node(disk1, randomNodeToCpu.copy())
        val diskTwoNode = Resource.Node(disk2, randomNodeToCpu.copy())
        val diskThreeNode = Resource.Node(disk3, randomNodeToCpu.copy())

        cpuNode.randomResource.appendResource(diskOneNode, 45)
            .appendResource(diskTwoNode, 15)
            .appendResource(diskThreeNode, 30)
            .appendResource(poolNode, 10)

        thread {
            execute(channel = channel, pool = pool, poolNode, cpuNode, diskOneNode, diskTwoNode, diskThreeNode)
        }

        return true
    }

    private fun execute(channel: TextChannel, pool: Pool, vararg nodes: Resource.Node) {
        print("Running ...\n")
        EmbedMessage.success(channel, description = "Iniciando simulação...")
        repeat(50) { i ->
            val sb = StringBuilder("```yml").appendLine()
            repeat(3) { _ ->
                val processes = MutableList(i + 1) { Process("Process#${it + 1}") }

                processes.forEach { process ->
                    pool.addProcess(process, i + 1)

                    while (!process.finished) {
                        nodes.forEach { node ->
                            node.resource.doWork()?.run {
                                if (!joinResourceQueue(node.next.resource)) {
                                    pool.remove(this@run)
                                }
                            }
                        }
                    }
                }
            }

            sb.append("Results [${i + 1} requests]:").appendLine()

            nodes.forEach {
                sb.append("- ${it.resource}").appendLine()
                it.resource.reset()
            }

            sb.append("```").appendLine()
            channel.sendMessage(sb.toString()).queue()
        }
    }

}