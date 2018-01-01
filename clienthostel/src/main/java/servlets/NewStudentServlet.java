package servlets;

import Util.Converter;
import Util.FirstLetterCapitalizer;
import service.*;
import ua.nure.hostel.Medical;
import ua.nure.hostel.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewStudentServlet extends HttpServlet {

    private HostelService hostelService;

    @Override
    public void init() throws ServletException {
        super.init();
        HostelServiceImplService service = new HostelServiceImplService();
        hostelService = service.getHostelServiceImplPort();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student studentForAdd = new Student();
        studentForAdd.setPid(hostelService.getNextId());
        studentForAdd.setName(req.getParameter("name"));
        studentForAdd.setSurname(req.getParameter("surname"));
        studentForAdd.setMiddlename(req.getParameter("middlename"));
        studentForAdd.setDob(req.getParameter("dob"));
        studentForAdd.setEmail(req.getParameter("email"));
        studentForAdd.setPhone(req.getParameter("phone"));
        studentForAdd.setSpec(req.getParameter("spec"));
        studentForAdd.setLevel(Integer.parseInt(req.getParameter("level")));
        String goodGrade = FirstLetterCapitalizer.capitalizeFirstLetter(req.getParameter("grade"));
        studentForAdd.setGrade(Grade.fromValue(goodGrade));
        studentForAdd.setStudyForm(req.getParameter("studyForm"));

        Payment paymentForStudentForAdd = new Payment();
        paymentForStudentForAdd.setBalance(Integer.parseInt(req.getParameter("balance")));
        if (req.getParameter("price") != null) {
            Subsidy subsidyForPaymentForStudentForAdd = new Subsidy();
            subsidyForPaymentForStudentForAdd.setPrice(Integer.parseInt(req.getParameter("price")));
            subsidyForPaymentForStudentForAdd.setEstimate(req.getParameter("estimate"));
            paymentForStudentForAdd.setSubsidy(subsidyForPaymentForStudentForAdd);
        }

        studentForAdd.setPayment(paymentForStudentForAdd);

        Medical medicalForStudentForAdd = new Medical();

        if (req.getParameter("expirationDate") != null && req.getParameter("isExists") != null) {
            medicalForStudentForAdd.setExpirationDate(Converter.fromString(req.getParameter("expirationDate")));
        } else {

            if (req.getParameter("expirationDate") != null) {
                medicalForStudentForAdd.setExpirationDate(Converter.fromString(req.getParameter("expirationDate")));
            } else {
                medicalForStudentForAdd.setIsExists(Boolean.valueOf(req.getParameter("isExists")));
            }

        }
        studentForAdd.setMedical(medicalForStudentForAdd);

        int floorId = Integer.parseInt(req.getParameter("floor"));
        int roomId = Integer.parseInt(req.getParameter("floor") + req.getParameter("room"));
        hostelService.createStudent(studentForAdd, floorId, roomId);
        resp.sendRedirect("all");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("newstudent.jsp");
        requestDispatcher.forward(req,resp);
    }
}
