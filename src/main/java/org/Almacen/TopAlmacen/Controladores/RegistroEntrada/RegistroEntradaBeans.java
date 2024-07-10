package org.Almacen.TopAlmacen.Controladores.RegistroEntrada;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.CreateDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.ListaDetalleProductoProveedorEntradaDto;
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

    private CreateProductoProveedorEntradaDto productoProveedorEntradaDto;

    private EmpresaDto empresaDto;

    private ProductoDto productoDto;

    private PrecioPorTipoUnidadDto precioPorTipoUnidadDto;

    private ListaDetalleProductoProveedorEntradaDto detalleProductoProveedorEntradaDto;

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

    private List<ListaDetalleProductoProveedorEntradaDto> ListadoDeDetalle;

    @PostConstruct
    private void init() {
        loadRegistrarEntrant();
        validarBtnnuevoEntrada();
    }

    // ================ methods de Invocation ===============
    //=======================================================
    public void NuevoRegister() {
        this.productoProveedorEntradaDto = new CreateProductoProveedorEntradaDto();
        this.empresaDto = new EmpresaDto();
        this.detalleProductoProveedorEntradaDto = new ListaDetalleProductoProveedorEntradaDto();
        this.empresaDtoList = empresaService.getAllEmpresa();
        this.productoDescripcionDtoList = productoService.productoDescripcionDtos();
        this.productoProveedorEntradaDto.setOC(generarNumeroDeSeisCifras());
        UsuarioDto usuarioDto = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.productoProveedorEntradaDto.setUsuario(UsuarioMapper.toUsuario(usuarioDto));
        fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        limpiarNuevoRegistro();
        validacionNuevoProducto(1);
        verficiacionEmpresa(1);
        validarbtnlista(1);
        nuevoDetalle();
        limpiarDetalles();
        validarGuardado();
        validarVerDatos(1);
    }

    public void editarTablaDetalle(RowEditEvent<ListaDetalleProductoProveedorEntradaDto> event) {
        int idtemp = Integer.parseInt(String.valueOf(event.getObject().getId()));
        for (ListaDetalleProductoProveedorEntradaDto detalle : ListadoDeDetalle) {
            if (detalle.getId() == idtemp) {
                detalle.setPrecioTotal(detalle.getCantidad() * detalle.getPrecioUnitario());
                break;
            }
        }
        SumanTotal();
    }

    public void registrarProducto() {
        this.detalleProductoProveedorEntradaDto.setId(getNextId());
        this.detalleProductoProveedorEntradaDto.setOC_id(ProductoProveedorEntradaMapper.toEntityCreate(productoProveedorEntradaDto));
        this.detalleProductoProveedorEntradaDto.setPrecioPorTipoUnidad(PrecioPorTipoUnidadMapper.toEntity(precioPorTipoUnidadDto));
        this.detalleProductoProveedorEntradaDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(idTipoUnidad)));
        this.detalleProductoProveedorEntradaDto.setPrecioTotal(detalleProductoProveedorEntradaDto.getPrecioUnitario() * detalleProductoProveedorEntradaDto.getCantidad());
        this.detalleProductoProveedorEntradaDto.setDescripcion(ProductoMapper.toConcatProduct(precioPorTipoUnidadDto.getProducto()));
        ListadoDeDetalle.add(detalleProductoProveedorEntradaDto);
        limpiarDetalles();
        SumanTotal();
        validarGuardado();
        PrimeFaces.current().executeScript("PF('dialogProducto').hide()");
    }

    public void eliminarTablaDetalle() {
        for (int i = 0; i < ListadoDeDetalle.size(); i++) {
            if (ListadoDeDetalle.get(i).getId() == idTempora) {
                ListadoDeDetalle.remove(i);
                break;
            }
        }
        for (int i = 0; i < ListadoDeDetalle.size(); i++) {
            ListadoDeDetalle.get(i).setId(i + 1);
        }
        SumanTotal();
    }

    public void cargarPrecioPorTipoUnidad() {
        this.precioPorTipoUnidadDto = precioPorTipoUnidadService.getByIdProductoIdTipoUnidad(idProducto, idTipoUnidad);
        this.detalleProductoProveedorEntradaDto.setPrecioUnitario(precioPorTipoUnidadDto.getPrecioUnitario());
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
                || c.getEmpresa().getNombre().toLowerCase().contains(filterText)
                || (String.valueOf(c.getFechaRegistro())).contains(filterText);
    }

    public void AgregarNuevoDetalle() {
        limpiarDetalles();
    }

    public void guardar() {
        this.productoProveedorEntradaDto.setPrecioFinal(precioTotal);
        List<CreateDetalleProductoProveedorEntradaDto> lst = DetalleProductoProveedorEntradaMapper.toDtoCreate(ListadoDeDetalle);
        this.productoProveedorEntradaService.create(productoProveedorEntradaDto, lst);
        loadRegistrarEntrant();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");

    }

    public void verDatos() {
        ProductoProveedorEntradaDto dto = productoProveedorEntradaService.findById(idRegistroEntrada);
        this.productoProveedorEntradaDto = new CreateProductoProveedorEntradaDto();
        this.empresaDtoList = empresaService.getAllEmpresa();
        this.productoProveedorEntradaDto.setOC(dto.getOC());
        this.fechaActual = dto.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.idEmpresa = dto.getEmpresa().getNroRUC();
        this.productoProveedorEntradaDto.setObservacion(dto.getObservacion());
        empresaDto = EmpresaMapper.toDto(dto.getEmpresa());
        precioTotal = dto.getPrecioFinal();
        var detalles = detalleProductoProveedorEntradaService.getAllByProveedorEntradaId(dto.getOC());
        this.ListadoDeDetalle = detalles.stream().map(DetalleProductoProveedorEntradaMapper::toDtoLista).collect(Collectors.toList());
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
        for (ListaDetalleProductoProveedorEntradaDto detalle : ListadoDeDetalle) {
            if (detalle.getId() > maxId) {
                maxId = detalle.getId();
            }
        }
        return maxId + 1;
    }

    private void SumanTotal() {
        precioTotal = 0;
        for (ListaDetalleProductoProveedorEntradaDto c : ListadoDeDetalle) {
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
        ListadoDeDetalle = new ArrayList<>();
    }

    private void limpiarDetalles() {
        this.detalleProductoProveedorEntradaDto = new ListaDetalleProductoProveedorEntradaDto();
        this.productoDto = new ProductoDto();
        this.productoDescripcionDtoList = productoService.productoDescripcionDtos();
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
                break;
            case 2:
                this.btnBotonAgregrar = false;
                break;
        }
    }

    private void validarBtnnuevoEntrada() {
        List<PrecioPorTipoUnidadDto> lst = precioPorTipoUnidadService.getAllPrecioPorTipoUnidad();
        this.btnNuevoEntrada = lst.isEmpty();
    }

    private void validarGuardado() {
        if (this.ListadoDeDetalle.isEmpty()) {
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

