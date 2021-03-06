package javaposse.jobdsl.dsl.helpers.publisher

import javaposse.jobdsl.dsl.Context
import javaposse.jobdsl.dsl.ContextHelper
import javaposse.jobdsl.dsl.DslContext

class SonarContext implements Context {
    String installationName
    String branch
    String additionalProperties
    boolean overrideTriggers
    final SonarTriggersContext sonarTriggersContext = new SonarTriggersContext()

    /**
     * Sets the Sonar installation, only applicable when multiple Sonar installations are defined.
     *
     * @since 1.43
     */
    void installationName(String installationName) {
        this.installationName = installationName
    }

    /**
     * Sets the {@code sonar.branch} property.
     */
    void branch(String branch) {
        this.branch = branch
    }

    /**
     * Sets the additional properties to pass to Maven.
     *
     * @since 1.39
     */
    void additionalProperties(String additionalProperties) {
        this.additionalProperties = additionalProperties
    }

    /**
     * Overrides the default trigger actions set at SonarQube installation level.
     */
    void overrideTriggers(@DslContext(SonarTriggersContext) Closure sonarTriggersClosure) {
        overrideTriggers = true
        ContextHelper.executeInContext(sonarTriggersClosure, sonarTriggersContext)
    }
}
