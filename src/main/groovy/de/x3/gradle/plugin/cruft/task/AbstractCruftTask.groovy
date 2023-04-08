package de.x3.gradle.plugin.cruft.task

import de.x3.gradle.plugin.cruft.ext.CruftExtension
import org.gradle.api.tasks.Input
import ru.vyarus.gradle.plugin.python.task.PythonTask

abstract class AbstractCruftTask extends PythonTask {

    enum CruftTask {
        CHECK,
        CREATE,
        DIFF,
        LINK,
        UPDATE
    }

    @Input
    abstract CruftTask getTaskType()

    abstract List<String> getArguments(CruftExtension ext);

    @Override
    Object getCommand() {
        CruftExtension ext = project.extensions.findByType(CruftExtension)
        return [taskType.name().toLowerCase()] + getArguments(ext)
    }

    @Override
    String getModule() {
        // restrict commands to cruft module
        return 'cruft'
    }
}
