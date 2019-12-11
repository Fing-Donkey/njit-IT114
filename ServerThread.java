package com.example.sockets;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.example.sockets.PayloadType;

public class ServerThread extends Thread{
	private Socket client;
	private String clientName;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean isRunning = false;
	SampleSocketServer server;
	public ServerThread(Socket myClient, String clientName, SampleSocketServer server) throws IOException {
		this.client = myClient;
		this.clientName = clientName;
		this.server = server;
		isRunning = true;
		out = new ObjectOutputStream(client.getOutputStream());
		in = new ObjectInputStream(client.getInputStream());
		System.out.println("Spawned thread for client " + clientName);
		server.broadcast(new Payload(PayloadType.CONNECT,clientName),clientName);
		send(new Payload(PayloadType.MESSAGE, "Other clients online: " + server.clients.size()));
		send(new Payload(PayloadType.UPDATE_NAME, clientName));
		
	}
	@Override
	public void run() {
		try{
			Payload fromClient;
			while(isRunning 
					&& !client.isClosed() 
					&& (fromClient = (Payload)in.readObject()) != null) {
				processPayload(fromClient);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Terminating client");
		}
		finally {
			System.out.println("Server cleaning up IO for " + clientName);
			stopThread();
			cleanup();
		}
	}
	public String getClientName() {
		return this.clientName;
	}
	void processPayload(Payload payload) {
		System.out.println("Received: " + payload);
		switch(payload.payloadType) {
			case MESSAGE:
				Payload toClient = new Payload(PayloadType.MESSAGE,clientName + ": " + server.isBlacklisted(payload.message));
				System.out.println("Sending: " + toClient.toString());
				server.broadcast(toClient);
			break;
			case DISCONNECT:
				System.out.println("Removing client " + clientName);
				server.removeClient(this);
				stopThread();
				break;
			default:
				break;
		}
	}
	public void stopThread() {
		isRunning = false;
	}
	public boolean isClosed() {
		return client.isClosed();
	}
	public void send(Payload p) {
		try {
			out.writeObject(p);
		} catch (IOException e) {
			System.out.println("Error sending payload to client");
			e.printStackTrace();
			cleanup();
		}
	}
	private void cleanup() {
		if(in != null) {
			try{in.close();}
			catch(Exception e) { System.out.println("Input already closed");}
		}
		if(out != null) {
			try {out.close();}
			catch(Exception e) {System.out.println("Output already closed");}
		}
		if(client != null && !client.isClosed()) {
			try {client.shutdownInput();} 
			catch (IOException e) {System.out.println("Socket/Input already closed");}
			try {client.shutdownOutput();}
			catch (IOException e) {
				System.out.println("Socket/Output already closed");}
			try {client.close();}
			catch (IOException e) {System.out.println("Socket already closed");}
		}
		System.out.println("Client " + clientName + " has been cleaned up");
	}
}
