package com.chetty.reporting.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ls.entities.ExchangeRate;
import com.ls.svc.controller.ExRateSvc.FileType;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Reporter {
	
	public static File download(FileType fileType, List<ExchangeRate> dataBeanList) throws Exception {
		String filePath = "C:/Users/ts-ashish.awasthi/Desktop/jaspertest-read-only/reports/test_jasper.jrxml";

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);

		Map<String, Object> parameters = new HashMap<String, Object>();

		JasperDesign jasperDesign = JRXmlLoader.load(filePath);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		
		if(fileType.equals(FileType.PDF)){
			File pdf = File.createTempFile("output.", ".pdf");
			FileOutputStream fs = new FileOutputStream(pdf);
			JasperExportManager.exportReportToPdfStream(jasperPrint, fs);
			return pdf;
		}else if(fileType.equals(FileType.XLS)){
			File xls = File.createTempFile("output.", ".xls");
			JRXlsExporter exporter1 = new JRXlsExporter();
			exporter1.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter1.setParameter(JRExporterParameter.OUTPUT_FILE, xls);
			exporter1.exportReport();
			return xls;
		}else if(fileType.equals(FileType.XLSX)){
			File xlsx = File.createTempFile("output.", ".xlsx");
			JRXlsxExporter exporter2 = new JRXlsxExporter();
	        exporter2.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter2.setParameter(JRXlsExporterParameter.OUTPUT_FILE, xlsx);
	        exporter2.exportReport();
	        return xlsx;
		}
		return null;
	}
}
