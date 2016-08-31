package com.assp.common;   

import org.apache.log4j.Logger;

import com.assp.common.utils.ReadConfig;
import com.assp.common.utils.ShellUtils;
import com.jcraft.jsch.ChannelExec;

/**
 * 类简述
 * <p>
 *      同步服务器资源
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年7月26日 下午2:44:25 
 */
public class SyncServerResourcesUtil {

	private static final Logger logger = Logger.getLogger(SyncServerResourcesUtil.class);
	public static final ReadConfig properties = new ReadConfig("/system.properties");
	
	/**
	 * 
	* @Title: syncTmplResorcesToSite 
	* @Description: 将模板资源转化为站点资源
	*@author (shx@sxw100.com)
	* @param @param templateCode
	* @param @param siteCode
	* @param @return
	* @param @throws NumberFormatException
	* @param @throws Exception    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public static  int  syncTmplResorcesToSite(String templateCode , String siteCode) throws NumberFormatException, Exception{
		logger.debug("----------------syncTmplResorcesToSite 开始---------------");
		logger.debug("templateCode----" +templateCode +  "siteCode---------" +siteCode);
		String ip = properties.getConfigByName("ip");
		String port = properties.getConfigByName("port");
		String userName = properties.getConfigByName("username");
		String password = properties.getConfigByName("password");
		String soruceTmplPath = properties.getConfigByName("soruceTmplPath");
		String dirSitePath = properties.getConfigByName("dirSitePath");
		
		ChannelExec  channelExec = ShellUtils.connect(ip, Integer.parseInt(port), userName, password);
		//cp -r /usr/local/nginx/virtual/web/wms/template/10005 /usr/local/nginx/virtual/web/wms/site/10005
		StringBuffer cmd = new StringBuffer();
		cmd.append("cp -r ");
		cmd.append(soruceTmplPath).append(templateCode.trim()).append(" ");
		cmd.append(dirSitePath).append(siteCode.trim());
		return ShellUtils.runCmd(cmd.toString(), "UTF-8", channelExec);
	}
	
	/**
	 * 
	* @Title: getIpAddress 
	* @Description: 获取ip路径
	*@author (shx@sxw100.com)
	* @param @param configName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getIpAddress(String configName){
		return String.valueOf(properties.getConfigByName(configName)) ;
	}
	
}
  