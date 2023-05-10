package com.yuriisurzhykov.goaldestinator.goalscreation

import com.yuriisurzhykov.goaldestinator.core.data.Serialization
import com.yuriisurzhykov.goaldestinator.core.data.cache.ObjectStorage

interface ProcessParameter : java.io.Serializable {

    fun putStepParameter(parameter: StepParameter)

    abstract class Abstract(
        private val serialization: Serialization,
        private val objectStorage: ObjectStorage
    ) : ProcessParameter {

        private val stepParams = mutableSetOf<StepParameter>()

        override fun putStepParameter(parameter: StepParameter) {
            stepParams.add(parameter)
        }
    }
}