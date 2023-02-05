package de.x3.gradle.plugin.cruft.ext

import de.x3.gradle.plugin.cruft.properties.CruftProperties
import org.gradle.api.Action
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.Optional

import javax.inject.Inject

abstract class CruftExtension extends AbstractCruftExtension {

    @Inject
    CruftExtension() {
        getProjectDir().convention(providers.gradleProperty(CruftProperties.PROJECT_DIR))
        getCheckout().convention(providers.gradleProperty(CruftProperties.CHECKOUT))
        getStrict().convention(providers.gradleProperty(CruftProperties.NOT_STRICT).map({ false }).orElse(true))
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
     *  If enabled, ensures that the project commit is exactly the same as the checked out cookiecutter template.
     *  If disabled, the check passes if the checked out cookiecutter template commit is an ancestor of the project commit.
     */
    @Input
    @Optional
    abstract Property<Boolean> getStrict()

    @Nested
    final CruftCheckExtension check = objects.newInstance(CruftCheckExtension)

    @Nested
    final CruftDiffExtension diff = objects.newInstance(CruftDiffExtension)

    @Nested
    final CruftLinkExtension link = objects.newInstance(CruftLinkExtension)

    @Nested
    final CruftUpdateExtension update = objects.newInstance(CruftUpdateExtension)

    void check(Action<CruftCheckExtension> action) {
        action.execute(check)
    }

    void diff(Action<CruftDiffExtension> action) {
        action.execute(diff)
    }

    void link(Action<CruftLinkExtension> action) {
        action.execute(link)
    }

    void update(Action<CruftUpdateExtension> action) {
        action.execute(update)
    }

}
