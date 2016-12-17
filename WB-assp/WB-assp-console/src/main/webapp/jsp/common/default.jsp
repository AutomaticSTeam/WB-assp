<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String rootPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易站-世界上最好用的建站系统</title>
<jsp:include page="/jsp/common/commonCssLink.jsp" />
<style type="text/css">
#mainFrame_center scrollbar{visibility:collapse !important;}
</style>
<script language="javascript"> 
javascript:window.history.forward(1); 
</script>
<script language="javascript"> 
//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
function banBackSpace(e){     
  var ev = e || window.event;//获取event对象     
  var obj = ev.target || ev.srcElement;//获取事件源     
    
  var t = obj.type || obj.getAttribute('type');//获取事件源类型    
    
  //获取作为判断条件的事件类型  
  var vReadOnly = obj.getAttribute('readonly');  
  var vEnabled = obj.getAttribute('enabled');  
  //处理null值情况  
  vReadOnly = (vReadOnly == null) ? false : vReadOnly;  
  vEnabled = (vEnabled == null) ? true : vEnabled;  
    
  //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
  //并且readonly属性为true或enabled属性为false的，则退格键失效  
  var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")   
              && (vReadOnly==true || vEnabled!=true))?true:false;  
   
  //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
  var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")  
              ?true:false;          
    
  //判断  
  if(flag2){  
      return false;  
  }  
  if(flag1){     
      return false;     
  }     
}  

//禁止后退键 作用于Firefox、Opera  
document.onkeypress=banBackSpace;  
//禁止后退键  作用于IE、Chrome  
document.onkeydown=banBackSpace; 
</script>
</head>
<frameset rows="150,*,70" cols="*" frameborder="no" border="0" framespacing="0" >
  <frame src="<%=rootPath %>/jsp/common/head.jsp" name="mainFrame_top" id="mainFrame_top" title="mainFrame_top" noresize="noresize"/>
  <frame src="<%=rootPath %>/jsp/common/welcome.jsp" name="mainFrame_center" id="mainFrame_center" title="mainFrame_center" noresize="noresize" />
  <%-- <frame src="<%=rootPath %>/jsp/common/footer.jsp" name="mainFrame_footer" id="mainFrame_footer" title="mainFrame_footer" noresize="noresize" /> --%>
</frameset>
</html>