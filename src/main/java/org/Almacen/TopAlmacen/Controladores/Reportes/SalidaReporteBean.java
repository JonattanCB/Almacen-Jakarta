package org.Almacen.TopAlmacen.Controladores.Reportes;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
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
import java.io.Serializable;
import java.util.*;

@Named("salidaReporteBean")
@ViewScoped
public class SalidaReporteBean implements Serializable {

    public void generarPDF() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String idParam = request.getParameter("id");
            int id = idParam != null ? Integer.parseInt(idParam) : 12345; // Valor de ejemplo si el parámetro no se proporciona

            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream logoEmpresa = servletContext.getResourceAsStream("/resources/imagenes/logo.png");
            InputStream reporteSalida = servletContext.getResourceAsStream("/resources/reportes/Reporte_Salida/Comprobante_Salida.jasper");

            if (logoEmpresa != null && reporteSalida != null) {
                // Crear datos falsos para `registro`
                SalidaRegistro registro = new SalidaRegistro();
                registro.setFecha(new Date());
                registro.setUsuario("Usuario Ejemplo");
                registro.setDependencia_s("Dependencia Ejemplo");
                registro.setPara_uso("Uso Ejemplo");
                registro.setTotalC_v("300");

                // Crear lista de datos falsos para `detalles`
                List<SalidaDetalle> detalles = new ArrayList<>();
                SalidaDetalle detalle1 = new SalidaDetalle();
                detalle1.setItem_s(1);
                detalle1.setCantidad_s(10);
                detalle1.setMedida_s("Unidad");
                detalle1.setDescripcion_s("Descripción 1");
                detalle1.setCodigo_d("C001");
                detalle1.setCant_d("10");
                detalle1.setMedida_d("Unidad");
                detalle1.setUnidad_v("Unidad");
                detalle1.setTotal_v("100");
                detalles.add(detalle1);

                SalidaDetalle detalle2 = new SalidaDetalle();
                detalle2.setItem_s(2);
                detalle2.setCantidad_s(20);
                detalle2.setMedida_s("Unidad");
                detalle2.setDescripcion_s("Descripción 2");
                detalle2.setCodigo_d("C002");
                detalle2.setCant_d("20");
                detalle2.setMedida_d("Unidad");
                detalle2.setUnidad_v("Unidad");
                detalle2.setTotal_v("200");
                detalles.add(detalle2);

                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(detalles.toArray());

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("Fecha", registro.getFecha());
                parameters.put("usuario", registro.getUsuario());
                parameters.put("dependencia_s", registro.getDependencia_s());
                parameters.put("para_uso", registro.getPara_uso());
                parameters.put("totalC_v", registro.getTotalC_v());
                parameters.put("Ruta_Imagen", logoEmpresa);

                JasperReport report = (JasperReport) JRLoader.loadObject(reporteSalida);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);

                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=ComprobanteSalida.pdf");

                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                context.responseComplete();
            } else {
                if (logoEmpresa == null) {
                    showError("No se pudo encontrar el logo de la empresa.");
                }
                if (reporteSalida == null) {
                    showError("No se pudo encontrar el archivo del reporte.");
                }
            }
        } catch (Exception e) {
            showError("Ocurrió un error al intentar generar el reporte: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
    }

    // Clases de ejemplo para `SalidaRegistro` y `SalidaDetalle`
    public static class SalidaRegistro {
        private Date fecha;
        private String usuario;
        private String dependencia_s;
        private String para_uso;
        private String totalC_v;

        // Getters y setters
        public Date getFecha() { return fecha; }
        public void setFecha(Date fecha) { this.fecha = fecha; }
        public String getUsuario() { return usuario; }
        public void setUsuario(String usuario) { this.usuario = usuario; }
        public String getDependencia_s() { return dependencia_s; }
        public void setDependencia_s(String dependencia_s) { this.dependencia_s = dependencia_s; }
        public String getPara_uso() { return para_uso; }
        public void setPara_uso(String para_uso) { this.para_uso = para_uso; }
        public String getTotalC_v() { return totalC_v; }
        public void setTotalC_v(String totalC_v) { this.totalC_v = totalC_v; }
    }

    public static class SalidaDetalle {
        private int item_s;
        private int cantidad_s;
        private String medida_s;
        private String descripcion_s;
        private String codigo_d;
        private String cant_d;
        private String medida_d;
        private String unidad_v;
        private String total_v;

        // Getters y setters
        public int getItem_s() { return item_s; }
        public void setItem_s(int item_s) { this.item_s = item_s; }
        public int getCantidad_s() { return cantidad_s; }
        public void setCantidad_s(int cantidad_s) { this.cantidad_s = cantidad_s; }
        public String getMedida_s() { return medida_s; }
        public void setMedida_s(String medida_s) { this.medida_s = medida_s; }
        public String getDescripcion_s() { return descripcion_s; }
        public void setDescripcion_s(String descripcion_s) { this.descripcion_s = descripcion_s; }
        public String getCodigo_d() { return codigo_d; }
        public void setCodigo_d(String codigo_d) { this.codigo_d = codigo_d; }
        public String getCant_d() { return cant_d; }
        public void setCant_d(String cant_d) { this.cant_d = cant_d; }
        public String getMedida_d() { return medida_d; }
        public void setMedida_d(String medida_d) { this.medida_d = medida_d; }
        public String getUnidad_v() { return unidad_v; }
        public void setUnidad_v(String unidad_v) { this.unidad_v = unidad_v; }
        public String getTotal_v() { return total_v; }
        public void setTotal_v(String total_v) { this.total_v = total_v; }
    }
}
