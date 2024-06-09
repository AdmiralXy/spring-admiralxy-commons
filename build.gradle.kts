plugins {
	java
	checkstyle
	idea
	`java-library`
	`maven-publish`
	signing
	id("com.github.ben-manes.versions") version libs.versions.ben.manes.versions.get()
}

java {
	withJavadocJar()
	withSourcesJar()
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "checkstyle")
	apply(plugin = "maven-publish")
	apply(plugin = "signing")

	repositories {
		mavenCentral()
	}

	dependencies {
		compileOnly(rootProject.libs.lombok)
		annotationProcessor(rootProject.libs.lombok)

		testImplementation(rootProject.libs.jackson.databind)
		testImplementation(rootProject.libs.junit.api)
		testImplementation(rootProject.libs.junit.params)
		testRuntimeOnly(rootProject.libs.junit.engine)
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	publishing {
		publications {
			create<MavenPublication>("mavenJava") {
				from(components["java"])

				pom {
					name = "Spring AdmiralXy Commons"
					description = "Utility library for Spring Boot applications"
					url = "https://github.com/AdmiralXy/spring-admiralxy-commons"
					packaging = "jar"

					licenses {
						license {
							name = "The Apache License, Version 2.0"
							url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
						}
					}
					developers {
						developer {
							id = "admiralxy"
							name = "AdmiralXy"
							email = "gameproblem80@gmail.com"
						}
					}
					scm {
						connection = "scm:git:git://github.com/AdmiralXy/spring-admiralxy-commons.git"
						developerConnection = "scm:git:ssh://github.com/AdmiralXy/spring-admiralxy-commons.git"
						url = "https://github.com/AdmiralXy/spring-admiralxy-commons"
					}
				}
			}
		}
		repositories {
			maven {
				val releasesUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
				val snapshotsUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
				url = if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesUrl
				credentials {
					username = project.findProperty("ossrhUsername") as String? ?: System.getenv("OSSRH_USERNAME")
					password = project.findProperty("ossrhPassword") as String? ?: System.getenv("OSSRH_PASSWORD")
				}
			}
		}
	}

	signing {
		useInMemoryPgpKeys(System.getenv("GPG_PRIVATE_KEY"), System.getenv("GPG_PASSPHRASE"))
		sign(publishing.publications["mavenJava"])
	}
}
