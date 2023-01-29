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

    List<String> getArguments(CruftExtension ext) {
        CruftDiffExtension diffExtension = ext.extensions.getByType(CruftDiffExtension)
        def args = []

        if (diffExtension.projectDir != null && !diffExtension.projectDir.empty) {
            args += ['-p', diffExtension.projectDir]
        } else if (ext.projectDir != null && !ext.projectDir.empty) {
            args += ['-p', ext.projectDir]
        }

        if (diffExtension.exitCode) {
            args += '-e'
        }

        if (diffExtension.checkout != null && !diffExtension.checkout.empty) {
            args += ['-c', diffExtension.checkout]
        } else if (ext.checkout != null && !ext.checkout.empty) {
            args += ['-c', ext.checkout]
        }

        args
    }
}
