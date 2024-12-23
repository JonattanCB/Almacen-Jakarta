package org.Almacen.Siman.Controladores.ComprobanteSalida;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.Siman.DTO.ComprobanteSalida.ComprobanteSalidaDto;
import org.Almacen.Siman.DTO.ComprobanteSalida.CreateComprobanteSalidaDto;
import org.Almacen.Siman.DTO.DetalleComprobanteSalida.CreateDetalleComprobanteSalidaDto;
import org.Almacen.Siman.DTO.DetalleComprobanteSalida.DetalleComprobanteSalidaDto;
import org.Almacen.Siman.DTO.MovimientoStock.ValidacionStockDto;
import org.Almacen.Siman.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.Siman.DTO.Usuario.UsuarioDto;
import org.Almacen.Siman.Mappers.*;
import org.Almacen.Siman.Model.ComprobanteSalida;
import org.Almacen.Siman.Model.DetalleComprobanteSalida;
import org.Almacen.Siman.Model.ItemsRequerimiento;
import org.Almacen.Siman.Model.Requerimiento;
import org.Almacen.Siman.Services.ComprobanteSalidaService;
import org.Almacen.Siman.Services.PrecioPorTipoUnidadService;
import org.Almacen.Siman.Services.RequerimientoService;
import org.Almacen.Siman.Services.StockUnidadesService;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.Almacen.Siman.Util.RoundNumber.redondear;

@Data
@Named("ComprobanteSalidaBeans")
@ViewScoped
public class ComprobanteSalidaBeans implements Serializable {

    @Inject
    private RequerimientoService requerimientoService;

    @Inject
    private PrecioPorTipoUnidadService precioPorTipoUnidadService;

    @Inject
    private ComprobanteSalidaService comprobanteSalidaService;

    @Inject
    private StockUnidadesService stockUnidadesService;

    private RequerimientoDto requerimientodto;

    private ComprobanteSalidaDto comprobanteSalidaDto;

    private String idRequerimiento;

    private String fechaActual;

    private String idComprobateSalida;

    private double precioTotal;

    private boolean comboRequerimiento;

    private boolean btnGuardarRequermiento;

    private boolean btnGuardarRequerimientoView;

    private boolean inputObservacion;

    private boolean btnRegistrar;

    private List<RequerimientoDto> listRequermientoAprobado;

    private List<DetalleComprobanteSalidaDto> detalleComprobanteSalidaDtos;

    private List<ComprobanteSalidaDto> comprobanteSalidaDtos;

    private List<ComprobanteSalidaDto> comprobanteSalidaDtosSeleccion;

    private List<ValidacionStockDto> validacionStockDtos;

    @PostConstruct
    private void init() {
        loadComprobanteSalida();
        ValidarRequerimientoExit();
    }

    public void registrarComprobateSalida() {
        comprobanteSalidaDto = new ComprobanteSalidaDto();
        detalleComprobanteSalidaDtos = new ArrayList<>();
        listRequermientoAprobado = requerimientoService.getAllAprobed().stream().map(RequerimientoMapper::toDto).collect(Collectors.toList());
        comprobanteSalidaDto.setEstado("PENDIENTE");
        idRequerimiento = "";
        fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        validacionRequerimiento(1);
        validarVerComprobanteSalida(1);
    }

    public void CargarRequerimiento() {
        requerimientodto = RequerimientoMapper.toDto(requerimientoService.getRequerimiento(idRequerimiento));
        comprobanteSalidaDto.setId(requerimientodto.getId());
        comprobanteSalidaDto.setDependencia(requerimientodto.getUsuario().getUnidadDependencia().getDependencia());
        comprobanteSalidaDto.setParaUso(requerimientodto.getRazonEntrada());
        comprobanteSalidaDto.setSolicitante(requerimientodto.getUsuario());
        comprobanteSalidaDto.setDependencia(requerimientodto.getUsuario().getUnidadDependencia().getDependencia());
        cargarDatosTabla();
        calcularPrecioFinal();
        validacionRequerimiento(2);
    }


