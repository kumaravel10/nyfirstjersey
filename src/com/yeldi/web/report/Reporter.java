package com.yeldi.web.report;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.yeldi.web.bean.User;
//import com.yeldi.web.dao.UserDAO; 
import com.yeldi.web.report.DataBean;
import com.yeldi.web.report.DataBeanMaker;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRJdtCompiler;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.JRExporterParameter;




public class Reporter {
	@SuppressWarnings("unchecked")

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		
		
		String sourceFileName = "reports/test_jasper.jrxml";
	      String printFileName = null;
		
		//InputStream inputStream = new FileInputStream ("reports/test_jasper.jrxml");
        
        DataBeanMaker dataBeanMaker = new DataBeanMaker();
        JRJdtCompiler compiler = new JRJdtCompiler();
        ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();
        
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
        
        Map parameters = new HashMap();
        
        

        JasperDesign jasperDesign = JRXmlLoader.load(sourceFileName);
      JasperReport jasperReport =  compiler.compileReport(jasperDesign);
     // printFileName = JasperFillManager.fillReportToFile(jasperReport,parameters, beanColDataSource);
//JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
//JasperReport jasperReport =  compiler.compileReport(jasperDesign);
JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
JasperExportManager.exportReportToPdfFile(jasperPrint, "d:/reports/test_jasper13.pdf"); 
JasperExportManager.exportReportToHtmlFile(jasperPrint, "d:/reports/test_jasper12.html");

/*JRXlsExporter exporter = new JRXlsExporter();
exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,jasperPrint);
exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"d:/reports/test_jasper15.xls");*/
}
		
		
		
		

	}


