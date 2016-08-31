<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@ page import="java.lang.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
 
<%
   	String rootPath = request.getContextPath();
%>
<script type="text/javascript" src="<%=rootPath%>/js/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="<%=rootPath%>/js/jquery.qrcode.min.js"></script> 

<script type="text/javascript">
(function(){
var p = {
		//可以将URL信息配置到title中 这样的话展示效果好点 直接可以看到URL
url:'www.wangkang.com',
showcount:'0',/*是否显示分享总数,显示：'1'，不显示：'0' */
//desc:'',/*默认分享理由(可选)*/
//summary:'',/*分享摘要(可选)*/
title:'我们都是大坏蛋',/*分享标题(可选)*/
site:'中华魂网',/*分享来源 如：腾讯网(可选)*/
//pics:'', /*分享图片的路径(可选)*/
style:'203'
//width:98,
//height:22
};
var s = [];
for(var i in p){
s.push(i + '=' + encodeURIComponent(p[i]||''));
}

document.write(['<a version="1.0" class="shareToSinaWB" href="http://v.t.sina.com.cn/share/share.php?',s.join('&'),'" target="_blank"  title="分享到新浪微博" >新浪微博</a>'].join(''));
document.write(['<a version="1.0" class="qzOpenerDiv" href="http://v.t.qq.com/share/share.php?',s.join('&'),'" target="_blank" title="分享到腾迅微博">腾迅微博</a>'].join(''));
document.write(['<a version="1.0" class="qzOpenerDiv" href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?',s.join('&'),'" target="_blank" title="分享到QQ空间" >QQ空间</a>'].join(''));
document.write(['<a version="1.0" class="qzOpenerDiv" href="http://www.douban.com/recommend/?',s.join('&'),'" target="_blank" title="分享到豆瓣">豆瓣</a>'].join(''));
document.write(['<a title="分享到微信" id="weixin">微信</a>'].join(''));
})();

$("#weixin").on("click",function(){
	weixinShare();
});
var weixinShare=function(){
	//canvas渲染方式
	//$('#code').qrcode("http://www.baidu.com"); //任意字符串 (支持H5)
	//table渲染
	$("#code").qrcode({ 
	    render: "table", //table方式 
	    width: 200, //宽度 
	    height:200, //高度 
	    text: "http://www.baidu.com" //任意内
	});
};
</script>
 <div id="code"></div> 



