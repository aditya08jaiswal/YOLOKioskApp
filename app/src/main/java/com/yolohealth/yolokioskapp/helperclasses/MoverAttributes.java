package com.yolohealth.yolokioskapp.helperclasses;

/***
 * 
 * @author arpit87
 * use this class to define any json attributes
 * that we get in response from server
 *
 */

public class MoverAttributes {

	//get booking
	public static final String DATETIME = "datetime";
	public static final int NOTIFICATION_UNREAD = 0;
    public static final String DENGUE = "denguemachine";
	public static final String TYPHOID = "typhoidmachine";
	public static final String MALERIA = "malariamachine";
	public static final String HIV = "hivmachine";
	public static final String C = "c";
	public static final String T1 = "t1";
	public static final String T2 = "t2";
	public static final String NS1 = "ns1";
	public static final String MC = "mc";
	public static final String GC = "gc";
	public static final String MT = "mt";
	public static final String GT = "gt";
	public static final String INVESTIGATION = "investigation";
	public static final String URINE = "urinemachine";
	public static final String EYE = "eyemachine";
	public static final String T = "t";
	public static final String PREGANCY = "pregnancymachine";
	public static final String ECGMACHIINE = "ecgmachine";
	public static final String HOSPITAL = "hospitalname";
	public static final String DATEOFBIRTH = "dateofbirth";
	public static final String SIGNPIC = "signpic";
    public static String MOVERSKILLSNAME = "skillnamestr";
    public static String BOOKINGFOR = "bookingfor";
	public static String LANDMARK = "landmark";
    public static String LOCALITY = "locality";
    public static String FLAT = "flat";
	//public static String TIME ="time";
	public static String MOVERID ="moverid";
	public static String UNIQUEID ="uniqueid";
	public static String BOOKINGID = "bookingid";
	//public static String AMPM = "ampm";
	//public static String TIMEDURATION = "timeduration";
	public static String ADDRESS = "address";
    public static final String USERNAME ="username";
	public static String USERID ="userid";
	public static String USERPHONE ="userphone";
	public static String LATITUDE = "latitude";
    public static String LONGITUDE = "longitude";
    //public static String TIMERAMOUNT = "timeramount";
	
	//login mover
	public static String MOVERNAME ="movername";
	public static String EMAIL ="email";
	public static String NOTES ="notes";
	public static final String GENDER ="gender";
	public static String MOVERPHONE = "moverphone";
    public static String MOVERSKILLS = "skillstr";
	public static String MOVERPASSWORD = "password";
	public static String QBPASSWORD = "qbpassword";
    public static String QBUSERNAME = "qbusername";
	public static String QBID = "qbid";
	public static String MOVERGROUPS ="movergroups";
	public static String SPECIALIZATION = "specialization";
	public static String QUALIFICATION = "qualification";
	public static String ISFORHOMECARE = "isforhomecare";
	public static String ISFORTELEMEDICINE = "isfortelemedicine";
	public static String PROFILEPIC = "profilepic";
	public static String EXPERIENCE = "experience";
	public static String DOB = "dob";
	public static String BIOSKETCH = "biosketch";
    public static String SERVICESOFFEREDSTR = "servicesofferedstr";

	//check mover status
	public static String ISAVAILABLE = "isavailable";
	public static String ISBOOKED = "isbooked";
    public static String BOOKINGLIST = "bookinglist";
    public static String BOOKINGSTATUSMOVER = "bookingstatusmover";


	public static String SLOTLENGTH = "slotlength";
	public static String AVAILABILITYID = "availabilityid";

	public static String ONLINESTATUS = "onlinestatus";
	public static String ISBUSY = "isbusy";

	//cancel booking
	public static String CANCELLEDBOOKINGID = "cancelledbookingid";
	
	//  booking status
	public static String BOOKINGSTATUS = "bookingstatus"; //pending:0accepted:1,rejected:2,missed:3,cancelled:4,served:5
	//gcm
    public static String GCMREGID = "gcmregid";

	//service
	public static String SERVICEID = "serviceid";
	public static String SERVICETAG = "servicetag";
	public static String SERVICETITLE = "servicetitle";
	public static String SERVICEDESCRIPTION = "servicedescription";
	public static String SERVICECHARGES = "servicecharges";
    public static String SERVICELIST = "servicelist";

	//receipt
	public static String RECEIPTID = "receiptid";

	//OTP
	public static String OTP = "otp";
    public static String PHONE = "phone";

	//prescription
	public static String PRESCRIPTIONID = "prescriptionid";
	public static String HISTORYTYPE = "historytype";
	public static String DOCTORNAME = "doctorname";
	public static String DOCTOREXPERIENCE = "doctorexperience";
	public static String DOCTORSPECIALIZATION = "doctorspecialization";
	public static String DOCTORQUALIFICATIONS = "doctorqualification";
	public static String PRESCRIPTION = "prescription";
	public static final String AGE = "age";
	public static String MEDICINE = "medicine";
	public static String INSTRUCTIONS = "instructions";
	public static String MORNING = "morning";
	public static String AFTERNOON = "afternoon";
	public static String EVENING = "evening";
	public static String HEALTHHISTORY = "healthhistory";

