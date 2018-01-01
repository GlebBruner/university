package model;

public class Util {

    public static boolean isName = false;
    public static boolean isSurname = false;
    public static boolean isMiddlename = false;
    public static boolean isPhone = false;
    public static boolean isEmail = false;
    public static boolean isDob = false;
    public static boolean isSpec = false;
    public static boolean isLevel = false;
    public static boolean isGrade = false;

    public static boolean isPayment = false;
    public static boolean isBalance = false;
    public static boolean isSubsudy = false;
    public static boolean isPrice = false;
    public static boolean isEstimate = false;

    public static boolean isMedical = false;
    public static boolean isExists = false;
    public static boolean isExpirationDate = false;


    public final static String HOSTEL = "hostel";
    public final static String FLOOR = "floor";
    public final static String ROOM = "room";
    public final static String STUDENT = "student";
    public final static String NAME = "name";
    public final static String SURNAME = "surname";
    public final static String MIDDLENAME = "middlename";
    public final static String PHONE = "phone";
    public final static String EMAIL = "email";
    public final static String DOB = "dob";
    public final static String SPEC = "spec";
    public final static String LEVEL = "level";
    public final static String GRADE = "grade";
    public final static String PAYMENT = "payment";
    public final static String BALANCE = "balance";
    public final static String SUBSIDY = "subsidy";
    public final static String ESTIMATE = "estimate";
    public final static String PRICE = "price";
    public final static String MEDICAL = "medical";
    public final static String ISEXISTS = "isExists";
    public final static String EXPIRATIONDATE = "expirationDate";

    public static Hostel prepareData() {
        Subsidy subsidy = new Subsidy();
        subsidy.setEstimate("2018-09-25");
        subsidy.setPrice(400);

        Payment payment = new Payment();
        payment.setBalance(500);
        payment.setSubsidy(subsidy);

        Medical medical = new Medical();
        medical.setMedicalInfo(Boolean.TRUE);

        Student student = new Student();
        student.setPid(1);
        student.setStudyform("daily");
        student.setName("Gleb");
        student.setSurname("Bruner");
        student.setMiddlename("Vital");
        student.setPhone("0686219808");
        student.setEmail("hlib.bruner@nure.ua");
        student.setDob("1996-10-17");
        student.setSpec("KN");
        student.setGrade(Grade.PHD);
        student.setLevel(1);
        student.setPayment(payment);
        student.setMedical(medical);

        Subsidy subsidy1 = null;

        Payment payment1 = new Payment();
        payment1.setBalance(-100);
        payment1.setSubsidy(subsidy1);

        Medical medical1 = new Medical();
        medical1.setMedicalInfo("2018-10-25");

        Student student1 = new Student();
        student1.setPid(2);
        student1.setStudyform("daily");
        student1.setName("Yaroslav");
        student1.setSurname("Grigorov");
        student1.setMiddlename("Andreevich");
        student1.setPhone("0679043208");
        student1.setEmail("yaroslav.grigorov@nure.ua");
        student1.setDob("1996-10-17");
        student1.setSpec("KN");
        student1.setGrade(Grade.PHD);
        student1.setLevel(1);
        student1.setPayment(payment1);
        student1.setMedical(medical1);

        Room room = new Room();
        room.setId(21);
        room.setState(State.GOOD);
        room.getStudent().add(student);

        Room room1 = new Room();
        room1.setId(31);
        room1.setState(State.BAD);
        room1.getStudent().add(student1);

        Floor floor = new Floor();
        floor.setId(2);
        floor.getRoom().add(room);

        Floor floor1 = new Floor();
        floor1.setId(3);
        floor1.getRoom().add(room1);


        Hostel hostel = new Hostel();
        hostel.getFloor().add(floor);
        hostel.getFloor().add(floor1);

        return hostel;
    }

}
