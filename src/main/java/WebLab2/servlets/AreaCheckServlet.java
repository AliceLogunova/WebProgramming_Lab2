package WebLab2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet(name = "checkArea", value = "/checkArea")
public class AreaCheckServlet extends HttpServlet {
    private BeansData resultBeanComponent = new BeansData();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");

        String xStr = request.getParameter("x");
        String yStr = request.getParameter("y");
        String rStr = request.getParameter("R");
        String userLocalDateTime = request.getParameter("time");

        long startTime = System.nanoTime();

        String result = processPoint(xStr, yStr, rStr);

        float fX = new BigDecimal(xStr).setScale(3, RoundingMode.FLOOR).floatValue();
        float fY = new BigDecimal(yStr).setScale(3, RoundingMode.FLOOR).floatValue();
        float fR = new BigDecimal(rStr).setScale(3, RoundingMode.FLOOR).floatValue();

        long endTime = System.nanoTime();
        double executionTimeMs = ((endTime - startTime) / 1e6);
        double fExecutionTime = new BigDecimal(executionTimeMs).setScale(3, RoundingMode.FLOOR).doubleValue();

        ReceivingData resultData = new ReceivingData(fX, fY, fR, result, userLocalDateTime, fExecutionTime);

        // Store the result on the server using the ResultBeanComponent
        resultBeanComponent.addResult(request.getSession(), resultData);

        request.setAttribute("resultData", resultData);

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

    private String processPoint(String xStr, String yStr, String rStr) {
        try {
            // Parse input values to doubles
            Double x = parseDoubleOrNull(xStr);
            Double y = parseDoubleOrNull(yStr);
            Double r = parseDoubleOrNull(rStr);

            if (x != null && y != null && r != null) {
                if (FirstQuarter(x, y, r)) {
                    return "Точка входит в область определения";
                } else if (SecondQuarter(x, y, r)) {
                    return "Точка не входит в область определения";
                } else if (ThirdQuarter(x, y)) {
                    return "Точка входит в область определения";
                } else if (FourthQuarter(x, y, r)) {
                    return "Точка входит в область определения";
                } else {
                    return "Точка не входит в область определения";
                }
            } else {
                throw new IllegalArgumentException("Неверный ввод. Пожалуйста, убедитесь, что вы вводите корректные значения.");
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private Double parseDoubleOrNull(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean FirstQuarter(double x, double y, double R) {
        float dist = (float) Math.sqrt(x * x + y * y);
        return dist < R && x >= 0 && y >= 0;
        }

    private boolean SecondQuarter(double x, double y, double R) {
        return x >= 0 && y <= 0 && x <= R && y >= -0.5 * R;
    }

    private boolean ThirdQuarter(double x, double y) {
        return x >= 0 && y >= 0;
    }

    private boolean FourthQuarter(double x, double y, double R) {
        return x >= R && x <= 0 && y >= 0 && y <= R/2 && y <= 2 / R * Math.abs(x);
    }

//import jakarta.servlet.annotation.WebServlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet(name = "checkArea", value = "/checkArea")
//public class AreaCheckServlet extends HttpServlet {
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        try {
//            float x = Float.parseFloat(request.getParameter("x"));
//            float y = Float.parseFloat(request.getParameter("y"));
//            float R = Float.parseFloat(request.getParameter("R"));
//            double start = System.currentTimeMillis();
//            LocalDateTime now = LocalDateTime.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
//            DecimalFormat decimalFormat = new DecimalFormat("0.0000");
//            String time = now.format(formatter);
//
//            if (validate(x, y, R)) {
//                boolean receiving = checkHit(x, y, R);
//                double execTime = (System.currentTimeMillis() - start) / 1000.0;
//                String executionTime = decimalFormat.format(execTime);
//                ReceivingData data = new ReceivingData(receiving, x, y, R, executionTime, time);
//
//                HttpSession session = request.getSession(true);
//                List<ReceivingData> sessionList = (List<ReceivingData>) session.getAttribute("sessionList");
//                if (sessionList == null) {
//                    sessionList = new ArrayList<>();
//                    session.setAttribute("sessionList", sessionList);
//                }
//                sessionList.add(data);
//                request.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
//            }
//        } catch (Exception e) {
//            getServletContext().setAttribute("error", e.getMessage());
//            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
//        }
//    }
//
//    public boolean validate(float x, float y, float R) {
//        if (-3 <= x || x <= 5) {
//            return false;
//        }
//        if (-3 <= y || y <= 5) {
//            return false;
//        }
//        if (1 <= R || R <= 3) {
//            return false;
//        }
//        return true;
//    }
//
//    public boolean checkHit(float x, float y, float R) {
//        // Triangle 4th quarter.
//        if (x >= R && x <= 0 && y >= 0 && y <= R/2 && y <= 2 / R * Math.abs(x)) { // rewrite if
//            return true;
//        }
//        // Circle 1st quarter.
//        float dist = (float) Math.sqrt(x * x + y * y);
//        if (dist < R && x >= 0 && y >= 0) {
//            return true;
//        }
//        // Square 2nd quarter.
//        if (x >= 0 && y <= 0 && x <= R && y >= -0.5 * R) {
//            return true;
//        }
//        return false;
//    }
}
