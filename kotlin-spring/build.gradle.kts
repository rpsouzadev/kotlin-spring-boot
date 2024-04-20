import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	id("org.flywaydb.flyway") version "10.11.1"
}

group = "com.rpsouza"
version = "0.0.1-SNAPSHOT"

sourceSets {
	create("mysqlSupport") {
		java {
			srcDir("src/mysql/java")
		}
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_21

	registerFeature("mysqlSupport") {
		usingSourceSet(sourceSets["mysqlSupport"])
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("org.flywaydb:flyway-core:10.11.1")
	implementation("org.flywaydb:flyway-mysql:10.11.1")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("com.github.dozermapper:dozer-core:7.0.0")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.mockito:mockito-core:3.+")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
