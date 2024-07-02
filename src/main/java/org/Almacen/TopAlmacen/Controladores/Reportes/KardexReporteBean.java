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

@Named("kardexReporteBean")
@ViewScoped
public class KardexReporteBean implements Serializable {

    public void generarPDF() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String idParam = request.getParameter("id");
            int id = idParam != null ? Integer.parseInt(idParam) : 12345; // Valor de ejemplo si el parámetro no se proporciona

            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream reporteKardex = servletContext.getResourceAsStream("/resources/reportes/Reporte_Kardex/Kardex.jasper");

            if (reporteKardex != null) {
                // Crear datos falsos para `registro`
                KardexRegistro registro = new KardexRegistro();
                registro.setN_kardex(id);
                registro.setArticulo("Artículo Ejemplo");
                registro.setMarca("Marca Ejemplo");
                registro.setOrden_compra("OC-12345");
                registro.setInv_inicial(100);
                registro.setUnid_medida("Unidad");

                // Crear lista de datos falsos para `detalles`
                List<KardexDetalle> detalles = new ArrayList<>();
                KardexDetalle detalle1 = new KardexDetalle();
                detalle1.setFecha_k(new Date());
                detalle1.setArea_k("Área Ejemplo");
                detalle1.setNombre_k("Nombre Ejemplo");
                detalle1.setN_pecosa_k(123);
                detalle1.setInv_inicial_k(100);
                detalle1.setCosto_unitario(10);
                detalle1.setEntrada_k(20);
                detalle1.setSalida_k(10);
                detalle1.setInv_final_k(110);
                detalles.add(detalle1);

                // Agregar un segundo detalle
                KardexDetalle detalle2 = new KardexDetalle();
                detalle2.setFecha_k(new Date());
                detalle2.setArea_k("Otra Área");
                detalle2.setNombre_k("Otro Nombre");
                detalle2.setN_pecosa_k(456);
                detalle2.setInv_inicial_k(150);
                detalle2.setCosto_unitario(15);
                detalle2.setEntrada_k(30);
                detalle2.setSalida_k(5);
                detalle2.setInv_final_k(175);
                detalles.add(detalle2);

                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(detalles.toArray());

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("n_kardex", registro.getN_kardex());
                parameters.put("articulo", registro.getArticulo());
                parameters.put("marca", registro.getMarca());
                parameters.put("orden_compra", registro.getOrden_compra());
                parameters.put("inv_inicial", registro.getInv_inicial());
                parameters.put("unid_medida", registro.getUnid_medida());

                JasperReport report = (JasperReport) JRLoader.loadObject(reporteKardex);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);

                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=KardexReporte.pdf");

                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                context.responseComplete();
            } else {
                showError("No se pudo encontrar el archivo del reporte.");
            }
        } catch (Exception e) {
            showError("Ocurrió un error al intentar generar el reporte: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
    }

    // Clases de ejemplo para `KardexRegistro` y `KardexDetalle`
    public static class KardexRegistro {
        private int n_kardex;
        private String articulo;
        private String marca;
        private String orden_compra;
        private int inv_inicial;
        private String unid_medida;

        // Getters y setters
        public int getN_kardex() { return n_kardex; }
        public void setN_kardex(int n_kardex) { this.n_kardex = n_kardex; }
        public String getArticulo() { return articulo; }
        public void setArticulo(String articulo) { this.articulo = articulo; }
        public String getMarca() { return marca; }
        public void setMarca(String marca) { this.marca = marca; }
        public String getOrden_compra() { return orden_compra; }
        public void setOrden_compra(String orden_compra) { this.orden_compra = orden_compra; }
        public int getInv_inicial() { return inv_inicial; }
        public void setInv_inicial(int inv_inicial) { this.inv_inicial = inv_inicial; }
        public String getUnid_medida() { return unid_medida; }
        public void setUnid_medida(String unid_medida) { this.unid_medida = unid_medida; }
    }

    public static class KardexDetalle {
        private Date fecha_k;
        private String area_k;
        private String nombre_k;
        private int n_pecosa_k;
        private int inv_inicial_k;
        private int costo_unitario;
        private int entrada_k;
        private int salida_k;
        private int inv_final_k;

        // Getters y setters
        public Date getFecha_k() { return fecha_k; }
        public void setFecha_k(Date fecha_k) { this.fecha_k = fecha_k; }
        public String getArea_k() { return area_k; }
        public void setArea_k(String area_k) { this.area_k = area_k; }
        public String getNombre_k() { return nombre_k; }
        public void setNombre_k(String nombre_k) { this.nombre_k = nombre_k; }
        public int getN_pecosa_k() { return n_pecosa_k; }
        public void setN_pecosa_k(int n_pecosa_k) { this.n_pecosa_k = n_pecosa_k; }
        public int getInv_inicial_k() { return inv_inicial_k; }
        public void setInv_inicial_k(int inv_inicial_k) { this.inv_inicial_k = inv_inicial_k; }
        public int getCosto_unitario() { return costo_unitario; }
        public void setCosto_unitario(int costo_unitario) { this.costo_unitario = costo_unitario; }
        public int getEntrada_k() { return entrada_k; }
        public void setEntrada_k(int entrada_k) { this.entrada_k = entrada_k; }
        public int getSalida_k() { return salida_k; }
        public void setSalida_k(int salida_k) { this.salida_k = salida_k; }
        public int getInv_final_k() { return inv_final_k; }
        public void setInv_final_k(int inv_final_k) { this.inv_final_k = inv_final_k; }
    }
}
