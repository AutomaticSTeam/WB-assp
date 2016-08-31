package com.assp.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ShellUtils {

	//private String charset = "UTF-8"; // 设置编码格式  
    
	private static Logger logger = Logger.getLogger(ShellUtils.class);
	
	private static ChannelExec channelExec = null;
	
	//连接指定数据库
	public static ChannelExec connect(String host, int port, String userName,
			String password) throws Exception {
			logger.info("尝试连接到....host:" + host + ",用户名:" + userName + ",密码:" + password + ",端口:"+ port);
			JSch jsch = new JSch(); // 创建JSch对象
			 Session session = jsch.getSession(userName, host, port); // 根据用户名，主机ip，端口获取一个Session对象  
	        session.setPassword(password); // 设置密码  
	        Properties config = new Properties();  
	        config.put("StrictHostKeyChecking", "no");  
	        session.setConfig(config); // 为Session对象设置properties  
	        int timeout = 60000000;  
	        session.setTimeout(timeout); // 设置timeout时间  
	        session.connect(); // 通过Session建立链接  
	        logger.info("连接成功");
			channelExec = (ChannelExec) session.openChannel("exec"); 
		return channelExec; 
	}
	
	
	public static void disconnect() {
		if (channelExec != null) {
			try {
				Session s = channelExec.getSession();
				s.disconnect();
				logger.info("关闭FTP");
			} catch (JSchException e) {
				logger.info("e");
				e.printStackTrace();
			}
		}
	}
		
	
	public static int runCmd(String cmd,String charset,ChannelExec channelExec) throws IOException  {
		logger.info("开始执行命令:"+cmd);
		int rst = 0; // 0 成功 1 失败
		channelExec.setCommand(cmd);
		channelExec.setInputStream(null);
		channelExec.setErrStream(System.err);
		BufferedReader reader = null;
		try {
			channelExec.connect();
			InputStream in = channelExec.getInputStream();
			 reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));
			String buf = null;
			while ((buf = reader.readLine()) != null){
				logger.info("执行结果："+buf);
			}
			logger.info("执行成功");
		} catch (JSchException e) {
			rst = 1;
			e.printStackTrace();
			logger.info("执行失败");
		}finally{
			if(reader != null ){
				reader.close();
			}
		}
		return rst;
	}
	
}
