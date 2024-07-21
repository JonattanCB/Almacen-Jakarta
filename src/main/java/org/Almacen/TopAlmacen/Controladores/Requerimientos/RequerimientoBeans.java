package org.Almacen.TopAlmacen.Controladores.Requerimientos;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.CreateItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.ItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.CreateRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Mappers.*;
import org.Almacen.TopAlmacen.Services.EmpresaService;
import org.Almacen.TopAlmacen.Services.ProductoService;
import org.Almacen.TopAlmacen.Services.RequerimientoService;
import org.Almacen.TopAlmacen.Services.TipoUnidadService;
import org.Almacen.TopAlmacen.Util.CodeGenerator;
import org.primefaces.PrimeFaces;
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
@Named("RequerimientoBeans")
@ViewScoped
public class RequerimientoBeans implements Serializable {

    @Inject
    private RequerimientoService requerimientoService;

    @Inject
    private EmpresaService empresaService;

    @Inject
    private ProductoService productoService;

    @Inject
    private TipoUnidadService tipoUnidadService;

    private RequerimientoDto requerimientoDto;

    private ItemsRequerimientoDto itemsRequerimientoDto;

    private UsuarioDto usuarioDto;

    private String fecha;

    private String observacionSalida;

    private String idRequerimiento;

    private int idProducto;

    private int idTipoUnidad;

    private int idTempora;

    private boolean producto;

    private boolean tipoUnidad;

    private boolean cantidad;

    private boolean btnGuardar;

    private boolean observacionVisual;

    private boolean btnEdicion;

    private boolean verRazonSalidad;

    private List<ItemsRequerimientoDto> ListadoRequerimientos;

    private List<RequerimientoDto> requerimientoDtos;

    private List<RequerimientoDto> requerimientoDtosSeleccionar;

    private List<ProductoDescripcionDto> productoDescripcionDtos;

    private List<TipoUnidadDto> tipoUnidadDtoList;

    @PostConstruct
    private void init() {
        usuarioDto = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        loadRequerimiento();
    }

    public void NuevoRequerimiento() {
        requerimientoDto = new RequerimientoDto();
        requerimientoDto.setId(CodeGenerator.Generator(10));
        requerimientoDto.setUsuario(UsuarioMapper.toUsuario(usuarioDto));
        requerimientoDto.setDependencia(usuarioDto.getUnidad().getDependencia());
        requerimientoDto.setEstado("PENDIENTE");
        fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LimpiarListadoRequerimiento();
        validarGuardar();
        ValidacionEdicion(1);
    }

