package org.Almacen.TopAlmacen.Controladores.PrecioTU;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.CreatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.CreateStockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.StockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.Mappers.PrecioPorTipoUnidadMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Mappers.TipoUnidadMapper;
import org.Almacen.TopAlmacen.Model.StockUnidades;
import org.Almacen.TopAlmacen.Services.PrecioPorTipoUnidadService;
import org.Almacen.TopAlmacen.Services.ProductoService;
import org.Almacen.TopAlmacen.Services.StockUnidadesService;
import org.Almacen.TopAlmacen.Services.TipoUnidadService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("PrecioTUBeans")
@ViewScoped
public class PrecioTUBeans implements Serializable {

    @Inject
    private PrecioPorTipoUnidadService precioPorTipoUnidadService;

    @Inject
    private TipoUnidadService tipoUnidadService;

    @Inject
    private ProductoService productoService;
    @Inject
    private StockUnidadesService stockUnidadesService;

    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;

    private StockUnidades stockUnidades;

    private int precioPorTipoUnidadID;

    private int tipoUnidadID;

    private int productoId;

    private int stockUnidadId;

    private boolean btnNuevoPrecio;

    private boolean unidad;

    private boolean btnGuardar;

    private boolean productoDesactivado;

    private boolean escribirdatos;

    private boolean preciodatos;

    private List<PrecioPorTipoUnidadDto> precioPorTipoUnidadlst;

    private List<PrecioPorTipoUnidadDto> precioPorTipoUnidadlstSeleccionado;

    private List<TipoUnidadDto> tipoUnidadDtoList;

    private List<ProductoDescripcionDto> productoDescripcionDtos;

    @PostConstruct
    private void init() {
        loadPrecioPorTipoUnidad();
        verificarExistenciProducto();
    }

    public void newPrecioPorTipoUnidad() {
        precioPorTipoUnidadDto = new PrecioPorTipoUnidadDto();
        productoDescripcionDtos = productoService.productoDescripcionDtos();
        tipoUnidadDtoList = tipoUnidadService.getAllTipoUnidad();
        tipoUnidadID = 0;
        productoId = 0;
        disabledPrecio(1);
    }

    public void determinarPrecioPorTipoUnidad() {
        System.out.println("entro aca");
        if (precioPorTipoUnidadDto.getId() == 0) {
            createPrecioPorTipoUnidad();
        } else {
            updatePrecioPorTipoUnidad();
        }
    }

    private void CargarPrecioPorTipoUnidad() {
        precioPorTipoUnidadDto = precioPorTipoUnidadService.getPrecioPorTipoUnidadById(precioPorTipoUnidadID);
        tipoUnidadDtoList = tipoUnidadService.getAllTipoUnidad();
        productoDescripcionDtos = productoService.productoDescripcionDtos();
        tipoUnidadID = precioPorTipoUnidadDto.getTipoUnidad().getId();
        stockUnidades = precioPorTipoUnidadDto.getStockUnidades();
        productoId = precioPorTipoUnidadDto.getProducto().getId();
    }


    public void seleccionaProducto() {
        tipoUnidadDtoList = tipoUnidadService.filterTipoUnidadList(productoId);
        disabledPrecio(4);
    }

    public void CargarPrecioPorTipoUnidadVer() {
        CargarPrecioPorTipoUnidad();
        disabledPrecio(3);
    }

    public void CargarPrecioPorTipoUnidadUpdate() {
        CargarPrecioPorTipoUnidad();
        disabledPrecio(2);
    }

