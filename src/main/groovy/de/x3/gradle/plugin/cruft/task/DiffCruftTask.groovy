package de.x3.gradle.plugin.cruft.task

import de.x3.gradle.plugin.cruft.ext.CruftDiffExtension
import de.x3.gradle.plugin.cruft.ext.CruftExtension
import org.gradle.api.tasks.Internal
import org.gradle.process.ExecOperations

import javax.inject.Inject

class DiffCruftTask extends AbstractCruftTask {

    @Internal
    CruftTask taskType = CruftTask.DIFF

    @Inject
    DiffCruftTask(ExecOperations execOperations) {
        super(execOperations, true)
    }

    @Override
    List<String> getArguments(CruftExtension cruftExtension) {
        CruftDiffExtension diffExtension = cruftExtension.diff
        def args = []

        def projectDir = cruftExtension.projectDir.orElse(diffExtension.projectDir)
        if (projectDir.present && !projectDir.get().empty) {
            args += ['-p', projectDir.get()]
        }

        if (diffExtension.exitCode.get()) {
            args += '-e'
        }

        def checkout = cruftExtension.checkout.orElse(diffExtension.checkout)
        if (checkout.present && !checkout.get().empty) {
            args += ['-c', checkout.get()]
        }

        args
    }
}
