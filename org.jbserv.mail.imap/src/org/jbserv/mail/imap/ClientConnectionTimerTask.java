package org.jbserv.mail.imap;

import java.util.TimerTask;

class ClientConnectionTimerTask extends TimerTask {

	private final ClientConnection clientConnection;

	public ClientConnectionTimerTask(final ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}

	@Override
	public void run() {
		clientConnection.shutdown();
	}

}