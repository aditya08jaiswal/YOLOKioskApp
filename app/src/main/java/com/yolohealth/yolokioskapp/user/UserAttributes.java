package com.yolohealth.yolokioskapp.user;

/***
 *
 * @author arpit87
 * use this class to define any json attributes
 * that we get in response from server
 *used to create json to be sent to server
 */

public class UserAttributes {
    //Authentication
    public static final String APPSECRET = "appsecret";

    //make boking
    public static final String FLAT = "flat";
    public static final String LOCALITY = "locality";
    public static final String LANDMARK = "landmark";

    //new booking notification
    public static final String NEWBOOKINGID = "newbookingid";

    //get booking
    public static final String DATETIME = "datetime";
    public static final String BOOKINGFOR = "bookingfor";
    public static final String VALUE = "value";
    //public static final String TIME ="time";
    public static final String MOVERID ="moverid";
    public static final String USERID ="userid";
    public static final String BOOKINGID = "bookingid";
    //public static final String AMPM = "ampm";
    //public static final String TIMEDURATION = "timeduration";
    public static final String USERNAME = "username";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    //public static final String TIMERAMOUNT = "timeramount";
    public static final String MOBILE = "mobile";

    //mover attri
    public static final String MOVERPHONE = "moverphone";
    public static final String MOVERNAME ="movername";
    public static final String PROFILEPIC ="profilepic";
    public static final String MOVERSKILLSTR ="skillstr";
    public static final String MOVERLIST ="moverlist";
    public static final String GENDER="gender";
    public static final String DOCTORPROFILEPIC ="profilepic";

    // Health Enrollment
    public static final String ENROLL_ID = "enrollid";
    public static final String ACTIVATIONCODE = "activationcode";
    public static final String ENROLLDATE ="enrolldate";
    public static final String ENROLLEDLIST = "enrolledlist";

    //create mover
    public static final String USERPHONE = "phone";
    public static final String USEREMAIL = "email";
    public static final String CHATUSER = "chatuser";
    public static final String CHATPASS = "chatpass";

    //check mover status
    public static final String ISAVAILABLE = "isavailable";
    public static final String ISBOOKED = "isbooked";
    public static final String BOOKINGLIST = "bookinglist";
    public static final String BOOKINGSTATUSMOVER = "bookingstatusmover";

    //  booking status
    public static final String BOOKINGSTATUS = "bookingstatus"; //pending:0accepted:1,rejected:2,missed:3,cancelled:4,served:5

    //gcm
    public static final String GCMREGID = "gcmregid";
    public static final String GCMINFOTYPE = "gcminfotype";
    public static final String GCMBOOKINGJSONSTR = "gcmbookingstr";
    public static final String UNIQUEID = "uniqueid";

    //mover list
    public static final String DOCLIST = "doclist";
    public static final String NURSELIST = "nurselist";
    public static final String PHYSIOLIST = "physiolist";
    public static final String CARETAKERLIST = "caretakerlist";
    public static final String AMBULANCELIST = "ambulancelist";

    //service
    public static final String SERVICETITLE = "servicetitle";
    public static final String SERVICETAG = "servicetag";
    public static final String SERVICECHARGES = "servicecharges";
    public static final String RECEIPTID = "receiptid";

    //OTP
    public static final String OTP = "otp";
    public static final String PHONE = "phone";

