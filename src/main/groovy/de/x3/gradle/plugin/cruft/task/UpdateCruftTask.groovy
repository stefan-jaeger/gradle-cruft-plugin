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

    @Inject
    UpdateCruftTask(ExecOperations execOperations) {
        super(execOperations)
    }

    @Override
    List<String> getArguments(CruftExtension ext) {
        CruftUpdateExtension updateExtension = ext.extensions.getByType(CruftUpdateExtension)
        String[] args = []

        if (updateExtension.projectDir != null && !updateExtension.projectDir.empty) {
            args += ['-p', updateExtension.projectDir]
        } else if (ext.projectDir != null && !ext.projectDir.empty) {
            args += ['-p', ext.projectDir]
        }

        if (updateExtension.skipUpdate) {
            args += '-s'
        } else {
            args += '-y'
        }

        if (updateExtension.checkout != null && !updateExtension.checkout.empty) {
            args += ['-c', updateExtension.checkout]
        } else if (ext.checkout != null && !ext.checkout.empty) {
            args += ['-c', ext.checkout]
        }

        if (updateExtension.refreshPrivateVariables) {
            args += '-r'
        }

        if (!updateExtension.strict || !ext.strict) {
            args += '--not-strict'
        }

        if (updateExtension.allowUntrackedFiles) {
            args += '--allow-untracked-files'
        }

        args
    }
}
