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

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "loadServlet", value = "/loadServlet")
public class HistoryLoadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");

        // Retrieve results from the session using ResultBeanComponent
        BeansData resultBeanComponent = new BeansData();
        List<ReceivingData> results = resultBeanComponent.getResults(request.getSession());

        if (!results.isEmpty()) {
            StringBuilder tableHtml = new StringBuilder();
            for (ReceivingData resultData : results) {
                tableHtml.append("<tr>")
                        .append("<td>").append(resultData.getX()).append("</td>")
                        .append("<td>").append(resultData.getY()).append("</td>")
                        .append("<td>").append(resultData.getR()).append("</td>")
                        .append("<td>").append(resultData.getCollision()).append("</td>")
                        .append("<td>").append(resultData.getTime()).append("</td>")
                        .append("<td>").append(resultData.getExecutionTime()).append(" ms").append("</td>")
                        .append("</tr>");
            }

            String htmlResponse = "<table><tbody>" + tableHtml.toString() + "</tbody></table>";
            response.getWriter().write(htmlResponse);
        }
    }
}
//@WebServlet(name = "loadHistory", value = "/loadHistory")
//public class HistoryLoadServlet extends HttpServlet {
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        try {
//            HttpSession session = request.getSession(false);
//            if (session != null) {
//                List<ReceivingData> sessionList = (List<ReceivingData>) session.getAttribute("sessionList");
//                Gson gson = new Gson();
//                String json = gson.toJson(sessionList);
//                response.setContentType("application/json;charset=UTF-8");
//                response.getWriter().write(json);
//            } else {
//                response.setStatus(404);
//            }
//        } catch (Exception e) {
//            getServletContext().setAttribute("error", e.getMessage());
//            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
//        }
//    }
//
//}