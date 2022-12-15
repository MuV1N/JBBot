plugins {
    id("java")
}

group = "de.muv1n"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.1")
    implementation("org.mongodb:mongodb-driver-reactivestreams:4.7.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    implementation("org.slf4j:slf4j-simple:2.0.5")
    implementation("com.google.code.gson:gson:2.10")
    implementation("org.projectlombok:lombok:1.18.24")
    implementation("org.projectlombok:lombok:1.18.24")
    implementation("io.github.cdimascio:dotenv-java:2.3.1")
    implementation("com.github.twitch4j:twitch4j:1.12.0")

    implementation(group = "ch.qos.logback", name = "logback-classic", version = "1.2.10")

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<Jar> {
    val classpath = configurations.runtimeClasspath

    inputs.files(classpath).withNormalizer(ClasspathNormalizer::class.java)

    manifest {
        attributes["Main-Class"] = "de.muv1n.jbbot.Main"

        attributes(
            "Class-Path" to classpath.map { cp -> cp.joinToString(" ") { "./lib/" + it.name } }
        )
    }
}


