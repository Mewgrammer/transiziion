package github.mewgrammer.transizion.statemachine

import org.springframework.statemachine.config.StateMachineFactory
import org.springframework.statemachine.persist.StateMachineRuntimePersister
import org.springframework.statemachine.service.DefaultStateMachineService
import org.springframework.stereotype.Service

@Service
class ExecutionService(
    factory: StateMachineFactory<States, Events>,
    persister: StateMachineRuntimePersister<States, Events, String>
) :
    DefaultStateMachineService<States, Events>(factory, persister)