    //rating
    public static final String RATING = "rating";
    public static final String STATUS = "status";
    public static final String CONSULTID = "consultid";
    public static final String COMMENTORROLE = "commenterrole";
    public static final String COMMENTER = "commenter";
    public static final String COMMENTTEXT = "commenttext";
    public static final String COMMENTID = "commentid";
    public static final String COMMENTDATE = "date";
    public static final String COMMENTERID = "commenterid";
    public static final String SEARCHPARAMETER = "searchParameter";
    public static final String SEARCHPARAMETERVALUE = "searchParameterValue";
    public static final String NOTIFICATION_ID = "notificationid";
    public static final String NOTIFICATION_TEXT = "notificationtext";
    public static final String CATEGORYCOUNT = "numofdocs";
    public static final String ISPAYMENTDONE = "paymentdone";
    public static final String KIOSK_STR = "kioskstr";
    public static final String DOCTOR_DETAILS = "doctordetails";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String USER_ROLE = "role";
    public static final String TRANSACTIONID = "transactionid";
    public static final String TRANSACTIONSTATUS = "transactionstatus";
    public static final String TRANSACTIONAMOUNT = "transactionamount";
    public static final String TRANSACTIONTYPE = "transactiontype";
    public static final String REPORTURL = "reporturl";
    public static final String ITEM_TYPE = "iteamtype";
    public static final String VIDEO_URL = "videourl";
    public static final String DATE = "date";
    public static final String AVAILABILITY_ID = "availabilityid";
    public static final String END_TIME = "endtime";
    public static final String START_TIME = "starttime";
    public static final String SLOT_BOOKED = "slotbooked";
    public static final String SLOT_LENGTH = "slotlength";
    public static final String ISOPPONENTONLINE = "opponentisonline";
    public static final String CITY = "city";
    public static final String MONTH = "month";
    public static final String YEAR = "year";
    public static final CharSequence ERROR_404 = "404";
    public static final String UNDERWEIGHT = "underweight";
    public static final String NORMAL = "normal";
    public static final String OVERWEIGHT = "overweight";
    public static final String LOW = "low";
    public static final String PREDIABETIC="prediabetic";
    public static final String PERFECT = "perfect";
    public static final String SLIGHT = "slight_impairment";
    public static final String MODERATE = "moderate_impairment";
    public static final String HIGHIMPAIRMENT = "high_impairment";
    public static final String HIGH = "high";
    public static final String VERYHIGH = "veryhigh";
    public static final String OPTIMAL = "optimal";
    public static final String NEAROPTIMAL = "near_optimal";
    public static final String FARVISION = "farvision";
    public static final String NEARVISIONJSCORE="nearvisionJscore";
    public static final String NEARVISION="nearvision";
    public static final String PREHYPER = "prehyper";
    public static final int BASIC_HEALTH_CHECKUP = 0;
    public static final int ADVANCE_HEALTH_CHECKUP = 1;
    public static final int DIABETES = 2;
    public static final int HEART_CHECKUP = 3;
    public static final int BLOOD_CHECKUP = 7;
    public static final int URINE_CHECKUP = 9;
    public static final int EYE_CHECKUP = 10;
    public static final String HASH_STRING = "hash_string";
    public static final String QBCALLERID = "qbcallerid";
    public static final String AUTHKEY = "authkey";
    public static final String AUTH_SECRET ="authsecret" ;
    public static final String QBAUTHKEY = "qbauthkey";
    public static final String QBAUTH_SECRET = "qbauthsecret";


    public static String DOB="dob";

    public static class BOOKINGCONSTANTS
    {
        public static int PENDING=0;
        public static int ACCEPTED=1;
        public static int REJECTED=2;
        public static int MISSED=3;
        public static int CANCEL=4;
        public static int SERVED=5;
    }

    /*--------Report--------- */
    //Body vitals
    public static final String REPORTDATA = "reportdata";
    public static final String BPMACHINE = "bpmachine";
    public static final String SYSTOLIC = "systolic";
    public static final String DIASTOLIC = "diastolic";
    public static final String PULSE = "pulse";
    public static final String WEIGHTMACHINE = "weightmachine";
    public static final String BMI = "bmi";
    public static final String FAT = "fat";
    public static final String HYDRATION = "hydration";
    public static final String MUSCLE = "muscle";
    public static final String WEIGHT = "weight";
    public static final String HEIGHTMACHINE = "heightmachine";
    public static final String HEIGHT = "height";
    public static final String OXIMETERMACHINE = "oximetermachine";
    public static final String OXYGENSAT = "oxygensat";
    public static final String KIOSKLOCATION = "kiosklocation";
    public static final String KIOSKID = "kioskid";
    public static final String TEMPERATUREMACHINE = "temperaturemachine";
    public static final String TEMPERATURE ="temperature";
    public static final String BLOODGLUCOSEMACHIINE = "bloodglucosemachine";
    public static final String BLOODGLUCOSE = "bloodglucose";
    public static final String REPORTID = "reportid";
    public static final String REPORTKEY = "reportkey";
    public static final String REPORTTYPE = "reporttype";
    public static final String REPORTSTATUS = "reportstatus";
    public static final String HEMOGLOBINMACHINE = "hemoglobinmachine";
    public static final String HEMOGLOBIN = "hb";
    public static final String HEMATOCRIT = "hct";
    public static final String LIPIDMACHINE = "lipidmachine";
    public static final String HDL = "hdl";
    public static final String LDL = "ldl";
    public static final String TOTALCH = "tc";
    public static final String TRIGLYCERIDE = "tg";
    public static final String MACHINESTR = "machinestr";
    public static final String STARTDATE = "startdate";
    public static final String ENDDATE = "enddate";


