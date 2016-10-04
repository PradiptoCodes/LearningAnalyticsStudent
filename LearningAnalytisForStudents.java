package com.exam.analytics;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.exam.dbfactory.ConnectionFactory;
import com.exam.dto.*;

/**
 * Servlet implementation class LearningAnalytisForStudents
 */
@WebServlet("/LearningAnalytisForStudents")
public class LearningAnalytisForStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LearningAnalytisForStudents() {
        super();
        
   

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
//******************* Analytics Report ****************************************************************************
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
	
public static  List<StudentAnalyticsReportDto> getAnalyticsReport(String tutorId,HttpServletRequest request)
{
List<StudentAnalyticsReportDto> studentlist=new ArrayList<StudentAnalyticsReportDto>();
Connection  con=null;
try
{
con = ConnectionFactory.getCon();
studentlist=StudentAnalyticsCalculation.getStudentPerformanceReport(tutorId);

String NhighestMarks="0";
String NumberofStudents="0";
String NumberofWebUsed="0";
String NumberofQuestionAsked="0";
String NumberofLoginTimes="0";
String NumberofHighestStudents="0";

NhighestMarks=studentlist.get(0).getHighestmarksachived();
NumberofStudents=studentlist.get(0).getNoofstudents();
NumberofWebUsed=studentlist.get(0).getNooflogin();
NumberofQuestionAsked=studentlist.get(0).getNumberofquestionasked();
NumberofLoginTimes=studentlist.get(0).getNooflogintimes();
NumberofHighestStudents=studentlist.get(0).getNoofhighestStudents();

request.setAttribute("NhighestMarks",NhighestMarks);
request.setAttribute("NumberofStudents", NumberofStudents);
request.setAttribute("NumberofWebUsed",NumberofWebUsed);
request.setAttribute("NumberofQuestionAsked",NumberofQuestionAsked);
request.setAttribute("NumberofLoginTimes", NumberofLoginTimes);;
request.setAttribute("NumberofHighestStudents", NumberofHighestStudents);;

//----------------------------------------------------------------------------------------------------------------------- 
  String chartname="No. of Students";
  DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
  List<String> studentsnumberanalyticts=new ArrayList<String>();
  studentsnumberanalyticts=getAllStudents(con);
  
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(0)), chartname,  "JAN" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(1)), chartname ,  "FEB" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(2)), chartname,  "MAR" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(3)),chartname , "APR" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(4)) ,chartname , "MAY" ); 
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(5)) ,chartname , "JUN" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(6)),chartname, "JUL" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(7)) , chartname , "AUG" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(8)), chartname, "SEP" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(9)) , chartname, "OCT" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(10)) , chartname, "NOV" );
  line_chart_dataset.addValue( Integer.parseInt(studentsnumberanalyticts.get(11)) , chartname, "DEC" );

  JFreeChart lineChartObject = ChartFactory.createLineChart(
     "Students counts per month","Months",
     "No of Students ",
     line_chart_dataset,PlotOrientation.VERTICAL,
     true,true,false);

  int width = 640; /* Width of the image */
  int height = 480; /* Height of the image */ 
  
  String appPath = request.getServletContext().getRealPath("/images/LineChart"+tutorId+".jpeg");
  File lineChart = new File( appPath ); 
  ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
  
  
  String chartname2="Months";
  DefaultCategoryDataset line_chart_dataset2 = new DefaultCategoryDataset();
  List<String> studentsnumberanalytictsHigestMArks=new ArrayList<String>();
  studentsnumberanalytictsHigestMArks=getAllMeritStudentsNumbers(con);
  
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(0)), chartname2,  "JAN" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(1)), chartname2 ,  "FEB" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(2)), chartname2,  "MAR" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(3)),chartname2 , "APR" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(4)) ,chartname2 , "MAY" ); 
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(5)) ,chartname2 , "JUN" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(6)),chartname2, "JUL" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(7)) , chartname2 , "AUG" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(8)), chartname2, "SEP" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(9)) , chartname2, "OCT" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(10)) , chartname2, "NOV" );
  line_chart_dataset2.addValue( Integer.parseInt(studentsnumberanalytictsHigestMArks.get(11)) , chartname2, "DEC" );

  JFreeChart lineChartObject2 = ChartFactory.createLineChart(
     "Highest Marks achive per month","Months",
     "Highest Marks achived ",
     line_chart_dataset2,PlotOrientation.VERTICAL,
     true,true,false);

 /* Height of the image */ 
  
  String appPath2 = request.getServletContext().getRealPath("/images/LineChart2"+tutorId+".jpeg");
  File lineChart2 = new File( appPath2 ); 
  ChartUtilities.saveChartAsJPEG(lineChart2 ,lineChartObject2, width ,height);
  

  
  

