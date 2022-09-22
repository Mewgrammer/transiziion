package github.mewgrammer.transizion.statemachine.guards

import github.mewgrammer.transizion.statemachine.Events
import github.mewgrammer.transizion.statemachine.States
import github.mewgrammer.transizion.statemachine.context.ContextExtensions.getExecution
import github.mewgrammer.transizion.statemachine.context.ContextHeader
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.guard.Guard
import org.springframework.stereotype.Component

@Component
class ExecutionGuard : Guard<States, Events>, Logging {
    override fun evaluate(context: StateContext<States, Events>): Boolean {
        logger.info("run-guard $context")
        return context.getExecution() != null
    }
}