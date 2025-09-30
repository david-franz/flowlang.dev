plugins {
    java
    antlr
    application
    idea
}

repositories {
    mavenCentral()
}

dependencies {
    // ANTLR tool for codegen + runtime for parser usage
    antlr("org.antlr:antlr4:4.13.1")
    implementation("org.antlr:antlr4-runtime:4.13.1")

    // ASM for later backend work
    implementation("org.ow2.asm:asm:9.7.1")
    implementation("org.ow2.asm:asm-commons:9.7.1")

    // (Optional) CLI lib if you want args parsing later
    // implementation("info.picocli:picocli:4.7.6")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<JavaCompile> {
    options.release.set(21)
}

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
}

application {
    // simple driver to parse a file / stdin
    mainClass.set("com.dfpp.Main")
}

tasks.generateGrammarSource {
    // Put generated sources in a stable package
    arguments = arguments + listOf("-visitor", "-listener", "-package", "com.dfpp.antlr")
    outputDirectory = file("$buildDir/generated-src/antlr/main/com/dfpp/antlr")
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generated-src/antlr/main")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

idea {
    module {
        // mark generated ANTLR sources as such in IntelliJ
        generatedSourceDirs.add(file("$buildDir/generated-src/antlr/main"))
    }
}