//-----------------------------------------------------------------------------------------------------------------------
  final String jan = "JAN";
  final String feb = "FEB";
  final String mar = "MAR";
  final String apr = "APR";
  final String may = "MAY";
  final String jun = "JUN";
  final String jul = "JUL";
  final String aug = "aug";
  final String sep = "sep";
  final String oct = "oct";
  final String nov = "NOV";
  final String dec = "DEC";

  final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
  
  List<String> studentperformancelist=new ArrayList<String>();
  studentperformancelist=getAllMeritStudentsNumbers(con);
  

  dataset.addValue(Integer.parseInt(studentperformancelist.get(0)) , jan, jan );
  dataset.addValue( Integer.parseInt(studentperformancelist.get(1)) , feb , feb );
  dataset.addValue(Integer.parseInt(studentperformancelist.get(2)) , mar , mar);
  dataset.addValue( Integer.parseInt(studentperformancelist.get(3)) , apr , apr );
  dataset.addValue( Integer.parseInt(studentperformancelist.get(4)) , may , may );
  dataset.addValue( Integer.parseInt(studentperformancelist.get(5)) , jun , jun );
  dataset.addValue(Integer.parseInt(studentperformancelist.get(6)), jul , jul );
  dataset.addValue( Integer.parseInt(studentperformancelist.get(7)), aug , aug );
  dataset.addValue( Integer.parseInt(studentperformancelist.get(8)), sep , sep );
  dataset.addValue(  Integer.parseInt(studentperformancelist.get(9)) , oct , oct);
  dataset.addValue(  Integer.parseInt(studentperformancelist.get(10)) , nov , nov );
  dataset.addValue(  Integer.parseInt(studentperformancelist.get(11)) , dec , dec );


  
  JFreeChart barChart = ChartFactory.createBarChart(
     "Number of Merit Students", 
     "Months", "Number of Students", 
     dataset,PlotOrientation.HORIZONTAL, 
     true, true, false);
     
width = 640; /* Width of the image */
height = 480; /* Height of the image */ 


  String appPath3 = request.getServletContext().getRealPath("/images/BarChart"+tutorId+".jpeg");



  File BarChart = new File( appPath3 ); 
  ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
  
//----------------------------------------------------------------------------------------------------------------------------------------------		      
  
   
//----------------------------------------------------------------------------------------------------------------------------------------------------		      
  
  List<String> studentNoQuestionasked=new ArrayList<String>();
  studentNoQuestionasked=getNumberofQuestionAsked(con);
  
  DefaultPieDataset dataset2 = new DefaultPieDataset( );             
  dataset2.setValue( "Jan" , Integer.parseInt(studentNoQuestionasked.get(0)) );             
  dataset2.setValue( "Feb" ,Integer.parseInt(studentNoQuestionasked.get(1)) );             
  dataset2.setValue( "Mar" ,Integer.parseInt(studentNoQuestionasked.get(2)) );             
  dataset2.setValue( "Apr" , Integer.parseInt(studentNoQuestionasked.get(3)) ); 
  dataset2.setValue( "May" , Integer.parseInt(studentNoQuestionasked.get(4)) ); 
  dataset2.setValue( "Jun" ,Integer.parseInt(studentNoQuestionasked.get(5)) ); 
  dataset2.setValue( "Jul" ,Integer.parseInt(studentNoQuestionasked.get(6)) ); 
  dataset2.setValue( "Aug" , Integer.parseInt(studentNoQuestionasked.get(7)) ); 
  dataset2.setValue( "Sep" , Integer.parseInt(studentNoQuestionasked.get(8)) ); 
  dataset2.setValue( "Oct" , Integer.parseInt(studentNoQuestionasked.get(9)) ); 
  dataset2.setValue( "Nov" , Integer.parseInt(studentNoQuestionasked.get(10)) ); 
  dataset2.setValue( "Dec" , Integer.parseInt(studentNoQuestionasked.get(11)) ); 

  JFreeChart chart = ChartFactory.createPieChart3D( 
     "No of Question asked to Tutors" ,  // chart title                   
     dataset2 ,         // data 
     true ,            // include legend                   
     true, 
     false);

  final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );             
  plot.setStartAngle( 270 );             
  plot.setForegroundAlpha( 0.60f );             
  plot.setInteriorGap( 0.02 );             
  width = 640; /* Width of the image */             
  height = 480; /* Height of the image */       

  String appPath5 = request.getServletContext().getRealPath("/images/PieNquestion"+tutorId+".jpeg");
  
  File pieChart3D = new File(appPath5);                           
  ChartUtilities.saveChartAsJPEG( pieChart3D , chart , width , height );  


}
catch(Exception e)
{
	System.out.println("Exception occur");
}
		
