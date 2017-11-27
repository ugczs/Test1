package Networktuff;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.ServerSocketHints;

public class NetworkComponent {
	
	private static NetworkComponent instance = new NetworkComponent();
	
	private Socket socket;
	
	private boolean connectionEstablished = false;
	
	private ConcurrentLinkedQueue<String> recievedCommands = new ConcurrentLinkedQueue<String>();
	
	private NetworkComponent() {
		
	}
	
	public synchronized boolean getE() {
		synchronized(this) {
			return this.connectionEstablished;
		}
	}
	
	public synchronized void setE(boolean t) {
		synchronized(this) {
			this.connectionEstablished = t;
		}
	}
	
	public static NetworkComponent getInstance() {
		return instance;
	}
	
	public void addInput(String s) {
		
		this.recievedCommands.add(s);
		System.out.println("Commad bei netzwerkC eerhalten size:"+this.recievedCommands.size());
	}
	
	public String getCommand() {
		System.out.println("getCommand aufgerufen");
		String s = this.recievedCommands.poll();
		System.out.println("Neue List Size="+this.recievedCommands.size());
		return s;
	}
	
	public int getCommandSize() {
		return this.recievedCommands.size();
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	
	public void sendString(String s) {
		s = s+"\n";
		try {
			this.socket.getOutputStream().write(s.getBytes());
		} catch (Exception e) {
			System.out.println("Es gab einen Fehler bei der sendString methode");
			System.err.println(e.getMessage());
			
		}
	}
	
	public void startServer(int port) {
		ServerSocketHints serverSocketHint = new ServerSocketHints();
		serverSocketHint.acceptTimeout = 0;
		ServerSocket serverSocket = Gdx.net.newServerSocket(Protocol.TCP, port, serverSocketHint);
		ServerSocketThread t = new ServerSocketThread(serverSocket);
		new Thread(t).start();
	}
	
	public void startClient(String ipAdress, int port) {
		this.socket = Gdx.net.newClientSocket(Protocol.TCP, ipAdress, port, null);
		ClientSocketThread t = new ClientSocketThread();
		new Thread(t).start();
		this.connectionEstablished = true;
	}
	
	
}