	/*--------Report--------- */
	//Body vitals
	public static final String REPORTDATA = "reportdata";
	public static final String BPMACHINE = "bpmachine";
	public static final String SYSTOLIC = "systolic";
	public static final String SYSTOLICINFERENCE = "systolic_inference";
	public static final String DIASTOLIC = "diastolic";
	public static final String DIASTOLICINFERENCE = "diastolic_inference";
	public static final String PULSE = "pulse";
	public static final String PULSEINFERENCE = "pulse_inference";
	public static final String WEIGHTMACHINE = "weightmachine";
	public static final String BMI = "bmi";
	public static final String BMIINFERENCE = "bmi_inference";
	public static final String HYDRATION = "hydration";
	public static final String HYDRATIONINFERENCE = "hydration_inference";
	public static final String MUSCLE = "muscle";
	public static final String MUSCLEINFERENCE = "muscle_inference";
	public static final String BONEMASS = "bonemass";
	public static final String BONEMASSINFERENCE = "bonemass_inference";
	public static final String FAT = "fat";
	public static final String FATINFERENCE = "fat_inference";
	public static final String MUSCLEQUALITYINFERENCE = "muscle_quality_inference";
	public static final String VFATINFERENCE = "vfat_inference";
	public static final String METABOLICAGEINFERENCE = "metabolic_age_inference";
	public static final String BMRINFERENCE = "bmr_inference";
	public static final String WEIGHT = "weight";
	public static final String HEIGHTMACHINE = "heightmachine";
	public static final String HEIGHT = "height";
	public static final String OXIMETERMACHINE = "oximetermachine";
	public static final String OXYGENSAT = "oxygensat";
	public static final String OXYGENSATINFERENCE = "oxygensat_inference";
	public static final String KIOSKLOCATION = "kiosklocation";
	public static final String TEMPERATUREMACHINE = "temperaturemachine";
	public static final String TEMPERATURE ="temperature";
	public static final String TEMPERATUREINFERENCE ="temprature_inference";
	public static final String BLOODGLUCOSEMACHIINE = "bloodglucosemachine";
	public static final String BLOODGLUCOSE = "bloodglucose";
	public static final String BLOODGLUCOSEINFERENCE = "bg_inference";
	public static final String REPORTID = "reportid";
	public static final String REPORTKEY = "reportkey";
	public static final String REPORTTYPE = "reporttype";
	public static final String REPORTSTATUS = "reportstatus";
	public static final String HEMOGLOBINMACHINE = "hemoglobinmachine";
	public static final String HEMOGLOBIN = "hb";
	public static final String HEMOGLOBININFERENCE = "hb_inference";
	public static final String HEMATOCRIT = "hct";
	public static final String LIPIDMACHINE = "lipidmachine";
	public static final String HDL = "hdl";
	public static final String HDLINFERENCE = "hdl_inference";
	public static final String LDL = "ldl";
	public static final String LDLINFERENCE = "ldl_inference";
	public static final String TOTALCH = "tc";
	public static final String TOTALCHINFERENCE = "tc_inference";
	public static final String TRIGLYCERIDE = "tg";
	public static final String TRIGLYCERIDEINFERENCE = "tg_inference";
	public static final String MACHINENAME = "machinename";

	public static final String MUSCLEQUALITYSCORE = "muscle_quality_score";
	public static final String METABOLICAGE = "bage";
	public static final String VFAT = "vfat";
	public static final String BMR = "kcal";
	public static final String NEARVISIONINFERENCE = "near_vision_score_inference";
	public static final String FARVISIONINFERENCE = "far_vision_inference";
	public static final String NEARVISIONJSCOREINFERENCE = "near_vision_jscore_inference";

	//Slotbooking
	public static String CONSULTSTATUS = "consultstatus";
	public static String STARTTIME = "starttime";
	public static String ENDTIME = "endtime";
	public static String SLOTNO = "slotno";
	public static String SLOTBOOKINGID = "slotbookingid";
	public static String DATE = "date";
	public static String DAY = "day";
	public static String SLOTDETAILS = "slotdetails";
	public static String UPCOMINGCONSULTLIST = "upcomingconsultlist";
	public static String PASTCONSULTLIST = "pastconsultlist";

	// Scanned Reports
	public static final String SCANNEDREPORTTITLE = "reporttitle";
	public static final String SCANNEDREPORTDESCRIPTION = "reportdescription";
	public static final String SCANNEDREPORTIMGURL = "reportimgurl";
	public static final String SCANNEDREPORTID = "reportid";
	public static final String SCANNEDREPORTKEY = "reportkey";

	//Suggestion MEDICINE
	public static final String SEARCHWORD = "searchword";

	//Health Package
	public static final String PACKAGEID = "packageid";
	public static final String PACKAGENAME = "packagename";
	public static final String DURATION = "duration";
	public static final String PACKAGEFEE = "packagefee";
	public static final String PACKAGEDESCRIPTION = "packagedescription";
	public static final String PACKAGELIST = "packagelist";
	public static final String STARTDATE = "startdate";
	public static final String ENDDATE = "enddate";
	public static final String PERIOD = "period";
	public static final String ENROLMENTCOUNT = "enrolmentcount";
	public static final String ENROLLEDLIST = "enrolledlist";
	public static int NOTIFICATION_READ=1;

    public static class BOOKINGCONSTANTS
	{
		public static int PENDING=0;
		public static int ACCEPTED=1;
		public static int REJECTED=2;
		public static int MISSED=3;
		public static int CANCEL=4;
		public static int SERVED=5;
	}
			
}
