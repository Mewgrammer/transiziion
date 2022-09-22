package github.mewgrammer.transizion.statemachine.context

import github.mewgrammer.transizion.statemachine.Events
import github.mewgrammer.transizion.statemachine.States
import org.springframework.statemachine.StateContext
import java.util.UUID

object ContextExtensions {

    fun StateContext<States, Events>.getExecutionId(): UUID {
        return this.messageHeaders[ContextHeader.EXECUTION_ID.value] as UUID
    }

    fun StateContext<States, Events>.getExecution(): Execution? {
        return this.extendedState.variables[ContextVariable.EXECUTION.value] as Execution?
    }

    fun StateContext<States, Events>.setExecution(execution: Execution) {
        this.extendedState.variables[ContextVariable.EXECUTION.value] = execution
    }
}