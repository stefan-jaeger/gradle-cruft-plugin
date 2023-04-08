package de.x3.gradle.plugin.cruft.task

import de.x3.gradle.plugin.cruft.ext.CruftCheckExtension
import de.x3.gradle.plugin.cruft.ext.CruftExtension
import org.gradle.api.tasks.Internal
import org.gradle.process.ExecOperations

import javax.inject.Inject

import static de.x3.gradle.plugin.cruft.task.AbstractCruftTask.CruftTask.CHECK

class CheckCruftTask extends AbstractCruftTask {

    @Internal
    CruftTask taskType = CHECK

    @Override
    List<String> getArguments(CruftExtension cruftExtension) {
        CruftCheckExtension checkExtension = cruftExtension.check
        String[] args = []

        def projectDir = cruftExtension.projectDir.orElse(checkExtension.projectDir)
        if (projectDir.present && !projectDir.get().empty) {
            args += ['-p', projectDir.get()]
        }

        def checkout = cruftExtension.checkout.orElse(checkExtension.projectDir)
        if (checkout.present && !checkout.get().empty) {
            args += ['-c', checkout.get()]
        }

        def strict = cruftExtension.strict.orElse(checkExtension.strict)
        if (!strict.get()) {
            args += '--not-strict'
        }

        args
    }
}
