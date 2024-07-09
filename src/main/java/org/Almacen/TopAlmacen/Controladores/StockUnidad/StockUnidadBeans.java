package org.Almacen.TopAlmacen.Controladores.StockUnidad;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.HistorialPrecios.HistorialPreciosDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.StockUnidadesDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.TablaStockUnidadesDto;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Mappers.StockUnidadesMapper;
import org.Almacen.TopAlmacen.Model.PrecioPorTipoUnidad;
import org.Almacen.TopAlmacen.Services.PrecioPorTipoUnidadService;
import org.Almacen.TopAlmacen.Services.StockUnidadesService;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Data
@Named("StockUnidadBeans")
@ViewScoped
public class StockUnidadBeans implements Serializable {

    @Inject
    private PrecioPorTipoUnidadService  precioPorTipoUnidadService;

    private List<TablaStockUnidadesDto> stockUnidadesDtos;

    private List<TablaStockUnidadesDto> stockUnidadesDtosSelecciones;

    @PostConstruct
    private void init(){
        loadStockUnidades();
    }

    private  void  loadStockUnidades(){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        TablaStockUnidadesDto c = (TablaStockUnidadesDto) value;
        return c.getDescripcion().toLowerCase().contains(filterText);
    }

}
