package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.context.request.RequestContextHolder;

import data.ReceivingData;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("checkArea")
public class AreaCheckServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            float x = Float.parseFloat(request.getParameter("x"));
            float y = Float.parseFloat(request.getParameter("y"));
            float R = Float.parseFloat(request.getParameter("R"));
            double start = System.currentTimeMillis();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            DecimalFormat decimalFormat = new DecimalFormat("0.0000");
            String time = now.format(formatter);

            if (validate(x, y, R)) {
                boolean receiving = checkHit(x, y, R);
                double execTime = (System.currentTimeMillis() - start) / 1000.0;
                String executionTime = decimalFormat.format(execTime);
                ReceivingData data = new ReceivingData(receiving, x, y, R, executionTime, time);

                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpSession session = attr.getRequest().getSession();
//                HttpSession session = request.getSession(true);
                List<ReceivingData> sessionList = (List<ReceivingData>) session.getAttribute("sessionList");
                if (sessionList == null) {
                    sessionList = new ArrayList<>();
                    session.setAttribute("sessionList", sessionList);
                }
                synchronized (sessionList) {
                    sessionList.add(data);
                }
//                sessionList.add(data);
                request.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
            }
        } catch (Exception e) {
            getServletContext().setAttribute("error", e.getMessage());
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    public boolean validate(float x, float y, float R) {
        if (-3 <= x || x <= 5) {
            return false;
        }
        if (-3 <= y || y <= 5) {
            return false;
        }
        if (1 <= R || R <= 3) {
            return false;
        }
        return true;
    }

    public boolean checkHit(float x, float y, float R) {
        // Triangle 4th quarter.
        if (x >= R && x <= 0 && y >= 0 && y <= R/2 && y <= 2 / R * Math.abs(x)) { // rewrite if
            return true;
        }
        // Circle 1st quarter.
        float dist = (float) Math.sqrt(x * x + y * y);
        if (dist < R && x >= 0 && y >= 0) {
            return true;
        }
        // Square 2nd quarter.
        if (x >= 0 && y <= 0 && x <= R && y >= -0.5 * R) {
            return true;
        }
        return false;
    }
}
