package KillWolf.Data;

import java.net.Socket;

public class DataUser {
	public Socket sk;
	public int port;
	public String name;
	public boolean live;
	public String identify;
	public boolean hasConfirmID; //被预言家预言了，仅在预言家的客户端可以使用
	public boolean havePoison = false;
	public boolean haveAntidote = false;
}
