package org.Almacen.TopAlmacen.Controladores.RegistroEntrada;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.ListadoDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.Empresa.EmpresaDto;
import org.Almacen.TopAlmacen.DTO.PrecioPorTipoUnidad.PrecioPorTipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.CreateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Mappers.*;
import org.Almacen.TopAlmacen.Services.*;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;


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

    private ListadoDetalleProductoProveedorEntradaDto DPPE ;

    private EmpresaDto empresaDto;

    private ProductoDto productoDto;

    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;

    private String idEmpresa;

    private String fechaActual;

    private String idRegistroEntrada;

    private int idProducto;

    private int idTipoUnidad;

    private int idTempora;

    private double precioTotal;

    private boolean bloquearEmpesa;

    private boolean btnNuevoEntrada;

    private boolean bloquearProducto;

    private boolean bloquearTipoUnidad;

    private boolean bloquearDatosAgregar;

    private boolean btnBotonAgregrar;

    private boolean guardarLista;

    private boolean verDatosValidacion;

    private boolean observacion;

    private List<ProductoProveedorEntradaDto> productoProveedorEntradaDtos;

    private List<ProductoProveedorEntradaDto> productoProveedorEntradaDtosSeleccion;

    private List<EmpresaDto> empresaDtoList;

    private List<ProductoDescripcionDto> productoDescripcionDtoList;

    private List<TipoUnidadDto> tipoUnidadDtoList;

    private List<ListadoDetalleProductoProveedorEntradaDto> ListadoDetallesPPE;

    @PostConstruct
    private void init() {
        loadRegistrarEntrant();
        validarBtnnuevoEntrada();
    }

    // ================ methods de Invocation ===============
    //=======================================================
    public void NuevoRegister() {
        UsuarioDto usuarioDto = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.productoProveedorEntradaDto = new ProductoProveedorEntradaDto();
        this.empresaDto = new EmpresaDto();
        this.empresaDtoList = empresaService.getAllActiveEstado();
        this.productoDescripcionDtoList = productoService.getAllProductosDescripcipDto();
        this.productoProveedorEntradaDto.setOC(generarNumeroDeSeisCifras());
        this.productoProveedorEntradaDto.setUsuario(UsuarioMapper.toUsuario(usuarioDto));
        this.fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        limpiarNuevoRegistro();
        validacionNuevoProducto(1);
        verficiacionEmpresa(1);
        validarbtnlista(1);
        nuevoDetalle();
        limpiarDetalles();
        validarGuardado();
        validarVerDatos(1);
    }

    public void editarTablaDetalle(RowEditEvent<ListadoDetalleProductoProveedorEntradaDto> event) {
        int idtemp = Integer.parseInt(String.valueOf(event.getObject().getId()));
        for (ListadoDetalleProductoProveedorEntradaDto detalle : ListadoDetallesPPE) {
            if (detalle.getId() == idtemp) {
                detalle.setPrecioTotal(detalle.getCantidad() * detalle.getPrecioUniario());
                break;
            }
        }
        SumanTotal();
    }

    public void registrarProducto() {
        this.DPPE.setId(getNextId());
        this.DPPE.setProductoProveedorEntrada(ProductoProveedorEntradaMapper.toEntity(productoProveedorEntradaDto));
        this.DPPE.setPrecioPorTipoUnidad(PrecioPorTipoUnidadMapper.toEntity(precioPorTipoUnidadDto));
        this.DPPE.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(idTipoUnidad)));
        this.DPPE.setDescripcionProducto(ProductoMapper.toConcatProduct(precioPorTipoUnidadDto.getProducto()));
        this.DPPE.setPrecioTotal(this.DPPE.getPrecioUniario() * this.DPPE.getCantidad());
        this.ListadoDetallesPPE.add(DPPE);
        limpiarDetalles();
        SumanTotal();
        validarGuardado();
        PrimeFaces.current().executeScript("PF('dialogProducto').hide()");
    }

    public void eliminarTablaDetalle() {
        for (int i = 0; i < ListadoDetallesPPE.size(); i++) {
            if (ListadoDetallesPPE.get(i).getId() == idTempora) {
                ListadoDetallesPPE.remove(i);
                break;
            }
        }
        for (int i = 0; i < ListadoDetallesPPE.size(); i++) {
            ListadoDetallesPPE.get(i).setId(i + 1);
        }
        SumanTotal();
    }

    public void cargarPrecioPorTipoUnidad() {
        this.precioPorTipoUnidadDto = precioPorTipoUnidadService.getByIdProductoIdTipoUnidad(idProducto, idTipoUnidad);
        this.DPPE.setPrecioUniario(precioPorTipoUnidadDto.getPrecioUnitario());
        this.DPPE.setCantidad(1);
        validacionNuevoProducto(3);
    }

    public void cargarTipoUnidad() {
        this.tipoUnidadDtoList = tipoUnidadService.filterTipoUnidadListProducto(idProducto);
        validacionNuevoProducto(2);
    }

    public void CargarEmpresa() {
        this.empresaDto = empresaService.getEmpresa(idEmpresa);
        this.productoProveedorEntradaDto.setEmpresa(EmpresaMapper.toEntity(empresaDto));
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
                || (c.getUsuario().getNombres()+" "+c.getUsuario().getApellidos()).toLowerCase().contains(filterText)
                || String.valueOf(c.getPrecioFinal()).contains(filterText)
                || c.getEmpresa().getNombre().toLowerCase().contains(filterText)
                || (String.valueOf(c.getFechaRegistro())).contains(filterText);
    }

    public void AgregarNuevoDetalle() {
        limpiarDetalles();
    }

    public void guardar() {
        List<CreateDetalleProductoProveedorEntradaDto> lst = DetalleProductoProveedorEntradaMapper.toDtoCreate(ListadoDetallesPPE);
        CreateProductoProveedorEntradaDto dto = new CreateProductoProveedorEntradaDto();
        dto.setOC(productoProveedorEntradaDto.getOC());
        dto.setObservacion(productoProveedorEntradaDto.getObservacion());
        dto.setEmpresa(productoProveedorEntradaDto.getEmpresa());
        dto.setPrecioFinal(precioTotal);
        dto.setUsuario(productoProveedorEntradaDto.getUsuario());
        this.productoProveedorEntradaService.create(dto, lst);
        loadRegistrarEntrant();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡EL registro de entrada ha sido registrado exitosamente en el sistema!"));
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public void verDatos() {
        productoProveedorEntradaDto = productoProveedorEntradaService.findById(idRegistroEntrada);
        this.empresaDtoList = empresaService.getAllEmpresa();
        this.fechaActual = productoProveedorEntradaDto.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.idEmpresa = productoProveedorEntradaDto.getEmpresa().getNroRUC();
        empresaDto = EmpresaMapper.toDto(productoProveedorEntradaDto.getEmpresa());
        precioTotal = productoProveedorEntradaDto.getPrecioFinal();
        var detalles = detalleProductoProveedorEntradaService.getAllByProveedorEntradaId(productoProveedorEntradaDto.getOC());
        this.ListadoDetallesPPE = detalles.stream().map(DetalleProductoProveedorEntradaMapper::toDtoLista).collect(Collectors.toList());
        verficiacionEmpresa(2);
        validarVerDatos(2);
    }

    // ================ methods de Private =================
    //=======================================================
    private void loadRegistrarEntrant() {
        this.productoProveedorEntradaDtos = productoProveedorEntradaService.findAll();
    }

    private String generarNumeroDeSeisCifras() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int LENGTH = 6;
        Random random = new Random();
        StringBuilder codigo = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            codigo.append(CHARACTERS.charAt(index));
        }

        return codigo.toString();
    }

    private int getNextId() {
        int maxId = 0;
        for (ListadoDetalleProductoProveedorEntradaDto detalle : ListadoDetallesPPE) {
            if (detalle.getId() > maxId) {
                maxId = detalle.getId();
            }
        }
        return maxId + 1;
    }

        private void SumanTotal() {
            precioTotal = 0;
            for (ListadoDetalleProductoProveedorEntradaDto c : ListadoDetallesPPE) {
                precioTotal = c.getPrecioTotal() + precioTotal;
            }
        }

    // ================ methods de Limpid =================
    //=======================================================
    private void limpiarNuevoRegistro() {
        this.idEmpresa = "";
        this.idProducto = 0;
        this.idTipoUnidad = 0;
    }

    private void nuevoDetalle() {
        ListadoDetallesPPE = new ArrayList<>();
    }

    private void limpiarDetalles() {
        this.DPPE = new ListadoDetalleProductoProveedorEntradaDto();
        this.productoDto = new ProductoDto();
        this.productoDescripcionDtoList = productoService.getAllProductosDescripcipDto();
        validacionNuevoProducto(1);
        this.idProducto = 0;
        this.idTipoUnidad = 0;
        this.precioTotal = 0;
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    // ================ methods de Validation =================
    //=======================================================
    private void verficiacionEmpresa(int opcion) {
        switch (opcion) {
            case 1:
                this.bloquearEmpesa = false;
                break;
            case 2:
                this.bloquearEmpesa = true;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcion);
        }
    }

    private void validacionNuevoProducto(int opcion) {
        switch (opcion) {
            case 1:
                this.bloquearProducto = false;
                this.bloquearDatosAgregar = true;
                this.bloquearTipoUnidad = true;
                break;
            case 2:
                this.bloquearProducto = true;
                this.bloquearDatosAgregar = true;
                this.bloquearTipoUnidad = false;
                break;
            case 3:
                this.bloquearProducto = true;
                this.bloquearDatosAgregar = false;
                this.bloquearTipoUnidad = true;
        }
    }

    private void validarbtnlista(int opcion) {
        switch (opcion) {
            case 1:
                this.btnBotonAgregrar = true;
                this.observacion = true;
                break;
            case 2:
                this.btnBotonAgregrar = false;
                this.observacion = false;
                break;
        }
    }

    private void validarBtnnuevoEntrada() {
        List<PrecioPorTipoUnidadDto> lst = precioPorTipoUnidadService.getAllPrecioPorTipoUnidad();
        List<EmpresaDto> lstEmpresa = empresaService.getAllEmpresa();
        if(lstEmpresa.isEmpty() || lst.isEmpty()) {
            btnNuevoEntrada = true;
        }else{
            btnNuevoEntrada = false;
        }
    }

    private void validarGuardado() {
      if (this.ListadoDetallesPPE.isEmpty()) {
            this.guardarLista = true;
        } else {
            this.guardarLista = false;
        }
    }

    private void validarVerDatos(int opcion) {
        switch (opcion) {
            case 1:
                verDatosValidacion = true;
                observacion = false;
                break;
            case 2:
                verDatosValidacion = false;
                observacion = true;
                break;
        }
    }

}