    private void cargarDatosTabla() {
        List<ItemsRequerimiento> lst = requerimientoService.getItemsByRequerimientoId(idRequerimiento);
        int idTemporar = 1;
        for (ItemsRequerimiento item : lst) {
            DetalleComprobanteSalidaDto detalle = new DetalleComprobanteSalidaDto();
            detalle.setId(idTemporar++);
            detalle.setComprobanteSalida(ComprobanteSalidaMapper.toEntity(comprobanteSalidaDto));
            detalle.setCantidad(item.getCantidad());
            detalle.setTipoUnidad(item.getTipoUnidad());
            detalle.setProducto(item.getProducto());
            detalle.setPrecioPorTipoUnidad(PrecioPorTipoUnidadMapper.toEntity(precioPorTipoUnidadService.getByIdProductoIdTipoUnidad(item.getProducto().getId(), item.getTipoUnidad().getId())));
            detalle.setPrecioUnitario(precioPorTipoUnidadService.getByIdProductoIdTipoUnidad(item.getProducto().getId(), item.getTipoUnidad().getId()).getPrecioUnitario());
            detalle.setPrecioTotal(detalle.getCantidad() * detalle.getPrecioUnitario());
            detalle.setDescripcionProducto(ProductoMapper.toConcatProduct(item.getProducto()));
            detalleComprobanteSalidaDtos.add(detalle);
        }
    }

