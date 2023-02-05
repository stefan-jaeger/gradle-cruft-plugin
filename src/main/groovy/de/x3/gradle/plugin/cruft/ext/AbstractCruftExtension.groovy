package de.x3.gradle.plugin.cruft.ext

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ProviderFactory

import javax.inject.Inject

abstract class AbstractCruftExtension {

    @Inject
    protected abstract ProviderFactory getProviders()

    @Inject
    protected abstract ObjectFactory getObjects()

}
