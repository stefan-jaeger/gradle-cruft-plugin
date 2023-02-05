package de.x3.gradle.plugin.cruft.ext

import de.x3.gradle.plugin.cruft.properties.CruftProperties
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

import javax.inject.Inject

abstract class CruftLinkExtension extends AbstractCruftExtension {

    private static final String CONFIG_FILE = "cruft.configFile"
    private static final String DEFAULT_CONFIG = "cruft.defaultConfig"
    private static final String EXTRA_CONTEXT = "cruft.extraContext"
    private static final String TEMPLATE = "cruft.template"
    private static final String DIRECTORY = "cruft.directory"

    @Inject
    CruftLinkExtension() {
        getProjectDir().convention(providers.gradleProperty(CruftProperties.PROJECT_DIR))
        getCheckout().convention(providers.gradleProperty(CruftProperties.CHECKOUT))
        getConfigFile().convention(providers.gradleProperty(CONFIG_FILE))
        getDefaultConfig().convention(providers.gradleProperty(DEFAULT_CONFIG).map({ true }).orElse(false))
        getExtraContext().convention(providers.gradleProperty(EXTRA_CONTEXT))
        getTemplate().convention(providers.gradleProperty(TEMPLATE))
        getDirectory().convention(providers.gradleProperty(DIRECTORY))
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
     *  Path to the Cookiecutter user config file
     */
    @Input
    @Optional
    abstract Property<String> getConfigFile()

    /**
     *  Do not load a config file. Use the defaults instead
     */
    @Input
    @Optional
    abstract Property<Boolean> getDefaultConfig()

    /**
     *  A JSON string describing any extra context to pass
     */
    @Input
    @Optional
    abstract Property<String> getExtraContext()

    /**
     * Git repository url of the cookiecutter template to use.
     */
    @Input
    @Optional
    abstract Property<String> getTemplate()

    /**
     * Directory within repo that holds cookiecutter.json file for advanced repositories with multi templates in it
     */
    @Input
    @Optional
    abstract Property<String> getDirectory()

}
