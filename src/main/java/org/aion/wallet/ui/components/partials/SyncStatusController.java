package org.aion.wallet.ui.components.partials;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.aion.wallet.connector.BlockchainConnector;
import org.aion.wallet.connector.dto.SyncInfoDTO;
import org.aion.wallet.ui.components.AbstractController;
import org.aion.wallet.ui.events.RefreshEvent;
import org.aion.wallet.util.SyncStatusFormatter;

import java.net.URL;
import java.util.ResourceBundle;

public class SyncStatusController extends AbstractController {

    private final BlockchainConnector blockchainConnector = BlockchainConnector.getInstance();

    @FXML
    private Label progressBarLabel;

    @Override
    protected void internalInit(URL location, ResourceBundle resources) {
    }

    @Override
    protected final void refreshView(final RefreshEvent event) {
        if (RefreshEvent.Type.TIMER.equals(event.getType())) {
            final Task<SyncInfoDTO> getSyncInfoTask = getApiTask(o -> blockchainConnector.getSyncInfo(), null);
            runApiTask(
                    getSyncInfoTask,
                    evt -> setSyncStatus(getSyncInfoTask.getValue()),
                    getErrorEvent(throwable -> {}, getSyncInfoTask),
                    getEmptyEvent()
            );
        }
    }

    private void setSyncStatus(SyncInfoDTO syncInfo) {
        progressBarLabel.setText(SyncStatusFormatter.formatSyncStatusByBlockNumbers(syncInfo));
    }
}
