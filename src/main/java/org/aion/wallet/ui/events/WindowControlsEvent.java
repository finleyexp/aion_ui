package org.aion.wallet.ui.events;

import javafx.scene.Node;

public class WindowControlsEvent extends AbstractUIEvent<WindowControlsEvent.Type> {

    public static final String ID = "ui.window_controls";

    private final Node eventSource;

    public WindowControlsEvent(final Type eventType, final Node eventSource) {
        super(eventType);
        this.eventSource = eventSource;
    }

    public Node getSource() {
        return eventSource;
    }

    public enum Type {
        MINIMIZE, CLOSE
    }
}
