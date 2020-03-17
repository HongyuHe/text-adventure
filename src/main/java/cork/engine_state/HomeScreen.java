package cork.engine_state;

import cork.Engine;

class HomeScreen implements IState {
    @Override
    public void
    next(EngineStateMachine s) { s.setState(new MainMenu()); }

    @Override
    public void
    execute(final Engine e) { e.displayHomeScreen(); }
}
