package de.x3.gradle.plugin.cruft.task

import de.x3.gradle.plugin.cruft.ext.CruftExtension
import de.x3.gradle.plugin.cruft.ext.CruftUpdateExtension
import org.gradle.api.tasks.Internal
import org.gradle.process.ExecOperations

import javax.inject.Inject

import static de.x3.gradle.plugin.cruft.task.AbstractCruftTask.CruftTask.UPDATE

class UpdateCruftTask extends AbstractCruftTask {

    @Internal
    CruftTask taskType = UPDATE

    @Override
    List<String> getArguments(CruftExtension cruftExtension) {
        CruftUpdateExtension updateExtension = cruftExtension.update
        String[] args = []

        def projectDir = cruftExtension.projectDir.orElse(updateExtension.projectDir)
        if (projectDir.present && !projectDir.get().empty) {
            args += ['-p', projectDir.get()]
        }

        if (updateExtension.skipUpdate.get()) {
            args += '-s'
        } else {
            args += '-y'
        }

        def checkout = cruftExtension.checkout.orElse(updateExtension.checkout)
        if (checkout.present && !checkout.get().empty) {
            args += ['-c', checkout.get()]
        }

        if (updateExtension.refreshPrivateVariables.get() ) {
            args += '-r'
        }

        def strict = cruftExtension.strict.orElse(updateExtension.strict)
        if (!strict.get()) {
            args += '--not-strict'
        }

        if (updateExtension.allowUntrackedFiles.get()) {
            args += '--allow-untracked-files'
        }

        args
    }
}
