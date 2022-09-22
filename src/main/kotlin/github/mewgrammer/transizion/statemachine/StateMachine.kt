package github.mewgrammer.transizion.statemachine

import github.mewgrammer.transizion.statemachine.actions.CancelAction
import github.mewgrammer.transizion.statemachine.actions.CompleteAction
import github.mewgrammer.transizion.statemachine.actions.PauseAction
import github.mewgrammer.transizion.statemachine.actions.StartAction
import github.mewgrammer.transizion.statemachine.guards.ExecutionGuard
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.persist.StateMachineRuntimePersister


@EnableStateMachineFactory
class StateMachine(
    private val persister: StateMachineRuntimePersister<States, Events, String>,
    private val runGuard: ExecutionGuard,
    private val pauseAction: PauseAction,
    private val completeAction: CompleteAction,
    private val startAction: StartAction,
    private val cancelAction: CancelAction
) : StateMachineConfigurerAdapter<States, Events>() {

    @Throws(Exception::class)
    override fun configure(config: StateMachineConfigurationConfigurer<States, Events>) {
        config
            .withConfiguration()
            .autoStartup(false)
            .and()
            .withPersistence()
            .runtimePersister(persister)
    }

    override fun configure(states: StateMachineStateConfigurer<States, Events>) {
        states
            .withStates()
            .initial(States.IDLE)
            .state(States.RUNNING, startAction)
            .state(States.PAUSED, pauseAction)
            .state(States.CANCELED, cancelAction)
            .state(States.FINISHED, completeAction)
            .states(States.values().toSet())
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<States, Events>) {
        transitions
            .withExternal()
            .source(States.IDLE)
            .target(States.RUNNING)
            .event(Events.STARTED)
            .action(startAction)
            .and()
            .withExternal()
            .source(States.RUNNING)
            .target(States.CANCELED)
            .event(Events.CANCELED)
            .and()
            .withExternal()
            .source(States.RUNNING)
            .target(States.PAUSED)
            .event(Events.PAUSED)
            .and()
            .withExternal()
            .source(States.PAUSED)
            .target(States.CANCELED)
            .event(Events.CANCELED)
            .and()
            .withExternal()
            .source(States.PAUSED)
            .target(States.RUNNING)
            .event(Events.RESUMED)
            .and()
            .withExternal()
            .source(States.RUNNING)
            .target(States.FINISHED)
            .event(Events.COMPLETED)
            .and()
    }


}