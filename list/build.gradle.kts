plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
}

android {
    compileSdkVersion(31)

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.incremental" to "true",
                    "room.expandProjection" to "true",
                    "deepLink.incremental" to "true",
                    "deepLink.customAnnotations" to "com.airbnb.AppDeepLink|com.airbnb.WebDeepLink",
//                    "deepLinkDoc.output" to "${buildDir}/doc/deeplinks.txt",
                )
            }
//                annotationProcessorOptions.compilerArgumentProviders.add(DeepLinkDispatchDirProvider(File(buildDir.path + "/deeplink")))
        }
    }
}
androidComponents {
    onVariants { variant ->
        variant.javaCompilation.annotationProcessor.argumentProviders.add(DeepLinkDispatchDirProvider(File(buildDir.path + "/deeplink/${variant.name}")))
        variant.javaCompilation.annotationProcessor.argumentProviders.add(RoomSchemaDirProvider(File(buildDir.path + "/schemas/${variant.name}")))
    }
}

android.libraryVariants.all { variant ->
        variant.javaCompileOptions.annotationProcessorOptions.compilerArgumentProviders.add(
            DeepLinkDispatchDirProvider(File(buildDir.path + "/" + variant.name))
        )
        variant.javaCompileOptions.annotationProcessorOptions.compilerArgumentProviders.add(RoomSchemaDirProvider(File(projectDir.parent + "/schemas")))
}

class RoomSchemaDirProvider(
    @get:org.gradle.api.tasks.OutputDirectory
    val outputDir : File
) : CommandLineArgumentProvider{

    override fun asArguments(): Iterable<String> {
        return listOf("-Aroom.schemaLocation=${outputDir.path}")
    }
}


class DeepLinkDispatchDirProvider(
    @get:org.gradle.api.tasks.OutputDirectory
    val outputDir : File
) : CommandLineArgumentProvider{

    override fun asArguments(): Iterable<String> {
        return listOf("-AdeepLinkDoc.output=${outputDir.path}/doc/deeplinks.txt")
    }
}

dependencies {
    val roomVersion = "2.4.1"

    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    val deepLinkDispatchVersion = "5.4.2"
    implementation("com.airbnb:deeplinkdispatch:$deepLinkDispatchVersion")
    kapt("com.airbnb:deeplinkdispatch-processor:$deepLinkDispatchVersion")
}