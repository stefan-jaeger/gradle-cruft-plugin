package de.x3.gradle.plugin.cruft

import de.x3.gradle.plugin.cruft.ext.*
import de.x3.gradle.plugin.cruft.task.CheckCruftTask
import de.x3.gradle.plugin.cruft.task.DiffCruftTask
import de.x3.gradle.plugin.cruft.task.LinkCruftTask
import de.x3.gradle.plugin.cruft.task.UpdateCruftTask
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.vyarus.gradle.plugin.python.PythonExtension
import ru.vyarus.gradle.plugin.python.PythonPlugin

class CruftPlugin implements Plugin<Project> {

    private static String[] DEFAULT_MODULES = [
            'cookiecutter:2.1.1',
            'cruft:2.14.0'
    ]

    @Override
    void apply(Project project) {
        project.extensions.create('cruft', CruftExtension)
        project.plugins.apply(PythonPlugin)
        project.extensions.getByType(PythonExtension).pip(DEFAULT_MODULES)

        registerTask(
                project,
                'checkCruft',
                'Check if the linked Cookiecutter template has been updated',
                CheckCruftTask
        )
        registerTask(
                project,
                'diffCruft',
                'Show the diff between the project and the current cruft template',
                DiffCruftTask
        )
        registerTask(
                project,
                'linkCruft',
                'Link an existing project to a Cookiecutter template',
                LinkCruftTask
        )
        registerTask(
                project,
                'updateCruft',
                'Update the project to the latest version of the linked Cookiecutter template',
                UpdateCruftTask
        )
    }

    static registerTask(Project project, String name, String taskDescription, Class<? extends DefaultTask> taskClass) {
        project.tasks.register(name, taskClass) {
            it.with {
                group = 'cruft'
                description = taskDescription
            }
        }
    }
}
