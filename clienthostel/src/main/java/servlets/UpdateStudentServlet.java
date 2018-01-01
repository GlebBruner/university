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

public class UpdateStudentServlet extends HttpServlet {

    private HostelService hostelService;

    @Override
    public void init() throws ServletException {
        HostelServiceImplService service = new HostelServiceImplService();
        hostelService = service.getHostelServiceImplPort();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student studentForUpdate = new Student();
        Subsidy subsidyForPayment = new Subsidy();
        Payment paymentForStudent = new Payment();
        Medical medicalForStudent = new Medical();

        studentForUpdate.setPid(Integer.parseInt(req.getParameter("pid")));
        studentForUpdate.setDob(req.getParameter("dob"));
        studentForUpdate.setStudyForm(req.getParameter("studyForm"));
        studentForUpdate.setSpec(req.getParameter("spec"));
        studentForUpdate.setLevel(Integer.parseInt(req.getParameter("level")));
        String goodGrade = FirstLetterCapitalizer.capitalizeFirstLetter(req.getParameter("grade"));
        studentForUpdate.setGrade(Grade.fromValue(goodGrade));
        studentForUpdate.setName(req.getParameter("name"));
        studentForUpdate.setSurname(req.getParameter("surname"));
        studentForUpdate.setMiddlename(req.getParameter("middlename"));
        studentForUpdate.setPhone(req.getParameter("phone"));
        studentForUpdate.setEmail(req.getParameter("email"));

        paymentForStudent.setBalance(Integer.parseInt(req.getParameter("balance")));
        if (req.getParameter("price") != null)  {
            subsidyForPayment.setPrice(Integer.parseInt(req.getParameter("price")));
            subsidyForPayment.setEstimate(req.getParameter("estimate"));
            paymentForStudent.setSubsidy(subsidyForPayment);
        }
        studentForUpdate.setPayment(paymentForStudent);

        if (req.getParameter("expirationDate") != null) {
            medicalForStudent.setExpirationDate(Converter.fromString(req.getParameter("expirationDate")));
        } else {
            medicalForStudent.setIsExists(Boolean.valueOf(req.getParameter("isExists")));
        }
        studentForUpdate.setMedical(medicalForStudent);

        hostelService.updateStudent(studentForUpdate);
        resp.sendRedirect("all");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("id"));
        Student student = hostelService.readStudent(studentId);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("editstudent.jsp");
        req.setAttribute("student", student);
        requestDispatcher.forward(req, resp);
    }
}
