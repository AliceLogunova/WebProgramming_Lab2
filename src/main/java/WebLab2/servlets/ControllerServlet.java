package WebLab2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller", value = "/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");

        // Check if the request contains point coordinates and radius
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("R");

        if (x != null && y != null && r != null) {
            // If coordinates and radius are provided, forward the request to AreaCheckServlet
            request.getRequestDispatcher("AreaCheckServlet").forward(request, response);
        } else {
            // If not all required parameters are provided, forward the request to the JSP page (index.jsp)
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}

//import jakarta.servlet.annotation.WebServlet;
//
//import jakarta.servlet.ServletException;
//
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "controller", value = "/controller")
//public class ControllerServlet extends HttpServlet {
//    @Override
//    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        List<ReceivingData> sessionList = (List<ReceivingData>) request.getSession().getAttribute("sessionList");
//        ReceivingData lastSession = null;
//        if (sessionList != null && !sessionList.isEmpty()) {
//            lastSession = sessionList.get(sessionList.size() - 1);
//            request.getSession().setAttribute("lastSession", lastSession);
//
//            try {
//                getServletContext().getRequestDispatcher("/checkArea").forward(request, response);
//            } catch (Exception e) {
//                getServletContext().setAttribute("error", e.getMessage());
//                request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
//            }
//        }
//        request.getSession().setAttribute("sessionList", sessionList);
//    }
//}