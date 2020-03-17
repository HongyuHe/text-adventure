package cork.engine_state;

import cork.Engine;

class GameRunning implements IState {
    @Override
    public void
    next(EngineStateMachine s) { s.setState(new HomeScreen()); }

    @Override
    public void
    execute(final Engine e) { e.runGame(); }
}
