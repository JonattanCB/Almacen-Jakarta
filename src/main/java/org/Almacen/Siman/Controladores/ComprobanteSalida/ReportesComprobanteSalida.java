package org.Almacen.Siman.Controladores.ComprobanteSalida;

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
import org.Almacen.Siman.DTO.ComprobanteSalida.PdfComprobanteSalidaDto;
import org.Almacen.Siman.DTO.DetalleComprobanteSalida.PdfDetallesComprobanteSalidaDto;
import org.Almacen.Siman.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.Siman.Mappers.ComprobanteSalidaMapper;
import org.Almacen.Siman.Mappers.RequerimientoMapper;
import org.Almacen.Siman.Services.ComprobanteSalidaService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
            var req_cs = comprobanteSalidaService.getbyReq_Cs(idcomprobanteSalida);

            PdfComprobanteSalidaDto csDto = ComprobanteSalidaMapper.toPdfDto(comprobanteSalidaService.getById(req_cs.getComprobanteSalida().getId()));
            RequerimientoDto rqdto = RequerimientoMapper.toDto(req_cs.getRequerimiento());
            List<PdfDetallesComprobanteSalidaDto> lst = comprobanteSalidaService.getAlltToPdf(req_cs.getRequerimiento().getId(), idcomprobanteSalida);

            // Getting resources
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream logoEmpresa = servletContext.getResourceAsStream("/resources/imagenes/logo.png");
            InputStream reporteSalida = servletContext.getResourceAsStream("/resources/reportes/Reporte_Salida/Comprobante_Salida.jasper");


            if (csDto.getEstado().equalsIgnoreCase("FINALIZADO")){
                csDto.setEstado("APROBADO");
            }

            if (logoEmpresa != null && reporteSalida != null) {
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(lst.toArray());
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("Fecha", String.valueOf(csDto.getFecha()));
                parameters.put("usuario", csDto.getUsuario());
                parameters.put("dependencia_s", csDto.getDependencia_s());
                parameters.put("para_uso", csDto.getPara_uso());
                parameters.put("totalC_v", csDto.getTotalC_v());
                parameters.put("estado_ap",csDto.getEstado());
                parameters.put("Ruta_Imagen", logoEmpresa);
                parameters.put("nombre_apr", rqdto.getAprobadoPor());
                parameters.put("nombre_ap", csDto.getAprobadoPor());


                JasperReport report = (JasperReport) JRLoader.loadObject(reporteSalida);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                byte[] pdfBytes = outputStream.toByteArray();

                ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);

                file = DefaultStreamedContent.builder()
                        .name("Comprobante Salida.pdf")
                        .contentType("application/pdf")
                        .stream(() -> inputStream)
                        .build();

                inputStream.close();
                outputStream.close();
            } else {
                if (logoEmpresa == null) {
                    System.out.println("No se pudo encontrar el logo de la empresa.");
                }
                if (reporteSalida == null) {
                    System.out.println("No se pudo encontrar el archivo del reporte.");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
