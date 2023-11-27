package servlets;

import data.ReceivingData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("controller")
public class ControllerServlet extends HttpServlet {
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<ReceivingData> sessionList = (List<ReceivingData>) request.getSession().getAttribute("sessionList");
        ReceivingData lastSession = null;
        if (sessionList != null && !sessionList.isEmpty()) {
            // Получение последнего элемента из sessionList
            lastSession = sessionList.get(sessionList.size() - 1);
            // Установка lastSession в атрибут сеанса для использования на странице result.jsp
            request.getSession().setAttribute("lastSession", lastSession);

            try {
                getServletContext().getRequestDispatcher("/checkArea").forward(request, response);
            } catch (Exception e) {
                getServletContext().setAttribute("error", e.getMessage());
                request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
        request.getSession().setAttribute("sessionList", sessionList);
        request.getSession().setAttribute("lastSession", lastSession);

    }
}