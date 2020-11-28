package br.com.gmfonseca.tp.resources

import br.com.gmfonseca.tp.process.Process

/**
 * Created by Gabriel Fonseca on 20/11/2020.
 */
class Pool(name: String, serviceTimeInterval: IntRange, processes: List<Process> = emptyList()) : Resource(name, serviceTimeInterval, processes)