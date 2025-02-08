plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    id("java")
}

group = "com.fiapgrupo27"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Dependências do AWS SDK para S3 e SQS
    implementation("software.amazon.awssdk:s3:2.20.20")
    implementation("software.amazon.awssdk:sqs:2.20.20")
    implementation("org.springframework.boot:spring-boot-starter-amqp")

    // Banco de Dados (ajuste conforme necessário)
    implementation("org.postgresql:postgresql:42.5.1")


    implementation("software.amazon.awssdk:s3:2.20.20")
    implementation("software.amazon.awssdk:apache-client:2.20.20") // 🔥 Adicionando Apache HTTP Client






    // Testes
    testImplementation("org.springframework.boot:spring-boot-starter-test")



}

tasks.withType<Test> {
    useJUnitPlatform()
}