return studentlist;
		
}

public static List<String> getAllStudents(Connection con) 
{
	String year="";
	List<String> noofstudents=new ArrayList<String>();	
  String janstudent="";
  String febstudent="";
  String marstudent="";
  String aprstudent="";
  String maystudent="";
  String junestudent="";
  String julystudent="";
  String augStudent="";
  String sepStudent="";
  String octStudent="";
  String novStudent="";
  String decStudent="";
		  

	Calendar now = Calendar.getInstance();   // Gets the current date and time
	int years = now.get(Calendar.YEAR);
	year=years+"";
	
	try
	{
	
	PreparedStatement psjan=con.prepareStatement("SELECT marksobtain FROM user_mst WHERE DATE BETWEEN '"+year+"-01-01' AND '"+year+"-01-31'")	;
	ResultSet rsjan=psjan.executeQuery();
	while(rsjan.next())
	{
		janstudent=rsjan.getString("marksobtain");
	}
		
	PreparedStatement psfeb=con.prepareStatement("SELECT marksobtain FROM user_mst WHERE DATE BETWEEN '"+year+"-02-01' AND '"+year+"-02-30'")	;
	ResultSet rsfeb=psfeb.executeQuery();
	while(rsfeb.next())
	{
		febstudent=rsfeb.getString("marksobtain");
	}	
		
		
	PreparedStatement psmar=con.prepareStatement("SELECT marksobtain FROM user_mst WHERE DATE BETWEEN '"+year+"-03-01' AND '"+year+"-03-31'")	;
	ResultSet rsmar=psmar.executeQuery();
	while(rsmar.next())
	{
		marstudent=rsmar.getString("marksobtain");
	}	
		
		
	PreparedStatement psapr=con.prepareStatement("SELECT marksobtain FROM user_mst WHERE DATE BETWEEN '"+year+"-04-01' AND '"+year+"-04-31'")	;
	ResultSet rsapr=psapr.executeQuery();
	while(rsapr.next())
	{
		aprstudent=rsapr.getString("marksobtain");
	}
	
	
	PreparedStatement psmay=con.prepareStatement("SELECT marksobtain FROM user_mst WHERE DATE BETWEEN '"+year+"-05-01' AND '"+year+"-05-31'")	;
	ResultSet rsmay=psmay.executeQuery();
	while(rsmay.next())
	{
		maystudent=rsmay.getString("marksobtain");
	}
	
	
	PreparedStatement psjun=con.prepareStatement("SELECT marksobtain FROM user_mst WHERE DATE BETWEEN '"+year+"-06-01' AND '"+year+"-06-31'")	;
	ResultSet rsjun=psjun.executeQuery();
	while(rsjun.next())
	{
		junestudent=rsjun.getString("marksobtain");
	}
	
	
	PreparedStatement psjul=con.prepareStatement("SELECT marksobtain FROM user_mst WHERE DATE BETWEEN '"+year+"-07-01' AND '"+year+"-07-31'")	;
	ResultSet   rsjul=psjul.executeQuery();
	while(rsjul.next())
	{
		julystudent=rsjul.getString("marksobtain");
	}
		
	
	PreparedStatement psaug=con.prepareStatement("SELECT marksobtain FROM user_mst WHERE DATE BETWEEN '"+year+"-08-01' AND '"+year+"-08-31'")	;
	ResultSet   rsaug=psaug.executeQuery();
	while(rsaug.next())
	{
		augStudent=rsaug.getString("marksobtain");
	}
		
	
	PreparedStatement pssep=con.prepareStatement("SELECT marksobtain FROM user_mst WHERE DATE BETWEEN '"+year+"-09-01' AND '"+year+"-09-31'")	;
	ResultSet   rssep=pssep.executeQuery();
	while(rssep.next())
	{
		sepStudent=rssep.getString("NoofStudents");
	}
		
	

	PreparedStatement psoct=con.prepareStatement("SELECT COUNT(NAME) AS NoofStudents FROM user_mst WHERE DATE BETWEEN '"+year+"-10-01' AND '"+year+"-10-31'")	;
	ResultSet   rsoct=psoct.executeQuery();
	while(rsoct.next())
	{
		octStudent=rsoct.getString("NoofStudents");
	}
	
	
	PreparedStatement psnov=con.prepareStatement("SELECT COUNT(NAME) AS NoofStudents FROM user_mst WHERE DATE BETWEEN '"+year+"-11-01' AND '"+year+"-11-31'")	;
	ResultSet   rsnov=psnov.executeQuery();
	while(rsnov.next())
	{
		novStudent=rsnov.getString("NoofStudents");
	}
		

	PreparedStatement psdec=con.prepareStatement("SELECT COUNT(NAME) AS NoofStudents FROM user_mst WHERE DATE BETWEEN '"+year+"-06-01' AND '"+year+"-06-31'")	;
	ResultSet   rsdec=psdec.executeQuery();
	while(rsdec.next())
	{
		decStudent=rsdec.getString("NoofStudents");
	}
		
	
	noofstudents.add(janstudent);
	noofstudents.add(febstudent);
	noofstudents.add(marstudent);
	noofstudents.add(aprstudent);
	noofstudents.add(maystudent);
	noofstudents.add(junestudent);
	noofstudents.add(julystudent);
	noofstudents.add(augStudent);
	noofstudents.add(sepStudent);
	noofstudents.add(octStudent);
	noofstudents.add(novStudent);
	noofstudents.add(decStudent);
	
	}
	catch(Exception e)
	{
		System.out.println("Exception occur"+e);
	}
	
	return noofstudents;
	
}

