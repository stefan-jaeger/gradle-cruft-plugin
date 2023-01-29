package de.x3.gradle.plugin.cruft.ext

class CruftLinkExtension {
    /**
     *  Path to the project directory.
     */
    String projectDir
    /**
     * The git reference to check against. Supports branches, tags and commit hashes.
     */
    String checkout
    /**
     *  Path to the Cookiecutter user config file
     */
    String configFile
    /**
     *  Do not load a config file. Use the defaults instead
     */
    Boolean defaultConfig
    /**
     *  A JSON string describing any extra context to pass
     */
    String extraContext
    /**
     * Git repository url of the cookiecutter template to use.
     */
    String template
    /**
     * Directory within repo that holds cookiecutter.json file for advanced repositories with multi templates in it
     */
    String directory
}
