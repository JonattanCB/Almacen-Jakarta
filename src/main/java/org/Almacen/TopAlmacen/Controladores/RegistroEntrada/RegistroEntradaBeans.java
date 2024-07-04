package org.Almacen.TopAlmacen.Controladores.RegistroEntrada;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.DetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.Empresa.EmpresaDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Mappers.TipoUnidadMapper;
import org.Almacen.TopAlmacen.Services.*;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@Named("RegistroEntradaBeans")
@ViewScoped
public class RegistroEntradaBeans implements Serializable {

    @Inject
    private ProductoProveedorEntradaService productoProveedorEntradaService;

    @Inject
    private DetalleProductoProveedorEntradaService detalleProductoProveedorEntradaService;

    @Inject
    private EmpresaService empresaService;

    @Inject
    private ProductoService productoService;

    @Inject
    private TipoUnidadService tipoUnidadService;

    @Inject
    private PrecioPorTipoUnidadService precioPorTipoUnidadService;

    private ProductoProveedorEntradaDto productoProveedorEntradaDto;

    private EmpresaDto empresaDto;

    private ProductoDto productoDto;

    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;

    private DetalleProductoProveedorEntradaDto detalleProductoProveedorEntradaDto;

    private String idEmpresa;

    private int idProducto;

    private int idTipoUnidad;

    private boolean bloquearEmpesa;

    private boolean btnNuevoEntrada;

    private boolean bloquearProducto;

    private boolean bloquearTipoUnidad;

    private boolean bloquearDatosAgregar;

    private List<ProductoProveedorEntradaDto> productoProveedorEntradaDtos;

    private List<ProductoProveedorEntradaDto> productoProveedorEntradaDtosSeleccion;

    private List<EmpresaDto> empresaDtoList;

    private List<ProductoDescripcionDto> productoDescripcionDtoList;

    private List<TipoUnidadDto> tipoUnidadDtoList;

    private List<DetalleProductoProveedorEntradaDto> ListadoDeDetalle;



    @PostConstruct
    private void init(){
        loadRegitroEntrada();
        validarBtnnuevoEntrada();
    }

    private void loadRegitroEntrada(){
        try {
            productoProveedorEntradaDtos = productoProveedorEntradaService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void NuevoRegistro(){
        productoProveedorEntradaDto = new ProductoProveedorEntradaDto();
        detalleProductoProveedorEntradaDto = new DetalleProductoProveedorEntradaDto();
        empresaDto = new EmpresaDto();
        empresaDtoList = empresaService.getAllEmpresa();
        productoDescripcionDtoList = productoService.productoDescripcionDtos();
        idEmpresa = "";
        idProducto = 0;
        idTipoUnidad = 0;
        validacionNuevoProducto(1);
        verficiacionEmpresa(1);
        nuevoDetalle();
        irAgregardetalle();
    }

    public  void verDatosRegistroEntrada(){

    }

    public void eliminarRegistroEntrada(){

    }

    public void CargarEmpresa(){
        empresaDto = empresaService.getEmpresa(idEmpresa);
        verficiacionEmpresa(2);
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        ProductoProveedorEntradaDto c = (ProductoProveedorEntradaDto) value;
        return (c.getOC() >= filterInt && c.getOC() <= filterInt)
                || c.getEmpresa().getNombre().toLowerCase().contains(filterText)
                || c.getFechaRegistro().equals(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void verficiacionEmpresa(int opcion){
        switch (opcion){
            case 1:
                bloquearEmpesa = false;
                break;
            case 2:
                bloquearEmpesa = true;
                break;
        }
    }

    private void validacionNuevoProducto(int opcion){
        switch (opcion){
            case 1:
                bloquearProducto = false;
                bloquearDatosAgregar = true;
                bloquearTipoUnidad = true;
                break;
            case 2:
                bloquearProducto = true;
                bloquearDatosAgregar = true;
                bloquearTipoUnidad = false;
                break;
            case 3:
                bloquearProducto = true;
                bloquearDatosAgregar = false;
                bloquearTipoUnidad = true;
        }
    }

     private void irAgregardetalle(){
        detalleProductoProveedorEntradaDto = new DetalleProductoProveedorEntradaDto();
        productoDto = new ProductoDto();
        validacionNuevoProducto(1);
        productoDescripcionDtoList = productoService.productoDescripcionDtos();
        idProducto = 0 ;
        idTipoUnidad = 0;
    }

    public void cargarTipoUnidad(){
        tipoUnidadDtoList = tipoUnidadService.filterTipoUnidadListProducto(idProducto);
        validacionNuevoProducto(2);
    }

    public void cargarPrecioPorTipoUnidad(){
        precioPorTipoUnidadDto = precioPorTipoUnidadService.getByIdProductoIdTipoUnidad(idProducto,idTipoUnidad);
        detalleProductoProveedorEntradaDto.setPrecioUnitario(precioPorTipoUnidadDto.getPrecioUnitario());
        validacionNuevoProducto(3);
    }

    public void registrarProducto(){
        boolean productoExistente = false;
        for (DetalleProductoProveedorEntradaDto detalle : ListadoDeDetalle) {
            if (detalle.getDescripcion().equals(ProductoMapper.toConcatProduct(ProductoMapper.toProducto(productoService.getProductoById(idProducto)))) && detalle.getTipoUnidad().getId()== idTipoUnidad) {
                detalle.setPrecioUnitario(detalleProductoProveedorEntradaDto.getPrecioUnitario());
                detalle.setCantidad(detalle.getCantidad() + detalleProductoProveedorEntradaDto.getCantidad());
                productoExistente = true;
                irAgregardetalle();
                break;
            }
        }
        if (!productoExistente) {
            detalleProductoProveedorEntradaDto.setId(getNextId());
            detalleProductoProveedorEntradaDto.setDescripcion(ProductoMapper.toConcatProduct(ProductoMapper.toProducto(productoService.getProductoById(idProducto))));
            detalleProductoProveedorEntradaDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(idTipoUnidad)));
            ListadoDeDetalle.add(detalleProductoProveedorEntradaDto);
            irAgregardetalle();
        }
    }

    public  void nuevoDetalle(){
        ListadoDeDetalle = new ArrayList<>();

    }

    private int getNextId() {
        int maxId = 0;
        for (DetalleProductoProveedorEntradaDto detalle : ListadoDeDetalle) {
            if (detalle.getId() > maxId) {
                maxId = detalle.getId();
            }
        }
        return maxId + 1;
    }

    private void validarBtnnuevoEntrada(){
        List<PrecioPorTipoUnidadDto> lst = precioPorTipoUnidadService.getAllPrecioPorTipoUnidad();
        if (lst.isEmpty()){
            btnNuevoEntrada = true;
        }else{
            btnNuevoEntrada=false;
        }
    }


}

