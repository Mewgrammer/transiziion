package github.mewgrammer.transizion

import github.mewgrammer.transizion.statemachine.Events
import github.mewgrammer.transizion.statemachine.ExecutionService
import github.mewgrammer.transizion.statemachine.States
import github.mewgrammer.transizion.statemachine.context.ContextVariable
import github.mewgrammer.transizion.statemachine.context.Execution
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.messaging.support.MessageBuilder
import reactor.core.publisher.Mono
import java.util.*

@SpringBootTest
class TransizionApplicationTests {

    @Autowired
    private lateinit var service: ExecutionService

    @Test
    fun contextLoads() {
        val stateMachine = service.acquireStateMachine(UUID.randomUUID().toString())
        var execution = Execution().apply {
            name = "test-execution"
        }
        stateMachine.extendedState.variables[ContextVariable.EXECUTION.value] = execution
        stateMachine.startReactively().block()


        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.STARTED).build())).blockLast()
        assertEquals(States.RUNNING, stateMachine.state.id)

        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.PAUSED).build())).blockLast()
        assertEquals(States.PAUSED, stateMachine.state.id)

        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.RESUMED).build())).blockLast()
        assertEquals(States.RUNNING, stateMachine.state.id)

        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.COMPLETED).build())).blockLast()
        assertEquals(States.FINISHED, stateMachine.state.id)

        println(stateMachine)
        stateMachine.stopReactively().block()
    }

}
