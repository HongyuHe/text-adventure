package cork.engine_state;

import cork.Engine;

class MainMenu implements IState {
    @Override
    public void
    next(EngineStateMachine s) { s.setState(new GameMenu()); }

    @Override
    public void
    execute(final Engine e) { e.displayMainMenu(); }
}
