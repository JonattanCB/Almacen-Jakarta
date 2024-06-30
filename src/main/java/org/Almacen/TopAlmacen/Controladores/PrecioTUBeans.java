package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.CreatePrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Mappers.TipoUnidadMapper;
import org.Almacen.TopAlmacen.Model.Producto;
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

    private List<PrecioPorTipoUnidadDto> precioPorTipoUnidadlst;

    private List<PrecioPorTipoUnidadDto> precioPorTipoUnidadlstSeleccionado;

    private List<TipoUnidadDto> tipoUnidadDtoList;

    private List<ProductoDescripcionDto> productoDescripcionDtos;

    @PostConstruct
    private void  init(){
        loadPrecioPorTipoUnidad();
    }

    public  void newPrecioPorTipoUnidad(){
        precioPorTipoUnidadDto = new PrecioPorTipoUnidadDto();
        tipoUnidadDtoList = tipoUnidadService.getAllTipoUnidad();
        productoDescripcionDtos = null; // me falta traer el produto del dto
        tipoUnidadID = 0;
        productoId = 0;
    }

    public void determinarPrecioPorTipoUnidad(){
        if (precioPorTipoUnidadDto.getId() == 0){
            createPrecioPorTipoUnidad();
        }else {
            updatePrecioPorTipoUnidad();
        }
        loadPrecioPorTipoUnidad();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public void CargarPrecioPorTipoUnidadUpdate(){

    }

    public void deletePrecioPorTipoUnidad(){

    }

    private void createPrecioPorTipoUnidad(){
        CreatePrecioPorTipoUnidadDto createPrecioPorTipoUnidadDto = new CreatePrecioPorTipoUnidadDto();
        createPrecioPorTipoUnidadDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(tipoUnidadID)));
        createPrecioPorTipoUnidadDto.setProducto(ProductoMapper.toProducto(productoService.getProductoById(productoId)));
        createPrecioPorTipoUnidadDto.setPrecio(precioPorTipoUnidadDto.getPrecioUnitario());
        createPrecioPorTipoUnidadDto.setUnidadesPorTipoUnidadPorProducto(precioPorTipoUnidadDto.getUnidadesPorTipoUnidadPorProducto());
        // preguntar:    createPrecioPorTipoUnidadDto.setStockUnidades();
    }

    private void updatePrecioPorTipoUnidad(){

    }

    private  void loadPrecioPorTipoUnidad(){
        try{
            precioPorTipoUnidadlst = precioPorTipoUnidadService.getAllPrecioPorTipoUnidad();
        }catch (Exception e){
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
                ||(c.getTipoUnidad().getNombre()).toLowerCase().contains(filterText)
                || (c.getProducto().getNombre()).toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
