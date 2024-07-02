package org.Almacen.TopAlmacen.Controladores.PrecioTU;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.CreatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.UpdatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.Mappers.PrecioPorTipoUnidadMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Mappers.TipoUnidadMapper;
import org.Almacen.TopAlmacen.Services.PrecioPorTipoUnidadService;
import org.Almacen.TopAlmacen.Services.ProductoService;
import org.Almacen.TopAlmacen.Services.TipoUnidadService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("PrecioTUBeans")
@SessionScoped
public class PrecioTUBeans implements Serializable {

    @Inject
    private PrecioPorTipoUnidadService precioPorTipoUnidadService;

    @Inject
    private TipoUnidadService tipoUnidadService;

    @Inject
    private ProductoService productoService;

    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;

    private int precioPorTipoUnidadID;

    private int tipoUnidadID;

    private int productoId;

    private boolean unidad;

    private boolean btnGuardar;

    private boolean escribirdatos;

    private boolean preciodatos;

    private List<PrecioPorTipoUnidadDto> precioPorTipoUnidadlst;

    private List<PrecioPorTipoUnidadDto> precioPorTipoUnidadlstSeleccionado;

    private List<TipoUnidadDto> tipoUnidadDtoList;

    private List<ProductoDescripcionDto> productoDescripcionDtos;

    @PostConstruct
    private void init() {
        loadPrecioPorTipoUnidad();
    }

    public void newPrecioPorTipoUnidad() {
        precioPorTipoUnidadDto = new PrecioPorTipoUnidadDto();
        tipoUnidadDtoList = tipoUnidadService.getAllTipoUnidad();
        productoDescripcionDtos = productoService.productoDescripcionDtos();
        tipoUnidadID = 0;
        productoId = 0;
        disabledPrecio(1);
    }

    public void determinarPrecioPorTipoUnidad() {
        if (precioPorTipoUnidadDto.getId() == 0) {
            createPrecioPorTipoUnidad();
        } else {
            updatePrecioPorTipoUnidad();
        }
        loadPrecioPorTipoUnidad();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private void CargarPrecioPorTipoUnidad() {
        precioPorTipoUnidadDto = precioPorTipoUnidadService.getPrecioPorTipoUnidadById(precioPorTipoUnidadID);
        tipoUnidadDtoList = tipoUnidadService.getAllTipoUnidad();
        productoDescripcionDtos = productoService.productoDescripcionDtos();
        tipoUnidadID = precioPorTipoUnidadDto.getTipoUnidad().getId();
        productoId = precioPorTipoUnidadDto.getProducto().getId();
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
        PrecioPorTipoUnidadDto dto = PrecioPorTipoUnidadMapper.toDto(precioPorTipoUnidadService.delete(precioPorTipoUnidadID));
        loadPrecioPorTipoUnidad();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El precio del producto " + dto.getProducto().getNombre() + " ha sido eliminado exitosamente del sistema!"));
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private void createPrecioPorTipoUnidad() {
        CreatePrecioPorTipoUnidadDto createPrecioPorTipoUnidadDto = new CreatePrecioPorTipoUnidadDto();
        createPrecioPorTipoUnidadDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(tipoUnidadID)));
        createPrecioPorTipoUnidadDto.setProducto(ProductoMapper.toProducto(productoService.getProductoById(productoId)));
        createPrecioPorTipoUnidadDto.setPrecio(precioPorTipoUnidadDto.getPrecioUnitario());
        createPrecioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(precioPorTipoUnidadDto.getUnidadesPorTipoUnidadPorProducto());
        if (createPrecioPorTipoUnidadDto.getTipoUnidad().getAbrev().equals("UND")){
            precioPorTipoUnidadService.CrearUnidadBasica(createPrecioPorTipoUnidadDto);
        }else{
           precioPorTipoUnidadService.crearProductoConUnidadSuperior(createPrecioPorTipoUnidadDto);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El precio del producto " + createPrecioPorTipoUnidadDto.getProducto().getNombre() + " ha sido registrado exitosamente en el sistema!"));
    }

    private void updatePrecioPorTipoUnidad() {
        UpdatePrecioPorTipoUnidadDto updatePrecioPorTipoUnidadDto = new UpdatePrecioPorTipoUnidadDto();
        updatePrecioPorTipoUnidadDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(tipoUnidadID)));
        updatePrecioPorTipoUnidadDto.setProducto(ProductoMapper.toProducto(productoService.getProductoById(productoId)));
        updatePrecioPorTipoUnidadDto.setPrecio(precioPorTipoUnidadDto.getPrecioUnitario());
        updatePrecioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(precioPorTipoUnidadDto.getUnidadesPorTipoUnidadPorProducto());
        precioPorTipoUnidadService.update(updatePrecioPorTipoUnidadDto, precioPorTipoUnidadID);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El precio del producto " + updatePrecioPorTipoUnidadDto.getProducto().getNombre() + " ha sido actualizado exitosamente en el sistema!"));
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

    public void verificarUnidad(){
        TipoUnidadDto tu = tipoUnidadService.getTipoUnidad(tipoUnidadID);
        if (tu.getAbrev().equals("UND")){
            precioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(1);
            unidad = true;
        }else{
            unidad = false;
        }
    }

    private void disabledPrecio(int opcion){
        switch (opcion){
            case 1:
                unidad = false;
                escribirdatos = false;
                preciodatos =false;
                btnGuardar = true;
                break;
            case 2:
                unidad= true;
                escribirdatos = true;
                preciodatos = false;
                btnGuardar =true;
                break;
            case 3:
                unidad = true;
                escribirdatos = true;
                preciodatos =true;
                btnGuardar =false;
                break;
        }
    }

}
