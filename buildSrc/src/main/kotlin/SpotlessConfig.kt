import com.diffplug.spotless.extra.wtp.EclipseWtpFormatterStep
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

fun Project.configureSpotless() {
    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {

        ratchetFrom("origin/develop")

        format("misc") {
            target("**/*.md", "**/.gitignore, **/CODEOWNERS")
            indentWithSpaces()
            trimTrailingWhitespace()
            endWithNewline()
        }
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/")
            ktlint()
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
        }
        format("xml") {
            target("**/*.xml")
            eclipseWtp(EclipseWtpFormatterStep.XML).configFile("$rootDir/config/spotless.xml.prefs")
        }
    }
}