public static List<String> getAllStudentsNumberofWebsiteaccess(Connection con) 
{
	String year="";
	List<String> noofstudents=new ArrayList<String>();	
  String janstudent="";
  String febstudent="";
  String marstudent="";
  String aprstudent="";
  String maystudent="";
  String junestudent="";
  String julystudent="";
  String augStudent="";
  String sepStudent="";
  String octStudent="";
  String novStudent="";
  String decStudent="";
		  
	
	Calendar now = Calendar.getInstance();   // Gets the current date and time
	int years = now.get(Calendar.YEAR);
	year=years+"";
	


	try
	{
	
	PreparedStatement psjan=con.prepareStatement("SELECT COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN '"+year+"-01-01' AND '"+year+"-01-31'")	;
	ResultSet rsjan=psjan.executeQuery();
	while(rsjan.next())
	{
		janstudent=rsjan.getString("NoofStudents");
	}
		
	PreparedStatement psfeb=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN '"+year+"-02-01' AND '"+year+"-02-30'")	;
	ResultSet rsfeb=psfeb.executeQuery();
	while(rsfeb.next())
	{
		febstudent=rsfeb.getString("NoofStudents");
	}	
		
		
	PreparedStatement psmar=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN '"+year+"-03-01' AND '"+year+"-03-31'")	;
	ResultSet rsmar=psmar.executeQuery();
	while(rsmar.next())
	{
		marstudent=rsmar.getString("NoofStudents");
	}	
		
		
	PreparedStatement psapr=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN '"+year+"-04-01' AND '"+year+"-04-31'")	;
	ResultSet rsapr=psapr.executeQuery();
	while(rsapr.next())
	{
		aprstudent=rsapr.getString("NoofStudents");
	}
	
	
	PreparedStatement psmay=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN  '"+year+"-05-01' AND '"+year+"-05-31'")	;
	ResultSet rsmay=psmay.executeQuery();
	while(rsmay.next())
	{
		maystudent=rsmay.getString("NoofStudents");
	}
	
	
	PreparedStatement psjun=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN  '"+year+"-06-01' AND '"+year+"-06-31'")	;
	ResultSet rsjun=psjun.executeQuery();
	while(rsjun.next())
	{
		junestudent=rsjun.getString("NoofStudents");
	}
	
	
	PreparedStatement psjul=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN '"+year+"-07-01' AND '"+year+"-07-31'")	;
	ResultSet   rsjul=psjul.executeQuery();
	while(rsjul.next())
	{
		julystudent=rsjul.getString("NoofStudents");
	}
		
	
	

	PreparedStatement psaug=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN '"+year+"-08-01' AND '"+year+"-08-31'")	;
	ResultSet   rsaug=psaug.executeQuery();
	while(rsaug.next())
	{
		augStudent=rsaug.getString("NoofStudents");
	}
		
	
	

	PreparedStatement pssep=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN  '"+year+"-09-01' AND '"+year+"-09-31'")	;
	ResultSet   rssep=pssep.executeQuery();
	while(rssep.next())
	{
		sepStudent=rssep.getString("NoofStudents");
	}
		
	
	PreparedStatement psoct=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN '"+year+"-10-01' AND '"+year+"-10-31'")	;
	ResultSet   rsoct=psoct.executeQuery();
	while(rsoct.next())
	{
		octStudent=rsoct.getString("NoofStudents");
	}
	
	
	PreparedStatement psnov=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN '"+year+"-11-01' AND '"+year+"-11-31'")	;
	ResultSet   rsnov=psnov.executeQuery();
	while(rsnov.next())
	{
		novStudent=rsnov.getString("NoofStudents");
	}
		
	PreparedStatement psdec=con.prepareStatement("SELECT  COUNT(LoginTime) AS NoofStudents FROM logintimemaster WHERE LoginTime BETWEEN '"+year+"-06-01' AND '"+year+"-06-31'")	;
	ResultSet   rsdec=psdec.executeQuery();
	while(rsdec.next())
	{
		decStudent=rsdec.getString("NoofStudents");
	}
		
	noofstudents.add(janstudent);
	noofstudents.add(febstudent);
	noofstudents.add(marstudent);
	noofstudents.add(aprstudent);
	noofstudents.add(maystudent);
	noofstudents.add(junestudent);
	noofstudents.add(julystudent);
	noofstudents.add(augStudent);
	noofstudents.add(sepStudent);
	noofstudents.add(octStudent);
	noofstudents.add(novStudent);
	noofstudents.add(decStudent);
	
	}
	catch(Exception e)
	{
		System.out.println("Exception occur"+e);
	}
	
	return noofstudents;
	
}


