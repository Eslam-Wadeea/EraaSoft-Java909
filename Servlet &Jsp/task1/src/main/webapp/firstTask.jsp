<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Concatenation</title>
</head>
<body>

<%! 
public String concat(String name  , int id){
	 return id +"-" + name;
	 }
%>
<%
final String name = "Eslam";
final int id = 11;
%>
<h1>The id and name are <%= concat(name , id) %></h1>


</body>
</html>