package org.Almacen.TopAlmacen.Controladores.StockUnidad;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.HistorialPrecios.HistorialPreciosDto;
import org.Almacen.TopAlmacen.DTO.StockUnidades.StockUnidadesDto;
import org.Almacen.TopAlmacen.Services.StockUnidadesService;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("StockUnidadBeans")
@ViewScoped
public class StockUnidadBeans implements Serializable {

    @Inject
    private StockUnidadesService stockUnidadesService;

    private List<StockUnidadesDto> stockUnidadesDtos;

    private List<StockUnidadesDto> stockUnidadesDtosSelecciones;

    @PostConstruct
    private void init(){
        loadStockUnidades();
    }

    private  void  loadStockUnidades(){
        try{
            stockUnidadesDtos = stockUnidadesService.getAllStockUnidades();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        StockUnidadesDto c = (StockUnidadesDto) value;
        return c.getDescripcion().toLowerCase().contains(filterText);
    }

}
