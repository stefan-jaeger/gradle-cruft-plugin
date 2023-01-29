package de.x3.gradle.plugin.cruft.ext

class CruftUpdateExtension {
    /**
     *  Path to the project directory.
     */
    String projectDir
    /**
     * The git reference to check against. Supports branches, tags and commit hashes.
     */
    String checkout

    /**
     * Refresh cookiecutter private variables for the latest template version
     */
    Boolean refreshPrivateVariables
    /**
     * Skip the template updates but update the cruft state
     */
    Boolean skipUpdate
    /**
     *  If enabled, ensures that the project commit is exactly the same as the checked out cookiecutter template.
     *  If disabled, the check passes if the checked out cookiecutter template commit is an ancestor of the project commit.
     */
    Boolean strict
    /**
     * Allow the project's cruft to be updated if there are untracked files in the git repository (but no other changes)
     */
    Boolean allowUntrackedFiles
}
