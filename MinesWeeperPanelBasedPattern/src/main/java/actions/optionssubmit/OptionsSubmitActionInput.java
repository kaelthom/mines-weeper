package actions.optionssubmit;

import dto.DeminorGameProperties;

public class OptionsSubmitActionInput {

    private DeminorGameProperties gameProperties;

    public OptionsSubmitActionInput(DeminorGameProperties properties) {
        setGameProperties(properties);
    }

    DeminorGameProperties getGameProperties() {
        return gameProperties;
    }

    private void setGameProperties(DeminorGameProperties properties) {
        this.gameProperties = properties;
    }

}
