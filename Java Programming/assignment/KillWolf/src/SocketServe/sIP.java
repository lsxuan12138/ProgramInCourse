package SocketServe;
/*
*	author: wbx
*/
import java.net.*;
import java.util.Enumeration;

public class sIP {
	public static boolean Log = true;
	public static String getIP() {//获取本机IP，返回空为失败
		String MIP = "";
		try{
		      //InetAddress addr = InetAddress.getLocalHost();
		      //MIP = addr.getHostAddress();
		      //if(Log) System.out.println("本机IP地址："+MIP);
		      //String hostname = addr.getHostName();
		      //if(Log) System.out.println("本机名称："+hostname);
		      //getAllIpAddress();
			MIP = GetRealLocalIP.getRealIP();
		    //  System.out.println();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return MIP;
	}
	/**

	* 获取本地真正的IP地址，即获得有线或者无线WiFi地址。

	* 过滤虚拟机、蓝牙等地址

	* @author yins

	* @date 2018年8月12日 下午9:53:58

	*/

	public class GetRealLocalIP {
	/**

	* 获取本地真正的IP地址，即获得有线或者无线WiFi地址。

	* 过滤虚拟机、蓝牙等地址

	* @author yins

	* @date 2018年8月12日下午9:56:35

	* @return

	*/

	public static String getRealIP() {
	try {
	Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();

	while (allNetInterfaces.hasMoreElements()) {
	NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();

	// 去除回环接口，子接口，未运行和接口

	if (netInterface.isLoopback() || netInterface.isVirtual()|| !netInterface.isUp())continue;
	if (!netInterface.getDisplayName().contains("Intel")&& !netInterface.getDisplayName().contains("Realtek")) continue;
	Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
	System.out.println(netInterface.getDisplayName());
	
	while (addresses.hasMoreElements()) {
	InetAddress ip = addresses.nextElement();
	if (ip != null) {
	// ipv4

	if (ip instanceof Inet4Address) {
	System.out.println("ipv4 = " + ip.getHostAddress());

	return ip.getHostAddress();

	}

	}

	}

	break;

	}

	} catch (SocketException e) {
	System.err.println("Error when getting host ip address"

	+ e.getMessage());

	}

	return null;

	}

	}
	/*
    
     * This method is used to get all ip addresses from the network interfaces.
     * network interfaces: eth0, wlan0, l0, vmnet1, vmnet8 
     
    public static void getAllIpAddress() {
        try {
            //get all network interface
            Enumeration<NetworkInterface> allNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface = null;
            
            //check if there are more than one network interface
            while (allNetworkInterfaces.hasMoreElements()) {
                //get next network interface
                networkInterface = allNetworkInterfaces.nextElement();
                //output interface's name
                //System.out.println("network interface: " + networkInterface.getDisplayName());
                
                                //get all ip address that bound to this network interface
                Enumeration<InetAddress> allInetAddress = networkInterface.getInetAddresses();
                
                InetAddress ipAddress = null;
                
                //check if there are more than one ip addresses
                //band to one network interface
                while (allInetAddress.hasMoreElements()) {
                    //get next ip address
                    ipAddress = allInetAddress.nextElement();
                    if (ipAddress != null && ipAddress instanceof Inet4Address) {
                    	
                    	System.out.println("ip address: " + ipAddress.getHostAddress());
                    }
                }
            }
            
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }//end method getAllIpAddress*/
}