public static List<String> getAllMeritStudentsNumbers(Connection con) 
{
	String year="";
	List<String> noofstudents=new ArrayList<String>();	
  String janstudent="";
  String febstudent="";
  String marstudent="";
  String aprstudent="";
  String maystudent="";
  String junestudent="";
  String julystudent="";
  String augStudent="";
  String sepStudent="";
  String octStudent="";
  String novStudent="";
  String decStudent="";
		  
	Calendar now = Calendar.getInstance();   // Gets the current date and time
	int years = now.get(Calendar.YEAR);
	year=years+"";
	
	try
	{
	
	PreparedStatement psjan=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-01-01' AND '"+year+"-01-31' AND marksobtain>80 ")	;
	ResultSet rsjan=psjan.executeQuery();
	while(rsjan.next())
	{
		janstudent=rsjan.getString("NoofStudents");
	}
		
	PreparedStatement psfeb=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-02-01' AND '"+year+"-02-31' AND marksobtain>80")	;
	ResultSet rsfeb=psfeb.executeQuery();
	while(rsfeb.next())
	{
		febstudent=rsfeb.getString("NoofStudents");
	}	
		
		
	PreparedStatement psmar=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-03-01' AND '"+year+"-03-31' AND marksobtain>80")	;
	ResultSet rsmar=psmar.executeQuery();
	while(rsmar.next())
	{
		marstudent=rsmar.getString("NoofStudents");
	}	
		
		
	PreparedStatement psapr=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-04-01' AND '"+year+"-04-31' AND marksobtain>80")	;
	ResultSet rsapr=psapr.executeQuery();
	while(rsapr.next())
	{
		aprstudent=rsapr.getString("NoofStudents");
	}
	
	
	PreparedStatement psmay=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-05-01' AND '"+year+"-05-31' AND marksobtain>80")	;
	ResultSet rsmay=psmay.executeQuery();
	while(rsmay.next())
	{
		maystudent=rsmay.getString("NoofStudents");
	}
	
	
	PreparedStatement psjun=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-06-01' AND '"+year+"-06-31' AND marksobtain>80")	;
	ResultSet rsjun=psjun.executeQuery();
	while(rsjun.next())
	{
		junestudent=rsjun.getString("NoofStudents");
	}
	
	
	PreparedStatement psjul=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-07-01' AND '"+year+"-07-31' AND marksobtain>80")	;
	ResultSet   rsjul=psjul.executeQuery();
	while(rsjul.next())
	{
		julystudent=rsjul.getString("NoofStudents");
	}
		
	
	PreparedStatement psaug=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-08-01' AND '"+year+"-08-31' AND marksobtain>80")	;
	ResultSet   rsaug=psaug.executeQuery();
	while(rsaug.next())
	{
		augStudent=rsaug.getString("NoofStudents");
	}
		
	
	PreparedStatement pssep=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-09-01' AND '"+year+"-09-31' AND marksobtain>80")	;
	ResultSet   rssep=pssep.executeQuery();
	while(rssep.next())
	{
		sepStudent=rssep.getString("NoofStudents");
	}
		

	PreparedStatement psoct=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-10-01' AND '"+year+"-10-31' AND marksobtain>80")	;
	ResultSet   rsoct=psoct.executeQuery();
	while(rsoct.next())
	{
		octStudent=rsoct.getString("NoofStudents");
	}
	
	
	PreparedStatement psnov=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-11-01' AND '"+year+"-11-31' AND marksobtain>80")	;
	ResultSet   rsnov=psnov.executeQuery();
	while(rsnov.next())
	{
		novStudent=rsnov.getString("NoofStudents");
	}
		

	PreparedStatement psdec=con.prepareStatement("Select COUNT(dateofexam) AS NoofStudents FROM studentexamansmaster WHERE dateofexam BETWEEN '"+year+"-12-01' AND '"+year+"-12-31' AND marksobtain>80")	;
	ResultSet   rsdec=psdec.executeQuery();
	while(rsdec.next())
	{
		decStudent=rsdec.getString("NoofStudents");
	}
		
	
	noofstudents.add(janstudent);
	noofstudents.add(febstudent);
	noofstudents.add(marstudent);
	noofstudents.add(aprstudent);
	noofstudents.add(maystudent);
	noofstudents.add(junestudent);
	noofstudents.add(julystudent);
	noofstudents.add(augStudent);
	noofstudents.add(sepStudent);
	noofstudents.add(octStudent);
	noofstudents.add(novStudent);
	noofstudents.add(decStudent);
	

	}
	catch(Exception e)
	{
		System.out.println("Exception occur"+e);
	}
	

	return noofstudents;
	
}


