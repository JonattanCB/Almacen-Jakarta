package org.Almacen.Siman.Controladores.Requerimientos;

import jakarta.annotation.PostConstruct;
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
import org.Almacen.Siman.DTO.ItemsRequerimiento.PdfItemsRequerimientosDto;
import org.Almacen.Siman.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.Siman.Mappers.ItemsRequerimientoMapper;
import org.Almacen.Siman.Mappers.RequerimientoMapper;
import org.Almacen.Siman.Services.RequerimientoService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Named("ReporteRequerimientoBeans")
@ViewScoped
public class ReporteRequerimientoBeans implements Serializable {

    @Inject
    private RequerimientoService requerimientoService;

    private String idRequerimiento;

    private StreamedContent file;

    @PostConstruct
    private void init() {

    }


    public void updateAndGeneratePDF(String idRegistro) {
        this.idRequerimiento = idRegistro;
        GenerarPDF();
    }

    public void GenerarPDF() {
        try {
            // Fetching data
            RequerimientoDto dto = RequerimientoMapper.toDto(requerimientoService.getRequerimiento(idRequerimiento));
            List<PdfItemsRequerimientosDto> lst = requerimientoService.getItemsByRequerimientoId(idRequerimiento).stream().map(ItemsRequerimientoMapper::toPdfDto).collect(Collectors.toList());
            // Getting resources
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream logoEmpresa = servletContext.getResourceAsStream("/resources/imagenes/logo.png");
            InputStream reporteEntrada = servletContext.getResourceAsStream("/resources/reportes/Reporte_Requerimientos/Formulacion_Pedido.jasper");

            if (logoEmpresa != null && reporteEntrada != null) {
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(lst.toArray());
                Map<String, Object> parameters = new HashMap<>();
                String fecha = dto.getFechaRegistrada().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                parameters.put("Ruta_Imagen", logoEmpresa);
                parameters.put("fecha", fecha);
                parameters.put("id", dto.getId());
                parameters.put("D_sol", dto.getDependencia().getNombre());
                parameters.put("estado", dto.getEstado());
                parameters.put("r_entrada", dto.getRazonEntrada());
                parameters.put("r_salida", dto.getRazonSalida());


                JasperReport report = (JasperReport) JRLoader.loadObject(reporteEntrada);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                byte[] pdfBytes = outputStream.toByteArray();

                ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);

                file = DefaultStreamedContent.builder()
                        .name("Reporte Requerimiento.pdf")
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
