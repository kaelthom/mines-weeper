package views.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MouseReleasedOnlyListener extends MouseListener {

	@Override
	public default void mouseClicked(MouseEvent e) {
		// Nothing to do
	}

	@Override
	public default void mousePressed(MouseEvent e) {
		// Nothing to do
	}

	@Override
	public default void mouseEntered(MouseEvent e) {
		// Nothing to do
	}

	@Override
	public default void mouseExited(MouseEvent e) {
		// Nothing to do
	}

}
