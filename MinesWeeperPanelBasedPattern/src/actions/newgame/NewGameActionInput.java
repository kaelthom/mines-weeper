package actions.newgame;

import dto.DeminorGameProperties;

public class NewGameActionInput {
	
	private DeminorGameProperties deminorProperties;

	public NewGameActionInput(DeminorGameProperties deminorProperties) {
		this.deminorProperties = deminorProperties;
	}

	public DeminorGameProperties getDeminorProperties() {
		return deminorProperties;
	}

	public void setDeminorProperties(DeminorGameProperties deminorProperties) {
		this.deminorProperties = deminorProperties;
	}
	
}
