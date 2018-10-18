package cn.hx.appium.server;

import java.util.ArrayList;
import java.util.List;

import cn.hx.appium.util.DosCmd;
import cn.hx.appium.util.XmlUtil;

public class Servers {
	
	
	public static void main(String[] args) throws Exception {
		DosCmd dc=new DosCmd();
//		List<String> deviceList=dc.execCmdConsole("adb devices");
//		for(int i=1;i<deviceList.size()-1;i++){
//			String[] deviceInfo=deviceList.get(i).split("\t");
//			//System.out.println(deviceList.get(i));
//			if(deviceInfo[1].equals("device")){
//				System.out.println(deviceInfo[0]);
//			}
//		}
		Servers server=new Servers(new Port(new DosCmd()), new DosCmd());
		//server.startServers();
//		List<String> serverCommands=server.GeneratServerCommand();
//		for(String s:serverCommands){
//			System.out.println(s);
//		}
		if(dc.killServer()){
			server.startServers();
		}
		
	}
	private List<Integer> appiumPortList;
	private List<Integer> bootstrapPortList;
	private List<String> deviceList;
	private Port port;
	private DosCmd dos;
	private String path=System.getProperty("user.dir");
	public Servers(Port port,DosCmd dos){
		this.port=port;
		this.dos=dos;
	}
	/**
	 * 根据设备数量生成可用端口列表
	 * @param start 端口起始号
	 * @return 返回值是一个List<Integer>
	 * @throws Exception
	 */
	//
	private List<Integer> getPortList(int start) throws Exception{
		List<String> deviceList=getDevices();
		List<Integer> portList=port.GeneratPortList(start, deviceList.size());
		return portList;	
	}
	/**
	 * 获取当前可用设备
	 * @return
	 * @throws Exception
	 */
	public  List<String> getDevices() throws Exception {
		List<String> devList = dos.execCmdConsole("adb devices");
		List<String> deviceRes = new ArrayList<String>();
		if (devList.size() > 2) {
			for(int i = 1; i < devList.size() - 1; i++) {
				String deviceInfo[] = devList.get(i).split("\t");
				if (deviceInfo[1].trim().equals("device")) {
					deviceRes.add(deviceInfo[0].trim());
				}
			}
		} else {
			System.out.println("当前没有设备或设备连接状态不正确");
		}
		return deviceRes;
	}
	/**
	 * 生成服务端启动命令字符串存入List
	 * @return
	 * @throws Exception
	 */
	public List<String> GeneratServerCommand() throws Exception{
		appiumPortList=getPortList(4491);
		bootstrapPortList=getPortList(2234);
		deviceList=getDevices();
		List<String> commandList=new ArrayList<String>();
		for(int i=0;i<deviceList.size();i++){
			String command="start appium -p "+appiumPortList.get(i)+" -bp "+bootstrapPortList.get(i)
					+" -U "+deviceList.get(i);
					//start指令是为了把dos窗口调出来，让appium运行更加稳定些。
					//+" > "+path+"\\logs\\"+deviceList.get(i).split(":")[0]+".log";
			commandList.add(command);
		}
		XmlUtil.createDeviceXml(deviceList,appiumPortList);
		return commandList;
	}
	/**
	 * 启动所有appium服务端
	 * @return
	 * @throws Exception
	 */
	public boolean startServers() throws Exception{
		List<String> startCommand=GeneratServerCommand();
		boolean flag=false;
		if(startCommand.size()>0){
			for(String s:startCommand){
				dos.startAppium(s);
				Thread.sleep(10000);
				if (startCommand.size()>1) {
					System.out.println("正在开启服务请等待5秒");
					Thread.sleep(5000);
				}
			}
			flag=true;
		}else{
			flag=false;	
		}
		return flag;
	}

}
