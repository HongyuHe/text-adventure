package cork.engine_state;

import cork.Engine;

interface IState {
    void next(EngineStateMachine s);
    void execute(final Engine e);
}
