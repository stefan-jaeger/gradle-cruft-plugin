package de.x3.gradle.plugin.cruft.task

import de.x3.gradle.plugin.cruft.ext.CruftExtension
import de.x3.gradle.plugin.cruft.ext.CruftLinkExtension
import org.gradle.api.GradleException
import org.gradle.api.tasks.Internal
import org.gradle.process.ExecOperations

import javax.inject.Inject

import static de.x3.gradle.plugin.cruft.task.AbstractCruftTask.CruftTask.LINK

class LinkCruftTask extends AbstractCruftTask {

    @Internal
    CruftTask taskType = LINK

    @Inject
    LinkCruftTask(ExecOperations execOperations) {
        super(execOperations)
    }

    @Override
    List<String> getArguments(CruftExtension ext) {
        CruftLinkExtension linkExtension = ext.extensions.getByType(CruftLinkExtension)
        def args = ['-y']

        if (linkExtension.projectDir != null && !linkExtension.projectDir.empty) {
            args += ['-p', linkExtension.projectDir]
        } else if (ext.projectDir != null && !ext.projectDir.empty) {
            args += ['-p', ext.projectDir]
        }

        if (linkExtension.checkout != null && !linkExtension.checkout.empty) {
            args += ['-c', linkExtension.checkout]
        } else if (ext.checkout != null && !ext.checkout.empty) {
            args += ['-c', ext.checkout]
        }

        if (linkExtension.configFile != null && !linkExtension.configFile.empty) {
            args += ['--config-file', linkExtension.configFile]
        }

        if (linkExtension.defaultConfig) {
            args += '-d'
        }

        if (linkExtension.extraContext != null && !linkExtension.extraContext.empty) {
            args += ['--extra-context', linkExtension.extraContext]
        }

        if (linkExtension.directory != null && !linkExtension.directory) {
            args += ['--directory', linkExtension.directory]
        }

        if (linkExtension.template == null || linkExtension.template.empty) {
            throw new GradleException("Template must be specified in the cruft config.")
        }

        args += linkExtension.template
        args
    }
}
