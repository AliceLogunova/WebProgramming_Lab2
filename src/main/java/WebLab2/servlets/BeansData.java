package WebLab2.servlets;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;

public class BeansData {

    public void addResult(HttpSession session, ReceivingData resultData) {
        List<ReceivingData> results = getResults(session);
        results.add(resultData);
        session.setAttribute("results", results);
    }

    public List<ReceivingData> getResults(HttpSession session) {
        List<ReceivingData> results = (List<ReceivingData>) session.getAttribute("results");
        if (results == null) {
            results = new ArrayList<>();
        }
        return results;
    }

    public void clearResults(HttpSession session) {
        session.removeAttribute("results");
    }
}
