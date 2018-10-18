package cn.hx.appium.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * @author hym
 *
 */
/**
 * 此类完成dos或shell命令的执行封装
 *
 */
public class DosCmd {
	private Log logger=Log.getLogger(DosCmd.class);
	String osName=System.getProperty("os.name");

	/**
	 * execute dos command执行doc命令
	 * @param dos command,String
	 * @return boolean.succeed is true,Failure is false
	 * 
	 */
	public boolean execCmd(String cmdString){
		Runtime p = Runtime.getRuntime();
		try {
			if(osName.toLowerCase().contains("mac")){
				String[] command={"/bin/sh","-c",cmdString};
				Process process=p.exec(command);
			}else if(osName.toLowerCase().contains("win")){
				Process process=p.exec("cmd /k "+cmdString);
			}
			System.out.println("正在执行指令("+cmdString+")，请等待10s");
			Thread.sleep(10000);
			System.out.println( cmdString+"dos命令执行完成");
			logger.debug("execute  command "+cmdString+" Succeed");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			logger.error("execute  command "+cmdString+" Failure", e);
			return false;
		}
	}

	/**
	 * 执行appium的都是启动命令,并把日志写到文件中。
	 * @param dos command,String
	 * @return boolean.succeed is true,Failure is false
	 * 
	 */
	public void startAppium(final String cmdString){
		Thread t = null;
		t = new Thread(new Runnable(){
			@Override
			public void run()
			{
				BufferedWriter outr = null;
				BufferedReader inr = null;
				String uuid = null;
				uuid = cmdString.split("-U ")[1];
				String path = System.getProperty("user.dir") + "\\logs\\" + uuid +".log";
				Runtime p = Runtime.getRuntime();
				Process process = null;
				try {
					System.out.println("-------开始执行指令("+cmdString+")，请等待10s");
					if(osName.toLowerCase().contains("mac")){
						String[] command={"/bin/sh","-c",cmdString};
						process=p.exec(command);
					}else if(osName.toLowerCase().contains("win")){
						process=p.exec("cmd /k "+cmdString);
					}
					File file = new File(path);
					FileUtil.isFileExists(file);
					InputStream in = process.getInputStream();
					inr = new BufferedReader(new InputStreamReader(in));
					outr=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
					String line = null;
					while ((line = inr.readLine()) != null) {
						outr.write(line);
						outr.write(System.getProperty("line.separator"));
						outr.flush();
					}

				} catch (Exception e) {
					e.printStackTrace();
					// TODO Auto-generated catch block
					logger.error("执行失败  command "+cmdString+" Failure", e);
				}finally{
					try {
						inr.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						outr.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.setDaemon(true);   //设置为守护线程主线程结束子线程就结束。
		t.start();
	}

	/**
	 * get result of command, after execute dos command 
	 * @param dos command,String
	 * @return List<String>
	 */
	public List<String> execCmdConsole(String cmdString) throws InterruptedException {
		List<String> dosRes = new ArrayList<String>();
		try {
			Process process=null;
			System.out.println(cmdString);
			if(osName.toLowerCase().contains("mac")){
				String[] command={"/bin/sh","-c",cmdString};
				process = Runtime.getRuntime().exec(command);
			}else if(osName.toLowerCase().contains("win")){
				process = Runtime.getRuntime().exec("cmd /c "+cmdString);
			}
			InputStream in = process.getInputStream();
			BufferedReader inr = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = inr.readLine()) != null) {
				dosRes.add(line);
			}
			process.waitFor();
			process.destroy();
			logger.debug("get result of command after execute dos command "+cmdString+" Succeed ");
		} catch (IOException e) {
			logger.error("get result of command after execute dos command "+cmdString+" Failure", e);
		}
		return dosRes;
	}

	/**
	 * get result of command, after execute dos command 
	 * @param dos command,String
	 * @return List<String>
	 */
	public void execCmdInput(String cmdString , String uuid) throws InterruptedException {
		try {
			Process process=null;
			System.out.println(cmdString);
			if(osName.toLowerCase().contains("mac")){
				String[] command={"/bin/sh","-c","adb shell input text"+cmdString};
				process = Runtime.getRuntime().exec(command);
			}else if(osName.toLowerCase().contains("win")){
				process = Runtime.getRuntime().exec("cmd /c adb -s "+uuid+" shell input text "+cmdString);
			}
			process.waitFor();
			process.destroy();
			logger.debug("get result of command after execute dos command "+cmdString+" Succeed ");
		} catch (IOException e) {
			logger.error("get result of command after execute dos command "+cmdString+" Failure", e);
		}
	}


	/**
	 * kill server by pid of server
	 * @param pid
	 * @return boolean
	 */
	public boolean killServer(){
		System.out.println("开始关闭appium");
		String command="taskkill -F -PID node.exe";
		if(osName.toLowerCase().contains("mac")){
			command="killall node";
		}else if(osName.toLowerCase().contains("win")){
			command="taskkill -F -PID node.exe";
		}else{
			command="taskkill -F -PID node.exe";
		}
		if(execCmd(command)){
			logger.debug("kill server node  Succeed");
			return true;
		}else{
			logger.error("kill server node Failure");
			return false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("os.name"));
		DosCmd dc=new DosCmd();
		dc.killServer();
		dc.startAppium("appium -p 4490 -bp 2233 -U c8ebc45e");
		System.out.println("主线程结束");
	}
}
