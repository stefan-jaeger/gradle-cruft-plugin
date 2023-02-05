package de.x3.gradle.plugin.cruft.ext

import de.x3.gradle.plugin.cruft.properties.CruftProperties
import org.gradle.api.provider.Property

import javax.inject.Inject

abstract class CruftUpdateExtension extends AbstractCruftExtension {

    private static final String REFRESH_PRIVATE_VARIABLES = 'cruft.refreshPrivateVariables'
    private static final String SKIP_UPDATE = 'cruft.skipUpdate'
    private static final String ALLOW_UNTRACKED_FILES = 'cruft.allowUntrackedFiles'

    @Inject
    CruftUpdateExtension() {
        getProjectDir().convention(providers.gradleProperty(CruftProperties.PROJECT_DIR))
        getCheckout().convention(providers.gradleProperty(CruftProperties.CHECKOUT))
        getRefreshPrivateVariables().convention(providers.gradleProperty(REFRESH_PRIVATE_VARIABLES).map({ true }).orElse(false))
        getSkipUpdate().convention(providers.gradleProperty(SKIP_UPDATE).map({ true }).orElse(false))
        getStrict().convention(providers.gradleProperty(CruftProperties.NOT_STRICT).map({ false }).orElse(true))
        getAllowUntrackedFiles().convention(providers.gradleProperty(ALLOW_UNTRACKED_FILES).map({ true }).orElse(true))
    }

    /**
     *  Path to the project directory.
     */
    abstract Property<String> getProjectDir()

    /**
     * The git reference to check against. Supports branches, tags and commit hashes.
     */
    abstract Property<String> getCheckout()

    /**
     * Refresh cookiecutter private variables for the latest template version
     */
    abstract Property<Boolean> getRefreshPrivateVariables()

    /**
     * Skip the template updates but update the cruft state
     */
    abstract Property<Boolean> getSkipUpdate()

    /**
     *  If enabled, ensures that the project commit is exactly the same as the checked out cookiecutter template.
     *  If disabled, the check passes if the checked out cookiecutter template commit is an ancestor of the project commit.
     */
    abstract Property<Boolean> getStrict()

    /**
     * Allow the project's cruft to be updated if there are untracked files in the git repository (but no other changes)
     */
    abstract Property<Boolean> getAllowUntrackedFiles()

}
