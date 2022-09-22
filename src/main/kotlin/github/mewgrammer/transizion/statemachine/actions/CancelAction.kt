package github.mewgrammer.transizion.statemachine.actions

import github.mewgrammer.transizion.statemachine.Events
import github.mewgrammer.transizion.statemachine.States
import github.mewgrammer.transizion.statemachine.context.ContextExtensions.getExecution
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action
import org.springframework.stereotype.Component

@Component
class CancelAction : Action<States, Events>, Logging {
    override fun execute(context: StateContext<States, Events>) {
        logger.info("cancel-action on event ${context.event} for execution ${context.getExecution()}")
    }
}