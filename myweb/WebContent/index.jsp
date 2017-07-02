<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的个人主页</title>
</head>
<body>
   <h1>欢迎大家学习JAVAEE开发</h1>
   <!-- 我是html、注释  -->
   <%-- 我是jsp注释 --%>
   
   <%
     //
     /**
            脚本
     */
     
     out.println("hello world");
   %>
   
   <%!
      String s = "张三";
      int add(int x, int y){
    	  return x+y;
      }
   
   %>
   
   <br>
    你好，<%=s %><br>
   x+y=<%=add(2,5) %><br>   
</body>
</html>
