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

    @Inject
    CheckCruftTask(ExecOperations execOperations) {
        super(execOperations)
    }

    @Override
    List<String> getArguments(CruftExtension ext) {
        CruftCheckExtension checkExtension = ext.extensions.getByType(CruftCheckExtension)
        String[] args = []

        if (checkExtension.projectDir != null && !checkExtension.projectDir.empty) {
            args += ['-p', ext.check.projectDir]
        } else if (ext.projectDir != null && !ext.projectDir.empty) {
            args += ['-p', ext.projectDir]
        }

        if (checkExtension.checkout != null && !checkExtension.checkout.empty) {
            args += ['-c', checkExtension.checkout]
        } else if (ext.checkout != null && !ext.checkout.empty) {
            args += ['-c', ext.checkout]
        }

        if (!checkExtension.strict) {
            args += '--not-strict'
        } else if (!ext.strict) {
            args += '--not-strict'
        }

        args
    }
}
