package WebLab2.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClearResultsServlet is responsible for clearing the results table and session-stored ResultData objects.
 */
@WebServlet(name = "clearHistory", value = "/clearHistory")
public class HistoryClearServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        // Clear the table by removing the session attribute using ResultBeanComponent
        BeansData resultBeanComponent = new BeansData();
        resultBeanComponent.clearResults(request.getSession());

        // Send a response indicating the table is cleared
        response.getWriter().write("<p>Таблица очищена.</p>");
    }
}

//import jakarta.servlet.annotation.WebServlet;
//
//import jakarta.servlet.ServletException;
//
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet(name = "clearHistory", value = "/clearHistory")
//public class HistoryClearServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            HttpSession session = request.getSession(false);
//            if (session != null) {
//                session.removeAttribute("sessionList");
//                response.setStatus(200);
//            } else {
//                response.setStatus(404);
//            }
//        } catch (Exception e) {
//            getServletContext().setAttribute("error", e.getMessage());
//            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
//        }
//    }
//}