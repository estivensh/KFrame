import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("org.gradle.maven-publish")
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    pom {
        name.set("KFrame")
        description.set("KFrame is a multiplatform library that allows you to preview your UI inside simulated device frames—just like an emulator. Supporting Android, iOS, Desktop, and Web, KFrame helps you visualize your app's design and behavior across platforms from a single place.")
        url.set("https://github.com/estivensh/KFrame")
        licenses {
            license {
                name.set("Apache-2.0")
                distribution.set("repo")
                url.set("https://github.com/estivensh/KFrame/blob/master/LICENSE.md")
            }
        }

        developers {
            developer {
                id.set("estivensh4")
                name.set("Estiven Sanchez")
                email.set("estivensh4@gmail.com")
            }
        }

        scm {
            connection.set("scm:git:ssh://github.com/estivensh/KFrame.git")
            developerConnection.set("scm:git:ssh://github.com/estivensh/KFrame.git")
            url.set("https://github.com/estivensh/KFrame")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}
