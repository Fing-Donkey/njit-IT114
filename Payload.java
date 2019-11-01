package com.example.sockets;
import java.io.Serializable;
public class Payload implements Serializable   {
	private static final long serialVersionUID = -7358730485627664992L;
	public int id;
	public int target = -1;
	public PayloadType payloadType;
	public int x;
	public int y;
	public String extra = null;
	public enum PayloadType{
		CONNECT,
		DISCONNECT,
		DIRECTION,
		MOVE_SYNC,
		SPEED_BOOST,
		TRIGGER_TAG,
		SET_IT,
		SYNC,
		STATS,
		ACK
	}
	public Payload(int id, PayloadType type) {
		this(id, type, 0,0, null);
	}
	public Payload(int id, PayloadType type, int x, int y) {
		this(id, type, x, y, null);
	}
	public Payload(int id, PayloadType type, int x, int y, String extra) {
		this(id, type, x, y, extra, -1);
	}
	public Payload(int id, PayloadType type, int x, int y, String extra, int target) {
		this.id = id;
		payloadType = type;
		this.x = x;
		this.y = y;
		this.extra = extra;
		this.target = target;
	}
	@Override
	public String toString() {
		return this.id + "-" + this.payloadType.toString() + "(" + x + "," + y +") - " + extra;
	}
	
}

