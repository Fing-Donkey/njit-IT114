package com.example.sockets;
import java.io.Serializable;
public class Payload implements Serializable   {
	private static final long serialVersionUID = 8631878017121002054L;
	public PayloadType payloadType;
	public String message;
	public int number;
	public String target;
	public Payload(PayloadType type, String message){
		this(type,message,null);
	}
	public Payload(PayloadType type, String message, String target) {
		this.payloadType = type;
		this.message = message;
		this.target = target;
	}
	@Override
	public String toString() {
		return "Payload[payloadType: " + payloadType.toString() + ", message: " + message + ", number: " + number + ", target: " + target; 
	}
}