    public void guardarComprobateSalida() {
        if (verificastock()) {
            PrimeFaces.current().executeScript("PF('dialogProducto').show()");
        } else {
            CreateComprobanteSalidaDto create = new CreateComprobanteSalidaDto();
            create.setId(comprobanteSalidaDto.getId());
            create.setObservacion(comprobanteSalidaDto.getObservacion());
            create.setDependencia(comprobanteSalidaDto.getDependencia());
            create.setParaUso(comprobanteSalidaDto.getParaUso());
            create.setSolicitante(comprobanteSalidaDto.getSolicitante());
            create.setEstado(comprobanteSalidaDto.getEstado());
            create.setPrecioFinal(precioTotal);
            List<CreateDetalleComprobanteSalidaDto> lst = detalleComprobanteSalidaDtos.stream().map(DetalleComprobanteSalidaMapper::toCreateDto).collect(Collectors.toList());
            comprobanteSalidaService.create(create, lst);
            System.out.println("A sido creado");
            loadComprobanteSalida();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡EL Comprobante de Salida ha sido registrado exitosamente en el sistema!"));
            requerimientoService.setEstadoFinalizado(idRequerimiento, "FINALIZADO");
            PrimeFaces.current().executeScript("PF('dialogsa').hide()");
            PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
        }
    }


    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        ComprobanteSalidaDto c = (ComprobanteSalidaDto) value;
        return c.getId().toLowerCase().contains(filterText)
                || (String.valueOf(c.getFechaRegistro())).toLowerCase().contains(filterText)
                || String.valueOf(c.getPrecioFinal()).contains(filterText)
                || (c.getDependencia().getNombre()).toLowerCase().contains(filterText)
                || (c.getParaUso()).toLowerCase().contains(filterText)
                || (c.getObservacion()).toLowerCase().contains(filterText);
    }

    public void verDatosComprobanteSalida() {
        comprobanteSalidaDto = ComprobanteSalidaMapper.toDto(comprobanteSalidaService.getById(idComprobateSalida));
        fechaActual = comprobanteSalidaDto.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        listRequermientoAprobado = requerimientoService.getAllAprobed().stream().map(RequerimientoMapper::toDto).collect(Collectors.toList());
        idRequerimiento = idComprobateSalida;
        detalleComprobanteSalidaDtos = comprobanteSalidaService.getDetalleComprobanteSalida(idComprobateSalida).stream().map(DetalleComprobanteSalidaMapper::toDto).collect(Collectors.toList());
        precioTotal = comprobanteSalidaDto.getPrecioFinal();
        validarVerComprobanteSalida(2);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void loadComprobanteSalida() {
        try {
            comprobanteSalidaDtos = comprobanteSalidaService.getall().stream().map(ComprobanteSalidaMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calcularPrecioFinal() {
        precioTotal = 0;
        for (DetalleComprobanteSalidaDto dt : detalleComprobanteSalidaDtos) {
            precioTotal = dt.getPrecioTotal() + precioTotal;
        }
        precioTotal = redondear(precioTotal, 2);
    }

    private void validacionRequerimiento(int opcion) {
        switch (opcion) {
            case 1:
                comboRequerimiento = false;
                btnGuardarRequermiento = true;
                break;
            case 2:
                comboRequerimiento = true;
                btnGuardarRequermiento = false;
                break;
        }
    }

    private boolean verificastock() {
        int idTemporal = 1;
        boolean stockInsuficiente = false;
        validacionStockDtos = new ArrayList<>();
        for (DetalleComprobanteSalidaDto dt : detalleComprobanteSalidaDtos) {
            if (stockUnidadesService.checkStock(dt.getProducto().getId(), dt.getCantidad(), dt.getPrecioPorTipoUnidad())) {
                ValidacionStockDto vs = new ValidacionStockDto();
                vs.setId(idTemporal++);
                vs.setCantidad(dt.getCantidad());
                vs.setDescripcion(dt.getDescripcionProducto());
                vs.setTipoUnidad(dt.getTipoUnidad().getAbrev());
                vs.setObservacion("STOCK INSUFICIENTE:El producto " + dt.getProducto().getNombre() + " no tiene suficiente stock para cubrir la solicitud. " + stockUnidadesService.convertStockFaltante(dt.getPrecioPorTipoUnidad(), dt.getCantidad()));
                validacionStockDtos.add(vs);
                stockInsuficiente = true;
            }
        }
        return stockInsuficiente;
    }

    public void editarTabla(RowEditEvent<DetalleComprobanteSalidaDto> event){
        int idtemp = Integer.parseInt(String.valueOf(event.getObject().getId()));
        for (DetalleComprobanteSalidaDto detalle : detalleComprobanteSalidaDtos) {
            if (detalle.getId() == idtemp) {
                detalle.setPrecioTotal(detalle.getCantidad() * detalle.getPrecioUnitario());
                break;
            }
        }
        calcularPrecioFinal();
    }

    private void validarVerComprobanteSalida(int opcion) {
        switch (opcion) {
            case 1:
                btnGuardarRequerimientoView = true;
                comboRequerimiento = false;
                inputObservacion = false;
                break;
            case 2:
                btnGuardarRequerimientoView = false;
                comboRequerimiento = true;
                inputObservacion = true;
                break;
        }
    }

    public void aprobarComprobateSalida(){
        ComprobanteSalida cs = comprobanteSalidaService.getById(idComprobateSalida);
        Requerimiento requerimiento = requerimientoService.getRequerimiento(cs.getId());
        List<DetalleComprobanteSalida> lst = comprobanteSalidaService.getDetalleComprobanteSalida(idComprobateSalida);
        UsuarioDto us = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        comprobanteSalidaService.insertToBD(cs,lst,requerimiento, UsuarioMapper.toUsuario(us));
        loadComprobanteSalida();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El comprobante de salidad  ha sido FINALIZADO en el sistema!"));
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public void desaprobarComprobanteSalida(){
        UsuarioDto us = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        comprobanteSalidaService.changeEstadoDesaprobado(UsuarioMapper.toUsuario(us),idComprobateSalida);
        loadComprobanteSalida();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El comprobante de salidad  ha sido DESAPROBADO en el sistema!"));
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    private  void ValidarRequerimientoExit(){
        List<RequerimientoDto> lst = requerimientoService.getAllAprobed().stream().map(RequerimientoMapper::toDto).collect(Collectors.toList());
        if (lst.isEmpty()){
            btnRegistrar = true;
        }else{
            btnRegistrar = false;
        }
    }


}
