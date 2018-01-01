package servlets;

import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class AllStudentsServlet extends HttpServlet {

    private HostelService hostelService;

    @Override
    public void init() throws ServletException {
        super.init();
        HostelServiceImplService service = new HostelServiceImplService();
        hostelService = service.getHostelServiceImplPort();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Floor> floors = hostelService.getHostel().getFloor();
        req.setAttribute("floorList", floors);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("allstudents.jsp");
        requestDispatcher.forward(req,resp);
    }
}