    private void loadRequerimiento() {
        try {
            requerimientoDtos = requerimientoService.getRequerimientos(usuarioDto.getUnidad().getDependencia().getId()).stream().map(RequerimientoMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LimpiarListadoRequerimiento() {
        ListadoRequerimientos = new ArrayList<>();
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        RequerimientoDto c = (RequerimientoDto) value;
        return c.getId().toLowerCase().contains(filterText)
                || (c.getUsuario().getNombres()+ " "+ c.getUsuario().getApellidos()).toLowerCase().contains(filterText)
                || c.getRazonEntrada().toLowerCase().contains(filterText)
                || c.getEstado().toLowerCase().contains(filterText)
                || (String.valueOf(c.getFechaRegistrada())).contains(filterText);
    }

    public void AgregarRequerimiento() {
        itemsRequerimientoDto = new ItemsRequerimientoDto();
        productoDescripcionDtos = productoService.getAllProductosDescripcipDto();
        idProducto = 0;
        idTipoUnidad = 0;
        validarRegistrar(1);
    }

    public void cargarTipoUnidad() {
        this.tipoUnidadDtoList = tipoUnidadService.filterTipoUnidadListProducto(idProducto);
        validarRegistrar(2);
    }

    public void habilitarCantidad() {
        itemsRequerimientoDto.setCantidad(1);
    }

    public void registrarItemRequerimiento() {
        itemsRequerimientoDto.setId(getNextId());
        itemsRequerimientoDto.setRequerimiento(RequerimientoMapper.toEntity(requerimientoDto));
        itemsRequerimientoDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(idTipoUnidad)));
        itemsRequerimientoDto.setProducto(ProductoMapper.toProducto(productoService.getProductoById(idProducto)));
        itemsRequerimientoDto.setDescripcionProducto(ProductoMapper.toConcatProduct(ProductoMapper.toProducto(productoService.getProductoById(idProducto))));
        ListadoRequerimientos.add(itemsRequerimientoDto);
        validarGuardar();
        PrimeFaces.current().executeScript("PF('dialogProducto').hide()");
    }

    private int getNextId() {
        int maxId = 0;
        for (ItemsRequerimientoDto detalle : ListadoRequerimientos) {
            if (detalle.getId() > maxId) {
                maxId = detalle.getId();
            }
        }
        return maxId + 1;
    }

    public void eliminarTablaRequerimiento() {
        for (int i = 0; i < ListadoRequerimientos.size(); i++) {
            if (ListadoRequerimientos.get(i).getId() == idTempora) {
                ListadoRequerimientos.remove(i);
                break;
            }
        }
        for (int i = 0; i < ListadoRequerimientos.size(); i++) {
            ListadoRequerimientos.get(i).setId(i + 1);
        }
    }

    public void guardar() {
        CreateRequerimientoDto create = new CreateRequerimientoDto();
        create.setId(requerimientoDto.getId());
        create.setUsuario(requerimientoDto.getUsuario());
        create.setRazonEntrada(requerimientoDto.getRazonEntrada());
        create.setDependencia(requerimientoDto.getDependencia());
        List<CreateItemsRequerimientoDto> lst = ListadoRequerimientos.stream().map(ItemsRequerimientoMapper::tocreate).collect(Collectors.toList());
        requerimientoService.create(create, lst);
        loadRequerimiento();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El requerimiento ha sido registrado exitosamente en el sistema!"));
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private void validarRegistrar(int opcion) {
        switch (opcion) {
            case 1:
                producto = false;
                tipoUnidad = true;
                cantidad = true;
                break;
            case 2:
                producto = true;
                tipoUnidad = false;
                cantidad = false;
                break;
        }
    }

    private void validarGuardar() {
        btnGuardar = ListadoRequerimientos.isEmpty();
    }

    public void ViewDatosRequerimiento() {
        requerimientoDto = RequerimientoMapper.toDto(requerimientoService.getRequerimiento(idRequerimiento));
        fecha = requerimientoDto.getFechaRegistrada().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        ListadoRequerimientos = requerimientoService.getItemsByRequerimientoId(idRequerimiento).stream().map(ItemsRequerimientoMapper::toDto).collect(Collectors.toList());
        ValidacionEdicion(2);
    }

    private void ValidacionEdicion(int opcion) {
        switch (opcion) {
            case 1:
                observacionVisual = false;
                btnEdicion = true;
                verRazonSalidad = false;
                break;
            case 2:
                observacionVisual = true;
                btnEdicion = false;
                verRazonSalidad = true;
                break;
        }
    }

    public void deledeRequerimiento() {
        requerimientoService.delete(idRequerimiento);
        loadRequerimiento();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El requerimiento ha sido eliminado exitosamente en el sistema!"));
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public void limpiarObservacionSalidaDesaprobada() {
        observacionSalida = "";
    }

    public void estadoAprobado() {
        requerimientoService.setEstadoAprobado(idRequerimiento, " ");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡El requerimiento ha sido aprobado.!", null));
        loadRequerimiento();
        PrimeFaces.current().executeScript("PF('aceptar').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");

    }

    public void estadoDesaprobado() {
        requerimientoService.setEstadoDesaprobado(idRequerimiento, observacionSalida);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El requerimiento ha sido desaprobado.!"));
        loadRequerimiento();
        PrimeFaces.current().executeScript("PF('aceptar').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

}
