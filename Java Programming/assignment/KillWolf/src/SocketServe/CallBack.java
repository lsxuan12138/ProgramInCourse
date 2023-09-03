package SocketServe;
/*
*	author: wbx
*/
import java.net.Socket;

public interface CallBack {
	public void run(String text,Socket sk);
}