public static List<String> getNumberofQuestionAsked(Connection con) 
{
  String year="";
  List<String> noofstudents=new ArrayList<String>();	
  String janstudent="";
  String febstudent="";
  String marstudent="";
  String aprstudent="";
  String maystudent="";
  String junestudent="";
  String julystudent="";
  String augStudent="";
  String sepStudent="";
  String octStudent="";
  String novStudent="";
  String decStudent="";
		  

	Calendar now = Calendar.getInstance();   // Gets the current date and time
	int years = now.get(Calendar.YEAR);
	year=years+"";
	

	try
	{
	
	PreparedStatement psjan=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-01-01' AND '"+year+"-01-31'")	;
	ResultSet rsjan=psjan.executeQuery();
	while(rsjan.next())
	{
		janstudent=rsjan.getString("NoofStudents");
	}
		
	PreparedStatement psfeb=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-02-01' AND '"+year+"-02-31' ")	;
	ResultSet rsfeb=psfeb.executeQuery();
	while(rsfeb.next())
	{
		febstudent=rsfeb.getString("NoofStudents");
	}	
		
		
	PreparedStatement psmar=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-03-01' AND '"+year+"-03-31' ")	;
	ResultSet rsmar=psmar.executeQuery();
	while(rsmar.next())
	{
		marstudent=rsmar.getString("NoofStudents");
	}	
		
		
	PreparedStatement psapr=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-04-01' AND '"+year+"-04-31'")	;
	ResultSet rsapr=psapr.executeQuery();
	while(rsapr.next())
	{
		aprstudent=rsapr.getString("NoofStudents");
	}
	
	
	PreparedStatement psmay=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-05-01' AND '"+year+"-05-31' ")	;
	ResultSet rsmay=psmay.executeQuery();
	while(rsmay.next())
	{
		maystudent=rsmay.getString("NoofStudents");
	}
	
	
	PreparedStatement psjun=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-06-01' AND '"+year+"-06-31' ")	;
	ResultSet rsjun=psjun.executeQuery();
	while(rsjun.next())
	{
		junestudent=rsjun.getString("NoofStudents");
	}
	
	
	PreparedStatement psjul=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-07-01' AND '"+year+"-07-31' ")	;
	ResultSet   rsjul=psjul.executeQuery();
	while(rsjul.next())
	{
		julystudent=rsjul.getString("NoofStudents");
	}
		
	
	PreparedStatement psaug=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-08-01' AND '"+year+"-08-31' ")	;
	ResultSet   rsaug=psaug.executeQuery();
	while(rsaug.next())
	{
		augStudent=rsaug.getString("NoofStudents");
	}
		
	
	PreparedStatement pssep=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-09-01' AND '"+year+"-09-31' ")	;
	ResultSet   rssep=pssep.executeQuery();
	while(rssep.next())
	{
		sepStudent=rssep.getString("NoofStudents");
	}
		
	

	PreparedStatement psoct=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-10-01' AND '"+year+"-10-31'")	;
	ResultSet   rsoct=psoct.executeQuery();
	while(rsoct.next())
	{
		octStudent=rsoct.getString("NoofStudents");
	}
	
	
	PreparedStatement psnov=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-11-01' AND '"+year+"-11-31' ")	;
	ResultSet   rsnov=psnov.executeQuery();
	while(rsnov.next())
	{
		novStudent=rsnov.getString("NoofStudents");
	}
		
	
	PreparedStatement psdec=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-12-01' AND '"+year+"-12-31' ")	;
	ResultSet   rsdec=psdec.executeQuery();
	while(rsdec.next())
	{
		decStudent=rsdec.getString("NoofStudents");
	}
		
	
	noofstudents.add(janstudent);
	noofstudents.add(febstudent);
	noofstudents.add(marstudent);
	noofstudents.add(aprstudent);
	noofstudents.add(maystudent);
	noofstudents.add(junestudent);
	noofstudents.add(julystudent);
	noofstudents.add(augStudent);
	noofstudents.add(sepStudent);
	noofstudents.add(octStudent);
	noofstudents.add(novStudent);
	noofstudents.add(decStudent);
	
	}
	catch(Exception e)
	{
		System.out.println("Exception occur"+e);
	}
	
	return noofstudents;
	
}


