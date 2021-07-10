object Dependencies {
    const val JDA = "net.dv8tion:JDA:${DepsVersions.JDA}"
    const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN_VERSION}"
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DepsVersions.COROUTINES_CORE}"
    const val LAVA_PLAYER = "com.sedmelluq:lavaplayer:${DepsVersions.LAVA_PLAYER}"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${DepsVersions.KOTLIN_REFLECT}"
    const val TAKENOKO = "com.github.yanex:takenoko:${DepsVersions.TAKENOKO}"

    private object DepsVersions {
        const val JDA = "4.2.0_204"
        const val COROUTINES_CORE = "1.3.9"
        const val LAVA_PLAYER = "1.3.53"
        const val KOTLIN_REFLECT = "1.4.10"
        const val TAKENOKO = "0.1"
    }
}
