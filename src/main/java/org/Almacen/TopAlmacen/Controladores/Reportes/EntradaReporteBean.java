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

@Named("entradaReporteBean")
@ViewScoped
public class EntradaReporteBean implements Serializable {

    public void generarPDF() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            // Valor de ejemplo si el parámetro no se proporciona

            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream logoEmpresa = servletContext.getResourceAsStream("/resources/imagenes/logo.png");
            InputStream reporteEntrada = servletContext.getResourceAsStream("/resources/reportes/Reporte_Entrada/Comprobante_Entrada.jasper");

            if (logoEmpresa != null && reporteEntrada != null) {
                // Crear datos falsos para `registro`
                RegistrosEntrada registro = new RegistrosEntrada();
                registro.setId(888);
                registro.setFingreso(new Date());
                registro.setNguia("123-ABC");
                registro.setProcedencia("Proveedor Falso");
                registro.setNorden("456-DEF");
                registro.setReobservacion("Observación de prueba");

                // Crear lista de datos falsos para `detalles`
                List<DetallesRegistroEntrada> detalles = new ArrayList<>();
                DetallesRegistroEntrada detalle1 = new DetallesRegistroEntrada();
                detalle1.setId_pr("P001");
                detalle1.setDesc_pr("Producto 1");
                detalle1.setNom_unmd("Unidad");
                detalle1.setMarca("Marca A");
                detalle1.setCantidad(10);
                detalles.add(detalle1);

                DetallesRegistroEntrada detalle2 = new DetallesRegistroEntrada();
                detalle2.setId_pr("P002");
                detalle2.setDesc_pr("Producto 2");
                detalle2.setNom_unmd("Unidad");
                detalle2.setMarca("Marca B");
                detalle2.setCantidad(20);
                detalles.add(detalle2);

                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(detalles.toArray());

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("id_EnAl", registro.getId());
                parameters.put("fecha_EnAl", registro.getFingreso());
                parameters.put("n_guia", registro.getNguia());
                parameters.put("procedencia", registro.getProcedencia());
                parameters.put("n_orden_compra", registro.getNorden());
                parameters.put("observacion", registro.getReobservacion());
                parameters.put("Ruta_Imagen", logoEmpresa);

                JasperReport report = (JasperReport) JRLoader.loadObject(reporteEntrada);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);

                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=RegistroEntrada.pdf");

                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                context.responseComplete();
            } else {
                if (logoEmpresa == null) {
                    showError("No se pudo encontrar el logo de la empresa.");
                }
                if (reporteEntrada == null) {
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

    // Clases de ejemplo para `RegistrosEntrada` y `DetallesRegistroEntrada`
    public static class RegistrosEntrada {
        private int id;
        private Date fingreso;
        private String nguia;
        private String procedencia;
        private String norden;
        private String reobservacion;

        // Getters y setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public Date getFingreso() { return fingreso; }
        public void setFingreso(Date fingreso) { this.fingreso = fingreso; }
        public String getNguia() { return nguia; }
        public void setNguia(String nguia) { this.nguia = nguia; }
        public String getProcedencia() { return procedencia; }
        public void setProcedencia(String procedencia) { this.procedencia = procedencia; }
        public String getNorden() { return norden; }
        public void setNorden(String norden) { this.norden = norden; }
        public String getReobservacion() { return reobservacion; }
        public void setReobservacion(String reobservacion) { this.reobservacion = reobservacion; }
    }

    public static class DetallesRegistroEntrada {
        private String id_pr;
        private String desc_pr;
        private String nom_unmd;
        private String marca;
        private int cantidad;

        // Getters y setters
        public String getId_pr() { return id_pr; }
        public void setId_pr(String id_pr) { this.id_pr = id_pr; }
        public String getDesc_pr() { return desc_pr; }
        public void setDesc_pr(String desc_pr) { this.desc_pr = desc_pr; }
        public String getNom_unmd() { return nom_unmd; }
        public void setNom_unmd(String nom_unmd) { this.nom_unmd = nom_unmd; }
        public String getMarca() { return marca; }
        public void setMarca(String marca) { this.marca = marca; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    }
}

