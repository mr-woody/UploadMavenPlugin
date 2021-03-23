package com.woodys.upload.jitpack.extension

import com.android.repository.api.Repository
import org.gradle.util.ConfigureUtil

/**
 * @author Created by woodys
 * @date 2020-05-20 21:45
 * @email 1126422054@qq.com
 */
class JitPackUploadConfiguration {
    /**
     * All the modules.
     */
    private List<Repository> moduleList =[]

    private String version="2.1"

    JitPackUploadConfiguration() {
    }

    /**
     * One module configuration closure.
     */
    void module(Closure closure){
        Module module = new Module()
        ConfigureUtil.configure(closure, module)
        moduleList.add(module)
    }

    void version(String version){
        this.version=version
    }

    def getVersion = { ->
        return version
    }

    List<Module> getModuleList() {
        return moduleList
    }

    class Module{
        String name
        String group

        void name(String name){
            this.name=name
        }

        void group(String group){
            this.group=group
        }
    }
}
