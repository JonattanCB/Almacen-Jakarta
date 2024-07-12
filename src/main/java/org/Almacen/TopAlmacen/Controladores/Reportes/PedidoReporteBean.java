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

@Named("pedidoReporteBean")
@ViewScoped
public class PedidoReporteBean implements Serializable {

    public void generarPDF() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String idParam = request.getParameter("id");
            int id = idParam != null ? Integer.parseInt(idParam) : 12345; // Valor de ejemplo si el parámetro no se proporciona

            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream logoEmpresa = servletContext.getResourceAsStream("/resources/imagenes/logo.png");
            InputStream reportePedido = servletContext.getResourceAsStream("/resources/reportes/Reporte_Pedido/Formulacion_Pedido.jasper");

            if (logoEmpresa != null && reportePedido != null) {
                // Crear datos falsos para `registro`
                PedidoRegistro registro = new PedidoRegistro();
                registro.setFecha(new Date()); //fecha
                registro.setId(id); //id
                registro.setD_sol("Dependencia Solicitante Ejemplo"); //Unidad dependencia
                registro.setDestino("Destino Ejemplo"); // No va
                registro.setObservacion("Observación de prueba"); //observacion

                // Crear lista de datos falsos para `detalles`
                List<PedidoDetalle> detalles = new ArrayList<>();
                PedidoDetalle detalle1 = new PedidoDetalle();
                detalle1.setId_p(1); //id
                detalle1.setDesc_p("Producto 1"); //descrio
                detalle1.setNom_tunid("Unidad"); //unidad
                detalle1.setCantidad(10);//cantidad
                detalles.add(detalle1);

                PedidoDetalle detalle2 = new PedidoDetalle();
                detalle2.setId_p(2);
                detalle2.setDesc_p("Producto 2");
                detalle2.setNom_tunid("Unidad");
                detalle2.setCantidad(20);
                detalles.add(detalle2);

                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(detalles.toArray());

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("Ruta_Imagen", logoEmpresa);
                parameters.put("fecha", registro.getFecha());
                parameters.put("id", registro.getId());
                parameters.put("D_sol", registro.getD_sol());
                parameters.put("destino", registro.getDestino());
                parameters.put("observacion", registro.getObservacion());

                JasperReport report = (JasperReport) JRLoader.loadObject(reportePedido);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);

                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=ComprobantePedido.pdf");

                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                context.responseComplete();
            } else {
                if (logoEmpresa == null) {
                    showError("No se pudo encontrar el logo de la empresa.");
                }
                if (reportePedido == null) {
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

    // Clases de ejemplo para `PedidoRegistro` y `PedidoDetalle`
    public static class PedidoRegistro {
        private Date fecha;
        private int id;
        private String D_sol;
        private String destino;
        private String observacion;

        // Getters y setters
        public Date getFecha() { return fecha; }
        public void setFecha(Date fecha) { this.fecha = fecha; }
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getD_sol() { return D_sol; }
        public void setD_sol(String D_sol) { this.D_sol = D_sol; }
        public String getDestino() { return destino; }
        public void setDestino(String destino) { this.destino = destino; }
        public String getObservacion() { return observacion; }
        public void setObservacion(String observacion) { this.observacion = observacion; }
    }

    public static class PedidoDetalle {
        private int id_p;
        private String desc_p;
        private String nom_tunid;
        private int cantidad;

        // Getters y setters
        public int getId_p() { return id_p; }
        public void setId_p(int id_p) { this.id_p = id_p; }
        public String getDesc_p() { return desc_p; }
        public void setDesc_p(String desc_p) { this.desc_p = desc_p; }
        public String getNom_tunid() { return nom_tunid; }
        public void setNom_tunid(String nom_tunid) { this.nom_tunid = nom_tunid; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    }
}
