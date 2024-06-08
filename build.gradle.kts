plugins {
	java
	checkstyle
	idea
	`maven-publish`
}

java {
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
			}
		}
	}
}
