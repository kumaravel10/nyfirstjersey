package com.yeldi.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.MalformedURLException;

//import com.yeldi.web.bean.User;
//import com.yeldi.web.dao.UserDAO; 
import com.yeldi.web.report.DataBean;
import com.yeldi.web.report.DataBeanMaker;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRJdtCompiler;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReportServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/pdf");
		PrintWriter pr=response.getWriter();
		;
		String action=request.getParameter("action");
		if(action!=null)
		{
			/*pr.print(action);
//InputStream inputStream = getServletContext().getResourceAsStream ("nyfirstjersey/WebContent/reports/test_jasper.jrxml");
        
			InputStream inputStream = getClass().getResourceAsStream("reports/test_jasper10.jrxml");      
    DataBeanMaker dataBeanMaker = new DataBeanMaker();
        JRJdtCompiler compiler = new JRJdtCompiler();
        ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();
        
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
        
        Map parameters = new HashMap();
        

JasperDesign jasperDesign;
try {
	jasperDesign = JRXmlLoader.load(inputStream);
	JasperReport jasperReport =  compiler.compileReport(jasperDesign);
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
	JasperExportManager.exportReportToPdfFile(jasperPrint, "d:/reports/test_jasper10.pdf");
} catch (JRException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}*/
			DataBeanMaker dataBeanMaker = new DataBeanMaker();
			ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();
			String jrxmlFile =getServletContext().getRealPath(request.getContextPath())+"/nyfirstjersey/WebContent/reports/test_jasper.jrxml";
	        InputStream input = new FileInputStream(new File(jrxmlFile));
	        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
	        Map parameters = new HashMap();
	        //Generating the report

	        
			try {
				JasperReport jasperReport = JasperCompileManager.compileReport(input);
		        JasperPrint jasperPrint;
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
				JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        //Exporting the report as a PDF

	        
	
			
		}
 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
	}

}
