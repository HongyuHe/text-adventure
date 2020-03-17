package cork.engine_state;

import cork.Engine;

public class EngineStateMachine {
    private static EngineStateMachine fsm = new EngineStateMachine();
    private IState state = new HomeScreen();

    private
    EngineStateMachine()
    {}

    public static EngineStateMachine
    instance() { return fsm; }

    public void
    setState(final IState s) { state = s; }

    public void
    execute(Engine e)
    {
        state.execute(e);
        state.next(this);
    }
}
