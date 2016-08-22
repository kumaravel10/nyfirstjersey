<%@ page language="java"%>
    
<%@ page contentType="application/pdf" %>

<%@ page trimDirectiveWhitespaces="true"%>

<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.FileNotFoundException" %>
<%@ page import="java.io.InputStream" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
        DataBeanMaker dataBeanMaker = new DataBeanMaker();
			ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();
        String jrxmlFile = session.getServletContext().getRealPath(request.getContextPath())+"/nyfirstjersey/WebContent/reports/test_jasper.jrxml";
        InputStream input = new FileInputStream(new File(jrxmlFile));
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
	        Map parameters = new HashMap();

        //Generating the report

        JasperReport jasperReport = JasperCompileManager.compileReport(input);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

        //Exporting the report as a PDF

        JasperExportManager.exportReportToPdfFile(jasperPrint, "d:/reports/test_jasper10.pdf");


</body>
</html>