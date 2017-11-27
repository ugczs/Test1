package Networktuff;

import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;

public class ServerSocketThread implements Runnable {
	
	private ServerSocket serverSocket;
	
	public ServerSocketThread(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void run() {
		System.out.println("Warte auf Verbindung");
		Socket socket = this.serverSocket.accept(null);
		System.out.println("Jemand hat sich mit dem Server verbunden");
		NetworkComponent n = NetworkComponent.getInstance();
		n.setSocket(socket);
		
		ClientSocketThread t = new ClientSocketThread();
		new Thread(t).start();
		
		System.out.println("Auf true will ich setzen");
		//n.connectionEstablished = true;
		n.setE(true);
		System.out.println("Auf True gesetzt");
		
		
	}

}