    //prescription_data
    public static final String PRESCRIPTIONDATA ="prescriptiondat";
    public static final String PRESCRIPTION=     "prescription";
    public static final String PRESCRIPTIONID=   "prescriptionid";
    public static final String PRESCRIPTIONKEY=  "prescriptionkey";
    public static final String DOCTORNAME = "doctorname";
    public static final String DOCTORQUALIFICATIONS = "doctorqualifications";
    public static final String DOCTOREXPERIENCE = "doctorexperience";
    public static final String DOCTORSPECIALIZATION = "doctorspecialization";
    public static final String QUALIFICATIONS = "qualification";
    public static final String EXPERIENCE = "experience";
    public static final String SPECIALIZATION = "specialization";
    public static final String AGE = "age";
    public static final String BIOSKECTH = "bioskecth";
    public static final String MEDICINE ="medicine";
    public static final String INSTRUCTIONS ="instructions";
    public static final String MORNING ="morning";
    public static final String AFTERNOON ="afternoon";
    public static final String EVENING ="evening";

    // Scanned Reports
    public static final String SCANNEDREPORTTITLE = "reporttitle";
    public static final String SCANNEDREPORTDESCRIPTION = "reportdescription";
    public static final String SCANNEDREPORTIMGURL = "reportimgurl";
    public static final String SCANNEDREPORTID = "reportid";
    public static final String SCANNEDREPORTKEY = "reportkey";


    public static final String SENDERNUMBER=     "sendernumber";

    //doctor online status
    public static final String ONLINESTATUS = "onlinestatus";

    //healthreport
    public static final String HEALTHHISTORY = "healthhistory";
    public static final String HISTORYTYPE = "historytype";

    //Doctor category and Booking

    public static final String CATEGORYNAME = "categoryname";
    public static final String CATEGORYDESCRIPTION = "categorydescription";
    public static final String CATEGORYLIST = "categorylist";
    public static final String CATEGORYTYPE = "categorytype";
    public static final String SKILLNUM = "skillnum";
    public static final String SKILLSTR = "skillstr";

    //Main Page Slider Image
    public static final String SLIDERIMAGELIST = "sliderimagelist";

    //QB Chat
    public static final String QBUSERNAME = "qbusername";
    public static final String QBPASSWORD = "qbpassword";
    public static final String QBID = "qbappid";

    //Health Package
    public static final String PACKAGEID = "packageid";
    public static final String PACKAGENAME = "packagename";
    public static final String DURATION = "duration";
    public static final String PACKAGEFEE = "packagefee";
    public static final String PACKAGEDESCRIPTION = "packagedescription";
    public static final String PACKAGELIST = "packagelist";
    public static final String PERIOD = "period";
    public static final String ENROLLMENTCOUNT = "enrollmentcount";
    public static final String PACKAGECODE = "code";
    public static final String ENROLLMENTSTATUS = "enrollmentstatus";

    //health package activation
    public static final String COACHQBID = "coachqbid";

    //token
    public static final String WAITING_NUMBER = "waiting_number";
    public static final String CURRENT_TOKEN = "current_token";
    public static final String ETA = "eta";



}