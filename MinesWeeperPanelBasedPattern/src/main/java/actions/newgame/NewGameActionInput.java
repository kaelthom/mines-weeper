package actions.newgame;

import dto.DeminorGameProperties;

public class NewGameActionInput {

    private DeminorGameProperties deminorProperties;

    public NewGameActionInput(DeminorGameProperties deminorProperties) {
        setDeminorProperties(deminorProperties);
    }

    DeminorGameProperties getDeminorProperties() {
        return deminorProperties;
    }

    private void setDeminorProperties(DeminorGameProperties deminorProperties) {
        this.deminorProperties = deminorProperties;
    }

}
