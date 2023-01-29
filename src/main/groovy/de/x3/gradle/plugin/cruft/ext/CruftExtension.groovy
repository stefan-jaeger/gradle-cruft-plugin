package de.x3.gradle.plugin.cruft.ext

import groovy.transform.CompileStatic

@CompileStatic
class CruftExtension {

    /**
     *  Path to the project directory.
     */
    String projectDir
    /**
     * The git reference to check against. Supports branches, tags and commit hashes.
     */
    String checkout
    /**
     *  If enabled, ensures that the project commit is exactly the same as the checked out cookiecutter template.
     *  If disabled, the check passes if the checked out cookiecutter template commit is an ancestor of the project commit.
     */
    boolean strict = true
}
