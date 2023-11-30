<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="">
    <title>ЧупапиМуняня</title>
    <link rel="stylesheet" href="style/style.css" />
    <link rel="icon" href="pics/icon.ico" type="image/x-icon" />
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>--%>
    <script src="js/commonFunctions.js"></script>
    <script src="js/validate.js"></script>
    <script src="js/canvasControl.js"></script>
    <script src="js/commonFunctions.js"></script>
    <script src="js/showMessage.js"></script>
    <script src="js/script.js"></script>
    <style>
        header {
            font-family: serif;
            font-size: 12px;
            color: #00ff00;
        }
    </style>
</head>
<body>
<header>
    <h1>Логунова Алиса Андреевна P3221 Вариант 2147 (3111)</h1>
</header>

<div id="error_div"
     style="display: none; position: absolute; top: 10px; right: 10px; background-color: #f44336; color: white; padding: 10px; border-radius: 5px;">
</div>

<table class="main_tab">
    <tr>
<%--            <svg class = "pic" width="400" height="400">--%>
<%--                &lt;%&ndash; PALOCHKIN &ndash;%&gt;--%>
<%--                <line x1="0" y1="200" x2="400" y2="200" stroke="white" stroke-width="2"/>--%>
<%--                <line x1="200" y1="0" x2="200" y2="400" stroke="white" stroke-width="2"/>--%>
<%--                &lt;%&ndash; STRELOCHKI &ndash;%&gt;--%>
<%--                <polygon points="400,200 390,195 390,205" fill="white"/>--%>
<%--                <polygon points="200,0 195,10 205,10" fill="white"/>--%>

<%--                &lt;%&ndash; OTMETOCHKI X &ndash;%&gt;--%>
<%--                <line x1="50" y1="195" x2="50" y2="205" stroke="white" stroke-width="2"/>--%>
<%--                <line x1="125" y1="195" x2="125" y2="205" stroke="white" stroke-width="2"/>--%>
<%--                <line x1="275" y1="195" x2="275" y2="205" stroke="white" stroke-width="2"/>--%>
<%--                <line x1="350" y1="195" x2="350" y2="205" stroke="white" stroke-width="2"/>--%>
<%--                <line x1="450" y1="195" x2="450" y2="205" stroke="white" stroke-width="2"/>--%>

<%--                <text x="45" y="220" font-size="13" stroke="white">-R</text>--%>
<%--                <text x="110" y="220" font-size="13" stroke="white">-R/2</text>--%>
<%--                <text x="265" y="220" font-size="13" stroke="white">R/2</text>--%>
<%--                <text x="345" y="220" font-size="13" stroke="white">R</text>--%>

<%--                &lt;%&ndash; OTMETOCHKI Y &ndash;%&gt;--%>
<%--                <line x1="195" y1="50" x2="205" y2="50" stroke="white" stroke-width="2"/>--%>
<%--                <line x1="195" y1="125" x2="205" y2="125" stroke="white" stroke-width="2"/>--%>
<%--                <line x1="195" y1="275" x2="205" y2="275" stroke="white" stroke-width="2"/>--%>
<%--                <line x1="195" y1="350" x2="205" y2="350" stroke="white" stroke-width="2"/>--%>
<%--                <line x1="195" y1="450" x2="205" y2="450" stroke="white" stroke-width="2"/>--%>

<%--                <text x="180" y="55" font-size="13" stroke="white">R</text>--%>
<%--                <text x="173" y="129" font-size="13" stroke="white">R/2</text>--%>
<%--                <text x="169" y="279" font-size="13" stroke="white">-R/2</text>--%>
<%--                <text x="180" y="355" font-size="13" stroke="white">-R</text>--%>

<%--                &lt;%&ndash; a quarter of circle &ndash;%&gt;--%>
<%--                <path d="M 200 50 A 150 150 0 0 1 350 200 L 200 200 Z" fill="rgba(134, 252, 91, 0.5)" stroke="white"--%>
<%--                      stroke-width="1"/>--%>

<%--                &lt;%&ndash; triangle &ndash;%&gt;--%>
<%--                <polygon points="50,200 200,200 200,125" fill="rgba(252, 110, 91, 0.5)" stroke="white"--%>
<%--                         stroke-width="1"></polygon>--%>
<%--                &lt;%&ndash; pryamougol'nik &ndash;%&gt;--%>
<%--                <polygon points="200,200 350,200 350,275 200,275" fill="rgba(91, 193, 252, 0.5)" stroke="white"--%>
<%--                         stroke-width="1"></polygon>--%>
<%--            </svg>--%>
        <div>
            <td>
                <form id="form" method="post" onsubmit="return validate();">
                    <canvas id="coordinateCanvas" width="500" height="500" style="border: 1px solid white;"></canvas>
                    <p id="coo_x" name="coo_x" for="coo_x">
                        Введите значение координаты X
                        <input type="text" name="coo_x" maxlength="17" required placeholder="Координата X"/>
                    </p>
                    <p id="coo_y" name="coo_y" for="coo_y">
                        Введите значение координаты Y
                        <input type="text" name="coo_y" maxlength="17" required placeholder="Координата Y"/>
                    </p>
                    <p>
                        Выберите значение радиуса (единичного отрезка) на графике
                        <select name="radius" id="radius">
                            <option value="1">1</option>
                            <option value="1.5">1.5</option>
                            <option value="2">2</option>
                            <option value="2.5">2.5</option>
                            <option value="3">3</option>
                        </select>
                    </p>
                    <p>
                        <button type="submit" name="submit" onclick="fetchResults();"><b>Post</b></button>
                        <button id="clear_but" name="clear" onclick="clearTable();"><b>Clear</b></button>
                    </p>
                </form>
            </td>
        </div>

        <td class="table2">
            <table id="param_tab">
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
<%--                    <c:forEach var="result" items="${sessionList}">--%>
<%--                        <tr>--%>
<%--                            <td>${result.x}</td>--%>
<%--                            <td>${result.y}</td>--%>
<%--                            <td>${result.R}</td>--%>
<%--                            <td>${result.collision}</td>--%>
<%--                            <td>${result.executionTime}</td>--%>
<%--                            <td>${result.time}</td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
                </tbody>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
