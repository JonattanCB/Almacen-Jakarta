package org.Almacen.TopAlmacen.Controladores.Requerimientos;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.DetalleProductoProveedorEntrada.ListaDetalleProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.CreateItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.ItemsRequerimiento.ItemsRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.CreateRequerimientoDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.TopAlmacen.DTO.TipoUnidad.TipoUnidadDto;
import org.Almacen.TopAlmacen.DTO.Usuario.UsuarioDto;
import org.Almacen.TopAlmacen.Mappers.ItemsRequerimientoMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Mappers.RequerimientoMapper;
import org.Almacen.TopAlmacen.Mappers.TipoUnidadMapper;
import org.Almacen.TopAlmacen.Model.ItemsRequerimiento;
import org.Almacen.TopAlmacen.Model.Requerimiento;
import org.Almacen.TopAlmacen.Services.EmpresaService;
import org.Almacen.TopAlmacen.Services.ProductoService;
import org.Almacen.TopAlmacen.Services.RequerimientoService;
import org.Almacen.TopAlmacen.Services.TipoUnidadService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    private String fecha;

    private int id;

    private int idProducto;

    private int idTipoUnidad;

    private int idTempora;

    private List<ItemsRequerimientoDto> ListadoRequerimientos;

    private List<RequerimientoDto> requerimientoDtos;

    private List<RequerimientoDto> requerimientoDtosSeleccionar;

    private List<ProductoDescripcionDto> productoDescripcionDtos;

    private  List<TipoUnidadDto> tipoUnidadDtoList;

    @PostConstruct
    private void init(){
        loadRequerimiento();
    }


    public void NuevoRequerimiento(){
        requerimientoDto = new RequerimientoDto();
        UsuarioDto usuarioDto = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        requerimientoDto.setUnidadDependencia(usuarioDto.getUnidad());
        requerimientoDto.setEstado("PENDIENTE");
        fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LimpiarListadoRequerimiento();
    }

    private void loadRequerimiento(){
        try {
            requerimientoDtos = requerimientoService.getRequerimientos().stream().map(RequerimientoMapper::toDto).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void LimpiarListadoRequerimiento(){
        ListadoRequerimientos = new ArrayList<>();
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }

        RequerimientoDto c = (RequerimientoDto) value;
        return c.getUnidadDependencia().getNombre().toLowerCase().contains(filterText)
                || (String.valueOf(c.getFechaRegistrada())).contains(filterText);
    }

    public void AgregarRequerimiento(){
        itemsRequerimientoDto = new ItemsRequerimientoDto();
        productoDescripcionDtos = productoService.productoDescripcionDtos();
        idProducto = 0;
        idTipoUnidad = 0;
    }

    public void cargarTipoUnidad() {
        this.tipoUnidadDtoList = tipoUnidadService.filterTipoUnidadListProducto(idProducto);
    }

    public void habilitarCantidad(){

    }

    public void registrarItemRequerimiento(){
        itemsRequerimientoDto.setId(getNextId());
        itemsRequerimientoDto.setRequerimiento(RequerimientoMapper.toEntity(requerimientoDto));
        itemsRequerimientoDto.setTipoUnidad(TipoUnidadMapper.toTipoUnidad(tipoUnidadService.getTipoUnidad(idTipoUnidad)));
        itemsRequerimientoDto.setDescripcion(ProductoMapper.toConcatProduct(ProductoMapper.toProducto(productoService.getProductoById(idProducto))));
        ListadoRequerimientos.add(itemsRequerimientoDto);
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

    public void guardar(){
        CreateRequerimientoDto create = new CreateRequerimientoDto();
        create.setUnidadDependencia(requerimientoDto.getUnidadDependencia());
        create.setRazonEntrada(requerimientoDto.getRazonEntrada());
        List<CreateItemsRequerimientoDto> lst = ListadoRequerimientos.stream().map(ItemsRequerimientoMapper::tocreate).collect(Collectors.toList());
        requerimientoService.create(create,lst);
        loadRequerimiento();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

}
