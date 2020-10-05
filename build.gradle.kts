buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        Libs.classpathDependencies().forEach { classpath(it) }
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
