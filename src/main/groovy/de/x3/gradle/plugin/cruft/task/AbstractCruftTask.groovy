package de.x3.gradle.plugin.cruft.task

import de.x3.gradle.plugin.cruft.ext.CruftExtension
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations

abstract class AbstractCruftTask extends DefaultTask {

    enum CruftTask {
        CHECK,
        CREATE,
        DIFF,
        LINK,
        UPDATE
    }

    private final ExecOperations execOperations
    private final boolean printOutput

    AbstractCruftTask(ExecOperations execOperations) {
        this.execOperations = execOperations
        this.printOutput = false
    }

    AbstractCruftTask(ExecOperations execOperations, boolean printOutput) {
        this.execOperations = execOperations
        this.printOutput = printOutput
    }

    @Input
    abstract CruftTask getTaskType()

    abstract List<String> getArguments(CruftExtension ext);

    @TaskAction
    def execute() {
        CruftExtension ext = project.extensions.findByType(CruftExtension)
        def cruftArgs = [taskType.name().toLowerCase()] + getArguments(ext)
        def errorOut = new ByteArrayOutputStream()
        def stdOut = new ByteArrayOutputStream()
        def result = execOperations.exec {
            executable = 'cruft'
            args = cruftArgs
            ignoreExitValue = true
            errorOutput = errorOut
            standardOutput = stdOut
        }
        def output = new String(stdOut.toByteArray())
        if (printOutput) {
            print output
        }
        if (result.exitValue != 0) {
            String error = new String(errorOut.toByteArray())
            throw new GradleException(error.empty ? output : error)
        }
    }
}
