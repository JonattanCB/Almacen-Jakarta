package org.Almacen.TopAlmacen.Controladores.RegistroEntrada;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.Controladores.Empresa.EmpresaBeans;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.DetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.Empresa.EmpresaDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.CreateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Mappers.*;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;
import org.Almacen.TopAlmacen.Services.*;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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

    private CreateProductoProveedorEntradaDto productoProveedorEntradaDto;

    private EmpresaDto empresaDto;

    private ProductoDto productoDto;

    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;

    private CreateDetalleProductoProveedorEntradaDto detalleProductoProveedorEntradaDto;

    private String idEmpresa;

    private int idProducto;

    private int idTipoUnidad;

    private int idTempora;

    private boolean bloquearEmpesa;

    private boolean btnNuevoEntrada;

    private boolean bloquearProducto;

    private boolean bloquearTipoUnidad;

    private boolean bloquearDatosAgregar;

    private boolean btnBotonAgregrar;

    private List<ProductoProveedorEntradaDto> productoProveedorEntradaDtos;

    private List<ProductoProveedorEntradaDto> productoProveedorEntradaDtosSeleccion;

    private List<EmpresaDto> empresaDtoList;

    private List<ProductoDescripcionDto> productoDescripcionDtoList;

    private List<TipoUnidadDto> tipoUnidadDtoList;

    private List<CreateDetalleProductoProveedorEntradaDto> ListadoDeDetalle;



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
        productoProveedorEntradaDto = new CreateProductoProveedorEntradaDto();
        detalleProductoProveedorEntradaDto = new CreateDetalleProductoProveedorEntradaDto();
        empresaDto = new EmpresaDto();
        empresaDtoList = empresaService.getAllEmpresa();
        productoDescripcionDtoList = productoService.productoDescripcionDtos();
        productoProveedorEntradaDto.setOC(generateRandomString(6));
        productoProveedorEntradaDto.setFechaRegistro(LocalDateTime.now());
        UsuarioDto usuarioDto = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        productoProveedorEntradaDto.setUsuario(UsuarioMapper.toUsuario(usuarioDto));
        idEmpresa = "";
        idProducto = 0;
        idTipoUnidad = 0;
        validacionNuevoProducto(1);
        verficiacionEmpresa(1);
        validarbtnlista(1);
        nuevoDetalle();
        irAgregardetalle();
    }

    public  void verDatosRegistroEntrada(){

    }

    public void guardar(){
        productoProveedorEntradaService.create(productoProveedorEntradaDto, ListadoDeDetalle);
        loadRegitroEntrada();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public void eliminarRegistroEntrada(){

    }

    public void CargarEmpresa(){
        empresaDto = empresaService.getEmpresa(idEmpresa);
        productoProveedorEntradaDto.setEmpresa(EmpresaMapper.toEntity(empresaDto));
        verficiacionEmpresa(2);
        validarbtnlista(2);
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        ProductoProveedorEntradaDto c = (ProductoProveedorEntradaDto) value;
        return c.getOC().toLowerCase().contains(filterText)
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
        detalleProductoProveedorEntradaDto = new CreateDetalleProductoProveedorEntradaDto();
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
        for (CreateDetalleProductoProveedorEntradaDto detalle : ListadoDeDetalle) {
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
            detalleProductoProveedorEntradaDto.setOC_id(ProductoProveedorEntradaMapper.toEntity(productoProveedorEntradaDto));
            detalleProductoProveedorEntradaDto.setDescripcion(ProductoMapper.toConcatProduct(ProductoMapper.toProducto(productoService.getProductoById(idProducto))));
            detalleProductoProveedorEntradaDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(idTipoUnidad)));
            ListadoDeDetalle.add(detalleProductoProveedorEntradaDto);
            irAgregardetalle();
        }
    }



    public  void nuevoDetalle(){
        ListadoDeDetalle = new ArrayList<>();

    }

    public void eliminarTablaDetalle(){
        for (int i = 0; i < ListadoDeDetalle.size(); i++) {
            if (ListadoDeDetalle.get(i).getId() == idTempora) {
                ListadoDeDetalle.remove(i);
                break;
            }
        }
        for (int i = 0; i < ListadoDeDetalle.size(); i++) {
            ListadoDeDetalle.get(i).setId(i + 1);
        }
    }

    private int getNextId() {
        int maxId = 0;
        for (CreateDetalleProductoProveedorEntradaDto detalle : ListadoDeDetalle) {
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

    private void validarbtnlista(int opcion){
        switch (opcion){
            case 1:
                btnBotonAgregrar = true;
                break;
            case 2:
                btnBotonAgregrar = false;
                break;
        }
    }

    public static String generateRandomString(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

}

