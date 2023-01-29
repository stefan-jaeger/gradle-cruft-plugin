package de.x3.gradle.plugin.cruft.ext

class CruftDiffExtension {

    /**
     *  Path to the project directory.
     */
    String projectDir
    /**
     * The git reference to check against. Supports branches, tags and commit hashes.
     */
    String checkout
    /**
     * Exit with status 1 on non-empty diff.
     */
    Boolean exitCode

}
