package org.Almacen.TopAlmacen.Controladores.RegistroEntrada;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.PdfDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Mappers.DetalleProductoProveedorEntradaMapper;
import org.Almacen.TopAlmacen.Services.DetalleProductoProveedorEntradaService;
import org.Almacen.TopAlmacen.Services.ProductoProveedorEntradaService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Named("ReportesEntradaBeans")
@ViewScoped
public class ReportesEntradaBeans implements Serializable {

    @Inject
    private ProductoProveedorEntradaService productoProveedorEntradaService;

    @Inject
    private DetalleProductoProveedorEntradaService detalleProductoProveedorEntradaService;

    private StreamedContent file;

    @PostConstruct
    private void init() {
        // Initialization logic if needed
    }

    public void generarPDF() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            descargar(idParam);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se ha proporcionado un ID válido."));
        }
    }


    private void descargar(String id){
        try{
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            ProductoProveedorEntradaDto dto = productoProveedorEntradaService.findById(id);
            var detalle = detalleProductoProveedorEntradaService.getAllByProveedorEntradaId(dto.getOC());
            List<PdfDetalleProductoProveedorEntradaDto> lst = detalle.stream()
                    .map(DetalleProductoProveedorEntradaMapper::toDtoPdf)
                    .collect(Collectors.toList());

            // Getting resources
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream logoEmpresa = servletContext.getResourceAsStream("/resources/imagenes/logo.png");
            InputStream reporteEntrada = servletContext.getResourceAsStream("/resources/reportes/Reporte_Entrada/Comprobante_Entrada.jasper");

            if (logoEmpresa != null && reporteEntrada != null) {
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(lst.toArray());
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("id_EnAl", dto.getOC());
                parameters.put("fecha_EnAl", dto.getFechaRegistro());
                parameters.put("procedencia", dto.getEmpresa().getNombre() + " - " + dto.getEmpresa().getNroRUC());
                parameters.put("n_orden_compra", dto.getOC());
                parameters.put("observacion", dto.getObservacion());
                parameters.put("Ruta_Imagen", logoEmpresa);

                JasperReport report = (JasperReport) JRLoader.loadObject(reporteEntrada);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                byte[] pdfBytes = outputStream.toByteArray();

                ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);

                file = DefaultStreamedContent.builder()
                        .name("Registro_entradas.pdf")
                        .contentType("application/pdf")
                        .stream(() -> inputStream)
                        .build();

                inputStream.close();
                outputStream.close();
            } else {
                if (logoEmpresa == null) {
                    System.out.println("No se pudo encontrar el logo de la empresa.");
                }
                if (reporteEntrada == null) {
                    System.out.println("No se pudo encontrar el archivo del reporte.");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
