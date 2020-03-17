package cork.engine_state;

import cork.Engine;

class GameMenu implements IState {
    @Override
    public void
    next(EngineStateMachine s) { s.setState(new GameRunning()); }

    @Override
    public void
    execute(final Engine e) { e.displayGameMenu(); }
}
