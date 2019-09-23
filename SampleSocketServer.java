package com.example.sockets;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
public class SampleSocketServer {
	public void start(int port) {
		System.out.println("Waiting for server");
		try(ServerSocket serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {	
		System.out.println("Client connected, waiting for message");
		String fromClient = "", toClient = "";
		while((fromClient = in.readLine()) != null) {
			System.out.println("message from client: " + fromClient);
			if("kill server".equalsIgnoreCase(fromClient)) {
				System.out.println("Client killed server");
				break;
			}
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println("Starting server");
		SampleSocketServer server = new SampleSocketServer();
		server.start(3001);
		System.out.println("server stopped");
	}
}
