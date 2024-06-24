package org.Almacen.TopAlmacen.Services;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Reporte {

    public void EntradaPDF() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            int id = Integer.parseInt(request.getParameter("id")); // Obtener el ID del parámetro de la solicitud

            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream logoEmpresa = servletContext.getResourceAsStream("/rsc/logo.png");
            InputStream reporteEntrada = servletContext.getResourceAsStream("/Helper/Reporte_Entrada.jasper");

            if (logoEmpresa != null && reporteEntrada != null) {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("id", id); // Suponiendo que se envía como parámetro
                parameters.put("Ruta_Imagen", logoEmpresa);

                JasperReport report = (JasperReport) JRLoader.loadObject(reporteEntrada);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanArrayDataSource(new Object[] {})); // Sin datos específicos

                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=RegistroEntrada.pdf");

                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                context.responseComplete();
            } else {
                showError("No se pudo generar el reporte. Verifique que los recursos necesarios estén disponibles.");
            }
        } catch (Exception e) {
            showError("Ocurrió un error al intentar generar el reporte: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
    }
}
