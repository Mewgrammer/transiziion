package github.mewgrammer.transizion.statemachine.config

import github.mewgrammer.transizion.statemachine.Events
import github.mewgrammer.transizion.statemachine.States
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor
import org.springframework.statemachine.data.jpa.JpaStateMachineRepository
import org.springframework.statemachine.persist.StateMachineRuntimePersister

@Configuration
class StateMachinePersister {
    @Bean
    fun stateMachineRuntimePersister(
        jpaStateMachineRepository: JpaStateMachineRepository
    ): StateMachineRuntimePersister<States, Events, String> {
        return JpaPersistingStateMachineInterceptor(jpaStateMachineRepository)
    }
}