    public void deletePrecioPorTipoUnidad() {
        PrecioPorTipoUnidadDto dto = precioPorTipoUnidadService.getPrecioPorTipoUnidadById(precioPorTipoUnidadID);
        if (precioPorTipoUnidadService.delete(precioPorTipoUnidadID) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puede eliminar el precio del producto " + dto.getProducto().getNombre() + " porque se está utilizando en el sistema."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El precio del producto " + dto.getProducto().getNombre() + " ha sido eliminado exitosamente del sistema!"));
        }
        loadPrecioPorTipoUnidad();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private void createPrecioPorTipoUnidad() {
        CreatePrecioPorTipoUnidadDto createPrecioPorTipoUnidadDto = new CreatePrecioPorTipoUnidadDto();
        createPrecioPorTipoUnidadDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(tipoUnidadID)));
        createPrecioPorTipoUnidadDto.setProducto(ProductoMapper.toProducto(productoService.getProductoById(productoId)));
        createPrecioPorTipoUnidadDto.setPrecio(precioPorTipoUnidadDto.getPrecioUnitario());
        createPrecioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(precioPorTipoUnidadDto.getUnidadesPorTipoUnidadPorProducto());
        if (createPrecioPorTipoUnidadDto.getTipoUnidad().getAbrev().equals("UND")) {
            precioPorTipoUnidadService.CrearUnidadBasica(createPrecioPorTipoUnidadDto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El precio del producto " + createPrecioPorTipoUnidadDto.getProducto().getNombre() + " ha sido registrado exitosamente en el sistema!"));
            loadPrecioPorTipoUnidad();
            PrimeFaces.current().executeScript("PF('dialogsa').hide()");
            PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
        } else {
            createPrecioPorTipoUnidadDto.setStockUnidades(precioPorTipoUnidadService.getByIdProducto(productoId).getStockUnidades());
            if (precioPorTipoUnidadService.crearProductoConUnidadSuperior(createPrecioPorTipoUnidadDto) == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puede registrar el precio del producto " + createPrecioPorTipoUnidadDto.getProducto().getNombre() + " sin tener registrada una unidad de ese producto."));
                PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
            } else {
                loadPrecioPorTipoUnidad();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El precio del producto " + createPrecioPorTipoUnidadDto.getProducto().getNombre() + " ha sido registrado exitosamente en el sistema!"));
                PrimeFaces.current().executeScript("PF('dialogsa').hide()");
                PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
            }
        }


    }

    private void updatePrecioPorTipoUnidad() {
        UpdatePrecioPorTipoUnidadDto updatePrecioPorTipoUnidadDto = new UpdatePrecioPorTipoUnidadDto();
        updatePrecioPorTipoUnidadDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(tipoUnidadID)));
        updatePrecioPorTipoUnidadDto.setProducto(ProductoMapper.toProducto(productoService.getProductoById(productoId)));
        updatePrecioPorTipoUnidadDto.setPrecio(precioPorTipoUnidadDto.getPrecioUnitario());
        updatePrecioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(precioPorTipoUnidadDto.getUnidadesPorTipoUnidadPorProducto());
        precioPorTipoUnidadService.update(updatePrecioPorTipoUnidadDto, precioPorTipoUnidadID);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El precio del producto " + updatePrecioPorTipoUnidadDto.getProducto().getNombre() + " ha sido actualizado exitosamente en el sistema!"));
        loadPrecioPorTipoUnidad();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private void loadPrecioPorTipoUnidad() {
        try {
            precioPorTipoUnidadlst = precioPorTipoUnidadService.getAllPrecioPorTipoUnidad();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        PrecioPorTipoUnidadDto c = (PrecioPorTipoUnidadDto) value;
        return (c.getId() >= filterInt && c.getId() <= filterInt)
                || c.getTipoUnidad().getNombre().toLowerCase().contains(filterText)
                || c.getProducto().getNombre().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void verificarUnidad() {
        TipoUnidadDto tu = tipoUnidadService.getTipoUnidad(tipoUnidadID);
        switch (tu.getAbrev()) {
            case "UND":
                precioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(1);
                unidad = true;
                break;
            case "DOC":
                precioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(12);
                unidad = true;
                break;
            case "DEC":
                precioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(10);
                unidad = true;
                break;
            case "CEN":
                precioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(100);
                unidad = true;
                break;
            case "MIL":
                precioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(1000);
                unidad = true;
                break;
            default:
                unidad = false;
        }
    }

    private void disabledPrecio(int opcion) {
        switch (opcion) {
            case 1:
                unidad = true;
                productoDesactivado = false;
                escribirdatos = true;
                preciodatos = true;
                btnGuardar = true;
                break;
            case 2:
                unidad = true;
                escribirdatos = true;
                preciodatos = false;
                btnGuardar = true;
                productoDesactivado = true;
                break;
            case 3:
                unidad = true;
                escribirdatos = true;
                preciodatos = true;
                btnGuardar = false;
                productoDesactivado = true;
                break;

            case 4:
                unidad = false;
                productoDesactivado = true;
                escribirdatos = false;
                preciodatos = false;
                btnGuardar = true;
                break;
        }
    }

    private void verificarExistenciProducto() {
        List<ProductoDto> dtoList = productoService.getAllProducto();
        if (dtoList.isEmpty()) {
            btnNuevoPrecio = true;
        } else {
            btnNuevoPrecio = false;
        }

    }

    // ================ methods de Invocation ===============
    //=======================================================


    // ================ methods de Private =================
    //=======================================================


    // ================ methods de Validation =================
    //=======================================================


}
