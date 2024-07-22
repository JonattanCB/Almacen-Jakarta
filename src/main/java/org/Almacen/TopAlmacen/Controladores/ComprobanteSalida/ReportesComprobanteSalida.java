package org.Almacen.TopAlmacen.Controladores.ComprobanteSalida;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import lombok.Data;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.Almacen.TopAlmacen.Controladores.Reportes.SalidaReporteBean;
import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.PdfComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.DetalleComprobanteSalida.PdfDetallesComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.PdfDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Mappers.ComprobanteSalidaMapper;
import org.Almacen.TopAlmacen.Mappers.DetalleProductoProveedorEntradaMapper;
import org.Almacen.TopAlmacen.Services.ComprobanteSalidaService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Named("ReportesComprobanteSalida")
@ViewScoped
public class ReportesComprobanteSalida implements Serializable {

    @Inject
    private ComprobanteSalidaService comprobanteSalidaService;

    private String idcomprobanteSalida;

    private StreamedContent file;

    public void updateAndGeneratePDF(String idComprobanteSalida) {
        this.idcomprobanteSalida = idComprobanteSalida;
        GenerarPDF();
    }

    public void GenerarPDF() {
        try {
            // Fetching data
            PdfComprobanteSalidaDto csDto = ComprobanteSalidaMapper.toPdfDto(comprobanteSalidaService.getById(idcomprobanteSalida));
            List<PdfDetallesComprobanteSalidaDto> lst = comprobanteSalidaService.getAlltToPdf(idcomprobanteSalida, idcomprobanteSalida);

            // Getting resources
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream logoEmpresa = servletContext.getResourceAsStream("/resources/imagenes/logo.png");
            InputStream reporteEntrada = servletContext.getResourceAsStream("/resources/reportes/Reporte_Entrada/Comprobante_Entrada.jasper");

            if (logoEmpresa != null && reporteEntrada != null) {
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(lst.toArray());
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("Fecha", String.valueOf(csDto.getFecha()));
                parameters.put("usuario", csDto.getUsuario());
                parameters.put("dependencia_s", csDto.getDependencia_s());
                parameters.put("para_uso", csDto.getPara_uso());
                parameters.put("totalC_v", csDto.getTotalC_v());
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


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
