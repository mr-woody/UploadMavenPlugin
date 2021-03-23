package com.woodys.upload.jitpack

import com.android.build.gradle.LibraryPlugin
import com.woodys.upload.jitpack.extension.JitPackUploadConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.bundling.Jar

/**
 * The jit pack upload plugin.
 * https://jitpack.io/
 */
class JitPackUploadPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        //We only handle root project.
        if(project.rootProject==project){
            println("> Apply jit pack upload plugin")
            project.plugins.apply('jitpack.io.upload')
            def uploadConfiguration = project.extensions.create("jitpack", JitPackUploadConfiguration)
            def version = uploadConfiguration.getVersion()
            //Add the classpath programmatically
//            project.buildscript.dependencies {
//                classpath "com.github.dcendents:android-maven-gradle-plugin:$version"
//            }
            project.allprojects { childProject->
                //Apply all the upload configuration.
                childProject.afterEvaluate {
                    //Apply the configuration to the project.
                    applyProjectUploadConfiguration(childProject,uploadConfiguration)
                }
            }
        }
    }

    /**
     * Apply all the upload configuration.
     * @param project
     */
    void applyProjectUploadConfiguration(Project project, JitPackUploadConfiguration uploadConfiguration){
        def moduleList = uploadConfiguration.getModuleList()
        moduleList.each { module->
            if(project.name==module.name){
                if(project.plugins.hasPlugin(JavaPlugin)){
                    project.plugins.apply('maven')
                    project.setGroup(module.group)
                    println("Java-module:${project.name} set group:${module.group}")
                    prepareJARArtifacts(project)
                } else if(project.plugins.hasPlugin(LibraryPlugin)){
                    project.plugins.apply('com.github.dcendents.android-maven')
                    project.setGroup(module.group)
                    println("Android-module:${project.name} set group:${module.group}")
                    prepareAARArtifacts(project)
                }
            }
        }
    }

    private void prepareJARArtifacts(Project project) {
        Jar sourcesJar = project.task("sourcesJar", type: Jar) {
            classifier = 'sources'
            from project.sourceSets.main.allSource
        }
        project.artifacts {
            archives sourcesJar
        }
    }

    private void prepareAARArtifacts(Project project) {
        def androidSourcesJar = project.tasks.create([name: "sourcesJar", type: Jar]) {
            classifier = 'sources'
            from project.android.sourceSets.main.javaDirectories
        }
        project.artifacts {
            archives androidSourcesJar
        }
    }

}
