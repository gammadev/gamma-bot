tasks.register("codeCoverageReport", JacocoReport::class) {
    subprojects subproject@{
        plugins.withType(JacocoPlugin::class).configureEach {
            tasks.matching { it.extensions.findByType<JacocoTaskExtension>() != null }.configureEach task@{
                sourceSets(this@subproject.the<SourceSetContainer>()["main"])
                executionData(this@task)
            }

            tasks.matching { it.extensions.findByType<JacocoTaskExtension>() != null }.forEach {
                rootProject.tasks.getByName("codeCoverageReport").dependsOn(it)
            }
        }
    }

    reports {
        xml.isEnabled = true
//        html.isEnabled = true
    }
}