public static List<String> getNumberofQuestionAskedperformance(Connection con) 
{
  String year="";
  List<String> noofstudents=new ArrayList<String>();	
  String janstudent="";
  String febstudent="";
  String marstudent="";
  String aprstudent="";
  String maystudent="";
  String junestudent="";
  String julystudent="";
  String augStudent="";
  String sepStudent="";
  String octStudent="";
  String novStudent="";
  String decStudent="";
		  

	Calendar now = Calendar.getInstance();   // Gets the current date and time
	int years = now.get(Calendar.YEAR);
	year=years+"";
	

	try
	{
	
	PreparedStatement psjan=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-01-01' AND '"+year+"-01-31'")	;
	ResultSet rsjan=psjan.executeQuery();
	while(rsjan.next())
	{
		janstudent=rsjan.getString("NoofStudents");
	}
		
	PreparedStatement psfeb=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-02-01' AND '"+year+"-02-31' ")	;
	ResultSet rsfeb=psfeb.executeQuery();
	while(rsfeb.next())
	{
		febstudent=rsfeb.getString("NoofStudents");
	}	
		
		
	PreparedStatement psmar=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-03-01' AND '"+year+"-03-31' ")	;
	ResultSet rsmar=psmar.executeQuery();
	while(rsmar.next())
	{
		marstudent=rsmar.getString("NoofStudents");
	}	
		
		
	PreparedStatement psapr=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-04-01' AND '"+year+"-04-31'")	;
	ResultSet rsapr=psapr.executeQuery();
	while(rsapr.next())
	{
		aprstudent=rsapr.getString("NoofStudents");
	}
	
	
	PreparedStatement psmay=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-05-01' AND '"+year+"-05-31' ")	;
	ResultSet rsmay=psmay.executeQuery();
	while(rsmay.next())
	{
		maystudent=rsmay.getString("NoofStudents");
	}
	
	
	PreparedStatement psjun=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-06-01' AND '"+year+"-06-31' ")	;
	ResultSet rsjun=psjun.executeQuery();
	while(rsjun.next())
	{
		junestudent=rsjun.getString("NoofStudents");
	}
	
	
	PreparedStatement psjul=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-07-01' AND '"+year+"-07-31' ")	;
	ResultSet   rsjul=psjul.executeQuery();
	while(rsjul.next())
	{
		julystudent=rsjul.getString("NoofStudents");
	}
		
	
	PreparedStatement psaug=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-08-01' AND '"+year+"-08-31' ")	;
	ResultSet   rsaug=psaug.executeQuery();
	while(rsaug.next())
	{
		augStudent=rsaug.getString("NoofStudents");
	}
		
	
	PreparedStatement pssep=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-09-01' AND '"+year+"-09-31' ")	;
	ResultSet   rssep=pssep.executeQuery();
	while(rssep.next())
	{
		sepStudent=rssep.getString("NoofStudents");
	}
		
	

	PreparedStatement psoct=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-10-01' AND '"+year+"-10-31'")	;
	ResultSet   rsoct=psoct.executeQuery();
	while(rsoct.next())
	{
		octStudent=rsoct.getString("NoofStudents");
	}
	
	
	PreparedStatement psnov=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-11-01' AND '"+year+"-11-31' ")	;
	ResultSet   rsnov=psnov.executeQuery();
	while(rsnov.next())
	{
		novStudent=rsnov.getString("NoofStudents");
	}
		
	
	PreparedStatement psdec=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-12-01' AND '"+year+"-12-31' ")	;
	ResultSet   rsdec=psdec.executeQuery();
	while(rsdec.next())
	{
		decStudent=rsdec.getString("NoofStudents");
	}
		
	
	noofstudents.add(janstudent);
	noofstudents.add(febstudent);
	noofstudents.add(marstudent);
	noofstudents.add(aprstudent);
	noofstudents.add(maystudent);
	noofstudents.add(junestudent);
	noofstudents.add(julystudent);
	noofstudents.add(augStudent);
	noofstudents.add(sepStudent);
	noofstudents.add(octStudent);
	noofstudents.add(novStudent);
	noofstudents.add(decStudent);
	
	}
	catch(Exception e)
	{
		System.out.println("Exception occur"+e);
	}
	
	return noofstudents;
	
}


