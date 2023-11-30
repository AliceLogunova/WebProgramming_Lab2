<jsp:useBean id="receivingData" scope="request" type="WebLab2.servlets.ReceivingData"/>
<%@ page import="WebLab2.servlets.ReceivingData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c2" %>


<!DOCTYPE html>

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title></title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<%--  <script src="js/results.js"></script>--%>
</head>
<body>
<header>
  Logunova Alisa Andreevna P3221 var: 2147 (3111)
</header>
<div id="param_tab">
  <table>
    <thead>
    <tr>
      <th>Координата X</th>
      <th>Координата Y</th>
      <th>Величина радиуса</th>
      <th>Попадание в область</th>
      <th>Время работы php-скрипта</th>
      <th>Текущее время</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td><%= ReceivingData.getX() %></td>
      <td><%= ReceivingData.getY() %></td>
      <td><%= ReceivingData.getR() %></td>
      <td><%= ReceivingData.getCollision() %></td>
      <td><%= ReceivingData.getExecutionTime() %></td>
      <td><%= ReceivingData.getTime() %></td>
    </tr>
    </tbody>
  </table>
  <button onClick="window.location.replace('./index.jsp');" type="reset">←</button>
</div>
</body>
</html>