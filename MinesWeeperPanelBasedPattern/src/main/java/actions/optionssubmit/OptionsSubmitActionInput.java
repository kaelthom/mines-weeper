package actions.optionssubmit;

import dto.DeminorGameProperties;

public class OptionsSubmitActionInput {
	
	public OptionsSubmitActionInput(DeminorGameProperties properties) {
		this.gameProperties = properties;
	}

	private DeminorGameProperties gameProperties;
	
	public DeminorGameProperties getGameProperties() {
		return gameProperties;
	}

	public void setGameProperties(DeminorGameProperties properties) {
		this.gameProperties = properties;
	}

}
