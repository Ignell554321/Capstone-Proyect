package com.example.Avatex_api.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Reporte")
public class ReportesController {
	
	
	//CONEXION BASE DE DATOS ACTUAL
	@Autowired
	protected DataSource datasource;
	
	@RequestMapping(value ="/Venta/{idVenta}", method = RequestMethod.GET)
    public void ventaPDF( HttpServletResponse response,@PathVariable(value="idVenta") Long idVenta) throws  Exception{

		
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/reporteVenta.jasper");
		Map<String,Object> params = new HashMap();
		params.put("idVenta", idVenta);
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, datasource.getConnection());
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
 
    }

	
	@RequestMapping(value ="/Compra/{idCompra}", method = RequestMethod.GET)
    public void compraPDF( HttpServletResponse response,@PathVariable(value="idCompra") Long idCompra) throws  Exception{

		
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/reporteCompra.jasper");
		Map<String,Object> params = new HashMap();
		params.put("idCompra", idCompra);
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, datasource.getConnection());
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
 
    }
	
	@RequestMapping(value ="/Compras", method = RequestMethod.GET)
    public void compraMesPDF( HttpServletResponse response) throws  Exception{

		
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/comprasMes.jasper");
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, datasource.getConnection());
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
 
    }
	
	@RequestMapping(value ="/Ventas", method = RequestMethod.GET)
    public void ventaMesPDF( HttpServletResponse response) throws  Exception{

		
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/VentasMes.jasper");
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, datasource.getConnection());
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
 
    }

}
