<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet, java.sql.SQLException" %>



<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/StudentManager/Styles/IndexStyles.css">
    <title>Home!</title>
</head>
<body>
	<div class="main-cont app-font"  >
        <div  >
            <h2>
            	Student's data
            </h2>
            <table  class="data-table">
            	<thead>
            		<tr>
            			<th>Name</th>
            			<th>Email</th>
            		</tr>
            	</thead>
            	<tbody>
            	
            		<%
            			ResultSet stData =  (ResultSet) request.getAttribute("studentData");
            			while(stData.next()){
            				String name = stData.getString("name");
            				String email = stData.getString("email");
            				
            				out.print("<tr>  <td>" + name + "</td>  <td>" + email + "</td>  </tr>");
            			}
            			stData.close();
            		%>
            		
            	</tbody>
            </table>
        </div>
    </div>
</body>
</html>