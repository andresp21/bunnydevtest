<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My dev test! - Andres Pachon</title>
</head>
<body>
<h1>Hello there Bunny Team! Here's my dev test!</h1>

The top trending article on Reddit right now is: <br/>
<h3>"<s:property value="trendiestArticleTitle"/>"</h3>

<s:form action="addspeedy">
Now click the button below to create a new 'speedy' project for this article on VoiceBunny!
	<s:submit value="GO!"/>
	<s:hidden name="trendiestArticleTitle" value="%{trendiestArticleTitle}"/>
</s:form>

</body>
</html>