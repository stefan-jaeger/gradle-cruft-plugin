package de.x3.gradle.plugin.cruft.ext

import de.x3.gradle.plugin.cruft.properties.CruftProperties
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

import javax.inject.Inject

abstract class CruftDiffExtension extends AbstractCruftExtension {

    private final String EXIT_CODE = 'cruft.exitCode'

    @Inject
    CruftDiffExtension() {
        getProjectDir().convention(providers.gradleProperty(CruftProperties.PROJECT_DIR))
        getCheckout().convention(providers.gradleProperty(CruftProperties.CHECKOUT))
        getExitCode().convention(providers.gradleProperty(EXIT_CODE).map({ true }).orElse(false))
    }

    /**
     *  Path to the project directory.
     */
    @Input
    @Optional
    abstract Property<String> getProjectDir()

    /**
     * The git reference to check against. Supports branches, tags and commit hashes.
     */
    @Input
    @Optional
    abstract Property<String> getCheckout()

    /**
     * Exit with status 1 on non-empty diff.
     */
    @Input
    @Optional
    abstract Property<Boolean> getExitCode()

}
