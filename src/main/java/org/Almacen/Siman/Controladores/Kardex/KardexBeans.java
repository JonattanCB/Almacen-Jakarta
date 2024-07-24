package org.Almacen.Siman.Controladores.Kardex;

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
import org.Almacen.Siman.DTO.DetalleProductoProveedorEntrada.PdfDetalleProductoProveedorEntradaDto;
import org.Almacen.Siman.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.Siman.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.Siman.Mappers.DetalleProductoProveedorEntradaMapper;
import org.Almacen.Siman.Services.KardexService;
import org.Almacen.Siman.Services.ProductoService;
import org.Almacen.Siman.Util.ItemKardexTemp;
import org.Almacen.Siman.Util.KardexTemp;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.LangUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Named("KardexBeans")
@ViewScoped
public class KardexBeans implements Serializable {

    @Inject
    private ProductoService productoService;

    @Inject
    private KardexService kardexService;

    private int idproducto;

    private boolean validarBtns;

    private StreamedContent file;

    private List<ProductoDescripcionDto> productoDescripcionDtos;

    private List<Date> fechasSeleccionadas;

    private List<ItemKardexTemp> itemKardexTemps;

    private List<ItemKardexTemp> itemKardexTempsSeleccion;

    private LocalDate startDate;

    private LocalDate endDate;

    @PostConstruct
    private void init() {
        fechasSeleccionadas = new ArrayList<>();
        itemKardexTemps = new ArrayList<>();
        loadProductoDescripcionDtos();
        validarBotones();
    }

    private void loadProductoDescripcionDtos() {
        this.productoDescripcionDtos = productoService.productoDescripcionDtos();
    }

    public void buscarKardex() {
        startDate = fechasSeleccionadas.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (fechasSeleccionadas.size() == 1) {
            endDate = fechasSeleccionadas.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            endDate = fechasSeleccionadas.get(fechasSeleccionadas.size() - 1).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        KardexTemp kardex = kardexService.generarKardex(idproducto, startDate, endDate);
        itemKardexTemps = kardex.getItems();
        PrimeFaces.current().ajax().update(":form-datos:tabla");
    }

    public void GenerarPDF() {

        try {
            // Fetching data
            KardexTemp kx = kardexService.generarKardex(idproducto, startDate, endDate);
            List<ItemKardexTemp> lst = kx.getItems();

            // Getting resources
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            InputStream reporteEntrada = servletContext.getResourceAsStream("/resources/reportes/Reporte_Kardex/Kardex.jasper");

            if (reporteEntrada != null) {
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(lst.toArray());
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("articulo", kx.getProducto());
                parameters.put("inv_inicial", kx.getInvInicial());
                parameters.put("unid_medida", kx.getUndMedida());
                parameters.put("periodo", kx.getPeriodo());

                JasperReport report = (JasperReport) JRLoader.loadObject(reporteEntrada);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                byte[] pdfBytes = outputStream.toByteArray();

                ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);

                file = DefaultStreamedContent.builder()
                        .name("Kardex.pdf")
                        .contentType("application/pdf")
                        .stream(() -> inputStream)
                        .build();

                inputStream.close();
                outputStream.close();
            } else {
                if (reporteEntrada == null) {
                    System.out.println("No se pudo encontrar el archivo del reporte.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        ItemKardexTemp c = (ItemKardexTemp) value;
        return (String.valueOf(c.getFecha()).toLowerCase().contains(filterText));
    }

    public void validarBotones() {
        if (fechasSeleccionadas.isEmpty() || idproducto == 0 || productoDescripcionDtos.isEmpty()) {
            validarBtns = true;
        } else {
            validarBtns = false;
        }
    }

}
