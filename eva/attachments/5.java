package Networktuff;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientSocketThread implements Runnable {
	
	public void run() {
		try {
			while(true) {
				BufferedReader buffer = new BufferedReader(new InputStreamReader(NetworkComponent.getInstance().getSocket().getInputStream()));
				String recieved = buffer.readLine();
				System.out.println("Nachricht war:"+recieved);
				if(recieved != null) {
				System.out.println("Nicht null");
					//System.out.println("Nachricht empfangen");
					NetworkComponent.getInstance().addInput(recieved);
				}
			}
		} catch (Exception e) {
			System.out.println("Es gab Probleme beim lesen aus dem Stream");
		}
	}
	
	public ClientSocketThread() {
		
	}

}
