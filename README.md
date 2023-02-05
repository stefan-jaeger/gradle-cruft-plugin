# Gradle Plugin for Cruft

This Plugin integrated basic [Cruft](https://github.com/cruft/cruft) tasks as part of your build.
This Plugin **does not install python pip or cruft itself**. A valid setup is mandatory for this Plugin to work.

See how to [install](https://github.com/cruft/cruft#installation) cruft.

The only intention of this Plugin is to simplify the usage of cruft and to integrate it into gradle builds.

Features:

* Check if the linked Cookiecutter template has been updated
* Show the diff between the project and the current cruft template.
* Link an existing project to a Cookiecutter template
* Update the project to the latest version of the linked Cookiecutter template

## Documentation

* Gradle Tasks `cruft`
    - `checkCruft`
    - `diffCruft`
    - `linkCruft`
    - `updateCruft`

## Setup

```groovy
buildscript {
    repositories {
      maven {
        url "https://plugins.gradle.org/m2/"
      }
    }
    dependencies {
        classpath 'de.0x3.cruft:1.1.0'
    }
}
apply plugin: 'de.0x3.cruft'
```

OR

```groovy
pluginManagement {
  repositories {
    gradlePluginPortal()
  }
}
plugins {
  id 'de.0x3.cruft' version '1.1.0'
}
```

### Compatibility

#### Java

This plugin was compiled for java 11 and should be compatible with java 17+.
additionally all requirements of [cruft](https://github.com/cruft/cruft) apply to this Plugin as well.

#### Gradle

| Gradle | Version |
|--------|---------|
| 7.x    | 1.0.0   |
| 7.x    | 1.1.0   |

#### Python & Pip

Make sure python and pip are installed:

```bash
python --version  
pip --version
```

## Configuration

````groovy
cruft {
    projectDir = '.'    // Path to the project directory
    checkout = '123asd' // The git reference to check against. Supports branches, tags and commit hashes.
    strict = true
    // If enabled, ensures that the project is updated to be the same as the checked out cookiecutter template
    check {
        projectDir = '.'    // Overwrite global configuration
        checkout = '123asd' // Overwrite global configuration
        strict = true   // Overwrite global configuration
    }
    diff { // diffCruft task configuration
        projectDir = '.'    // Overwrite global configuration
        checkout = '123asd' // Overwrite global configuration
        exitCode = false    // Overwrite global configuration
    }
    link { // linkCruft task configuration
        projectDir = '.'    // Overwrite global configuration
        checkout = '123asd' // Overwrite global configuration
        configFile = 'config.json'  // Path to the Cookiecutter user config file
        defaultConfig = false   // Do not load a config file. Use the defaults instead
        extraContext = '{}' // A JSON string describing any extra context to pass to cookiecutter.
        template = 'ssh://gitrepo/template.git' //
        directory = 'cookiecutter'  // Directory within repo that holds cookiecutter.json file for advanced repositories
        // with multi templates in it
    }
    update { // update task configuration
        projectDir = '.'    // Overwrite global configuration
        checkout = '123asd' // Overwrite global configuration
        refreshPrivateVariables = false // Refresh cookiecutter private variables for the latest template version
        skipUpdate = false  // Skip the template updates but update the cruft state
        strict = true   // Overwrite global configuration
        allowUntrackedFiles = false // Allow the project's cruft to be updated if there are untracked files in the git
    }
}
````

### Reference Links

* [Cruft](https://github.com/cruft/cruft)
* [Cookiecutter](https://github.com/cookiecutter/cookiecutter)
* [Python](https://www.python.org/)
* [Pip](https://pypi.org/project/pip/)