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
							name = "GNU General Public License v3.0"
							url = "https://www.gnu.org/licenses/gpl-3.0.html"
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
						connection = "scm:https://github.com/AdmiralXy/spring-admiralxy-commons.git"
						developerConnection = "scm:git@github.com:AdmiralXy/spring-admiralxy-commons.git"
						url = "https://github.com/AdmiralXy/spring-admiralxy-commons"
					}
				}
			}
		}
		repositories {
			maven {
				name = "GitHubPackages"
				url = uri("https://maven.pkg.github.com/AdmiralXy/spring-admiralxy-commons")
				credentials {
					username = System.getenv("GITHUB_ACTOR")
					password = System.getenv("GITHUB_TOKEN")
				}
			}
		}
	}

	signing {
		useInMemoryPgpKeys(System.getenv("GPG_PRIVATE_KEY"), System.getenv("GPG_PASSPHRASE"))
		sign(publishing.publications["mavenJava"])
	}
}
