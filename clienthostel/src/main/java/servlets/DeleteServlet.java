package servlets;

import service.HostelService;
import service.HostelServiceImplService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    private HostelService hostelService;

    @Override
    public void init() throws ServletException {
        HostelServiceImplService service = new HostelServiceImplService();
        hostelService = service.getHostelServiceImplPort();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("id"));
        hostelService.deleteStudent(studentId);
        resp.sendRedirect("all");
    }
}
