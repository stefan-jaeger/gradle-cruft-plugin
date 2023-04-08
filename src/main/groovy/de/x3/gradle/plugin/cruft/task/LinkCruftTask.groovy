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

    @Override
    List<String> getArguments(CruftExtension cruftExtension) {
        CruftLinkExtension linkExtension = cruftExtension.link
        def args = ['-y']

        def projectDir = cruftExtension.projectDir.orElse(linkExtension.projectDir)
        if (projectDir.present && !projectDir.get().empty) {
            args += ['-p', projectDir.get()]
        }

        def checkout = cruftExtension.checkout.orElse(linkExtension.checkout)
        if (checkout.present && !checkout.get().empty) {
            args += ['-c', checkout.get()]
        }

        if (linkExtension.configFile.present && !linkExtension.configFile.get().empty) {
            args += ['--config-file', linkExtension.configFile]
        }

        if (linkExtension.defaultConfig.get()) {
            args += '-d'
        }

        if (linkExtension.extraContext.present && !linkExtension.extraContext.get().empty) {
            args += ['--extra-context', linkExtension.extraContext.get()]
        }

        if (linkExtension.directory.present && !linkExtension.directory.get().empty) {
            args += ['--directory', linkExtension.directory]
        }

        if (!linkExtension.template.present || linkExtension.template.get().empty) {
            throw new GradleException("Template must be specified in the cruft config.")
        }

        args += linkExtension.template.get()

        args
    }
}
