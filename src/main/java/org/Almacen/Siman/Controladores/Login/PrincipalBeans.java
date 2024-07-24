package org.Almacen.Siman.Controladores.Login;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.Siman.DTO.HistorialPrecios.HistorialPreciosDto;
import org.Almacen.Siman.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.Siman.DTO.StockUnidades.TablaStockUnidadesDto;
import org.Almacen.Siman.DTO.Usuario.UsuarioDto;
import org.Almacen.Siman.Mappers.RequerimientoMapper;
import org.Almacen.Siman.Services.*;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
@Named("PrincipalBeans")
@ViewScoped
public class PrincipalBeans implements Serializable {

    @Inject
    private RequerimientoService requerimientoService;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private ProductoService productoService;

    @Inject
    private ProductoProveedorEntradaService productoProveedorEntradaService;

    @Inject
    private ComprobanteSalidaService comprobanteSalidaService;

    @Inject
    private StockUnidadesService stockUnidadesService;

    private UsuarioDto us;

    private boolean pantallaUnidad;

    private boolean pantallaJefatura;

    private boolean pantallaAlmacen;


    private int cantidadItem;

    private int  cantidadItem1;

    private int  cantidadItem2;

    private int cantidadItem3;

    private int cantidadItem4;

    private int cantidadItem5;

    private int cantidadItem6;

    private int cantidadItem7;

    private List<RequerimientoDto> requerimientos;

    private List<TablaStockUnidadesDto> stockUnidadesDtos;

    private List<TablaStockUnidadesDto> stockUnidadesDtoBajos;

    @PostConstruct
    private void init(){
        us  = (UsuarioDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        visualizacionDashboard();
    }

    private void visualizacionDashboard(){
         pantallaJefatura = false;
         pantallaUnidad = false;
         pantallaAlmacen = false;
        if (us.getUnidad().getNombre().equalsIgnoreCase("Jefatura") &&
                !us.getUnidad().getDependencia().getNombre().equalsIgnoreCase("Almacen")) {
            pantallaJefatura = true;
            loadRequerimeintosJefatura();
            cargarCardJefatura();
        } else if (us.getUnidad().getDependencia().getNombre().equalsIgnoreCase("Almacen")) {
            pantallaAlmacen = true;
            cargarCardAlmacen();
            loadStockUnidad();
            loadStockUnidadBajo();
        } else if (!us.getUnidad().getRol().getNombre().toLowerCase().contains("jef") &&
                !us.getUnidad().getDependencia().getNombre().equalsIgnoreCase("Almacen")) {
            pantallaUnidad = true;
            loadRequerimientosUser();
            cargarCardUser();
        }
    }

    private void loadStockUnidadBajo(){
        AtomicInteger counter = new AtomicInteger(1);
        stockUnidadesDtoBajos = stockUnidadesService.getAllTablaStockUnidadesDto().stream().filter(dto -> dto.getCantidadStockUnidad() < 30).map(dto -> {
                    dto.setId(counter.getAndDecrement());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private void loadRequerimientosUser(){
        requerimientos = requerimientoService.getAllByUnidadUser(us.getUnidad().getId(),us.getId()).stream().map(RequerimientoMapper::toDto).collect(Collectors.toList());
    }

    private void loadRequerimeintosJefatura(){
        requerimientos = requerimientoService.getRequerimientosStatusPendiente(us.getUnidad().getDependencia().getId()).stream().map(RequerimientoMapper::toDto).collect(Collectors.toList());
    }

    private void loadStockUnidad(){
        stockUnidadesDtos = stockUnidadesService.getAllTablaStockUnidadesDto();
    }

    private void cargarCardAlmacen(){
        cantidadItem = productoService.cantidadProductos();
        cantidadItem1 = productoService.cantidadProductosStatus("ACTIVO");
        cantidadItem2 = usuarioService.cantidadUsuarios();
        cantidadItem3 = usuarioService.cantidadUsuariosStatus("ACTIVO");
        cantidadItem4 = productoProveedorEntradaService.cantiStatus("PENDIENTE");
        cantidadItem5 = productoProveedorEntradaService.cantiStatus("FINALIZADO");
        cantidadItem6 = comprobanteSalidaService.cantStatus("FINALIZADO");
        cantidadItem7 = comprobanteSalidaService.cantStatus("PENDIENTE");
    }

    private void cargarCardJefatura(){
        cantidadItem = requerimientoService.setCantidadRequerimientoDependencia(us.getUnidad().getDependencia().getId());
        cantidadItem1 = requerimientoService.setCantidadRequermientoDependenciaStatus(us.getUnidad().getDependencia().getId(),"PENDIENTE");
        cantidadItem2 = requerimientoService.setCantidadRequermientoDependenciaStatus(us.getUnidad().getDependencia().getId(),"APROBADO");
        cantidadItem3 = requerimientoService.setCantidadRequermientoDependenciaStatus(us.getUnidad().getDependencia().getId(),"DESAPROBADO");
        cantidadItem4 = usuarioService.cantidadUsuarioDependencia(us.getUnidad().getDependencia().getId());
        cantidadItem5 = usuarioService.cantidadUsuarioDependenciaStatus(us.getUnidad().getDependencia().getId(),"ACTIVO");
    }

    private  void cargarCardUser(){
        cantidadItem = requerimientoService.setCantidadRequerimientoUser(us.getId(),us.getUnidad().getId());
        cantidadItem1 = requerimientoService.setCantidadRequermientoStatus(us.getId(),us.getUnidad().getId(),"APROBADO");
        cantidadItem2 = requerimientoService.setCantidadRequermientoStatus(us.getId(),us.getUnidad().getId(),"PENDIENTE");
        cantidadItem3 = requerimientoService.setCantidadRequermientoStatus(us.getId(),us.getUnidad().getId(),"DESAPROBADO");
        cantidadItem4 = requerimientoService.setCantidadRequermientoStatus(us.getId(),us.getUnidad().getId(),"FINALIZADO");
    }

}
