package views.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MouseReleasedOnlyListener extends MouseListener {

    @Override
    default void mouseClicked(MouseEvent e) {
        // Nothing to do
    }

    @Override
    default void mousePressed(MouseEvent e) {
        // Nothing to do
    }

    @Override
    default void mouseEntered(MouseEvent e) {
        // Nothing to do
    }

    @Override
    default void mouseExited(MouseEvent e) {
        // Nothing to do
    }

}
