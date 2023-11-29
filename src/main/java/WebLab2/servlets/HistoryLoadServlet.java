package WebLab2.servlets;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//@WebServlet("loadHistory")
@WebServlet(name = "loadHistory", value = "/loadHistory")
public class HistoryLoadServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                List<ReceivingData> sessionList = (List<ReceivingData>) session.getAttribute("sessionList");
                Gson gson = new Gson();
                String json = gson.toJson(sessionList);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(json);
            } else {
                response.setStatus(404);
            }
        } catch (Exception e) {
            getServletContext().setAttribute("error", e.getMessage());
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}