public static List<String> getFeedbackPerformance(Connection con) 
{
  String year="";
  List<String> noofstudents=new ArrayList<String>();	
  String janstudent="";
  String febstudent="";
  String marstudent="";
  String aprstudent="";
  String maystudent="";
  String junestudent="";
  String julystudent="";
  String augStudent="";
  String sepStudent="";
  String octStudent="";
  String novStudent="";
  String decStudent="";
		  

	Calendar now = Calendar.getInstance();   // Gets the current date and time
	int years = now.get(Calendar.YEAR);
	year=years+"";
	

	try
	{
	
	PreparedStatement psjan=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-01-01' AND '"+year+"-01-31'")	;
	ResultSet rsjan=psjan.executeQuery();
	while(rsjan.next())
	{
		janstudent=rsjan.getString("NoofStudents");
	}
		
	PreparedStatement psfeb=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-02-01' AND '"+year+"-02-31' ")	;
	ResultSet rsfeb=psfeb.executeQuery();
	while(rsfeb.next())
	{
		febstudent=rsfeb.getString("NoofStudents");
	}	
		
		
	PreparedStatement psmar=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-03-01' AND '"+year+"-03-31' ")	;
	ResultSet rsmar=psmar.executeQuery();
	while(rsmar.next())
	{
		marstudent=rsmar.getString("NoofStudents");
	}	
		
		
	PreparedStatement psapr=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-04-01' AND '"+year+"-04-31'")	;
	ResultSet rsapr=psapr.executeQuery();
	while(rsapr.next())
	{
		aprstudent=rsapr.getString("NoofStudents");
	}
	
	
	PreparedStatement psmay=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-05-01' AND '"+year+"-05-31' ")	;
	ResultSet rsmay=psmay.executeQuery();
	while(rsmay.next())
	{
		maystudent=rsmay.getString("NoofStudents");
	}
	
	
	PreparedStatement psjun=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-06-01' AND '"+year+"-06-31' ")	;
	ResultSet rsjun=psjun.executeQuery();
	while(rsjun.next())
	{
		junestudent=rsjun.getString("NoofStudents");
	}
	
	
	PreparedStatement psjul=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-07-01' AND '"+year+"-07-31' ")	;
	ResultSet   rsjul=psjul.executeQuery();
	while(rsjul.next())
	{
		julystudent=rsjul.getString("NoofStudents");
	}
		
	
	PreparedStatement psaug=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-08-01' AND '"+year+"-08-31' ")	;
	ResultSet   rsaug=psaug.executeQuery();
	while(rsaug.next())
	{
		augStudent=rsaug.getString("NoofStudents");
	}
		
	
	PreparedStatement pssep=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-09-01' AND '"+year+"-09-31' ")	;
	ResultSet   rssep=pssep.executeQuery();
	while(rssep.next())
	{
		sepStudent=rssep.getString("NoofStudents");
	}
		
	

	PreparedStatement psoct=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-10-01' AND '"+year+"-10-31'")	;
	ResultSet   rsoct=psoct.executeQuery();
	while(rsoct.next())
	{
		octStudent=rsoct.getString("NoofStudents");
	}
	
	
	PreparedStatement psnov=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN  '"+year+"-11-01' AND '"+year+"-11-31' ")	;
	ResultSet   rsnov=psnov.executeQuery();
	while(rsnov.next())
	{
		novStudent=rsnov.getString("NoofStudents");
	}
		
	
	PreparedStatement psdec=con.prepareStatement("Select COUNT(DATE) AS NoofStudents FROM question_mst WHERE DATE BETWEEN '"+year+"-12-01' AND '"+year+"-12-31' ")	;
	ResultSet   rsdec=psdec.executeQuery();
	while(rsdec.next())
	{
		decStudent=rsdec.getString("NoofStudents");
	}
		
	
	noofstudents.add(janstudent);
	noofstudents.add(febstudent);
	noofstudents.add(marstudent);
	noofstudents.add(aprstudent);
	noofstudents.add(maystudent);
	noofstudents.add(junestudent);
	noofstudents.add(julystudent);
	noofstudents.add(augStudent);
	noofstudents.add(sepStudent);
	noofstudents.add(octStudent);
	noofstudents.add(novStudent);
	noofstudents.add(decStudent);

	
	}
	catch(Exception e)
	{
		System.out.println("Exception occur"+e);
	}
	
	return noofstudents;
	
}


public void analyzeReport()
{
	
	
	
}


//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
//******************* Analytics Report ****************************************************************************
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
}
