package actions.optionssubmit;

import dto.DeminorGameProperties;
import views.panels.OptionsLevelPanel;

public class OptionsSubmitActionInput {
	
	public OptionsSubmitActionInput(DeminorGameProperties properties, OptionsLevelPanel optionsLevelPanel) {
		this.properties = properties;
		this.optionsLevelPanel = optionsLevelPanel;
	}

	private DeminorGameProperties properties;
	
	private OptionsLevelPanel optionsLevelPanel;

	public DeminorGameProperties getProperties() {
		return properties;
	}

	public void setProperties(DeminorGameProperties properties) {
		this.properties = properties;
	}

	public OptionsLevelPanel getOptionsLevelPanel() {
		return optionsLevelPanel;
	}

	public void setOptionsLevelPanel(OptionsLevelPanel optionsLevelPanel) {
		this.optionsLevelPanel = optionsLevelPanel;
	}

}
