package org.Almacen.Siman.Controladores.StockUnidad;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.Siman.DTO.StockUnidades.TablaStockUnidadesDto;
import org.Almacen.Siman.Services.StockUnidadesService;
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

    private List<TablaStockUnidadesDto> stockUnidadesDtos;

    private List<TablaStockUnidadesDto> stockUnidadesDtosSelecciones;

    @PostConstruct
    private void init(){
        loadStockUnidades();
    }

    private  void  loadStockUnidades(){
        try{
          stockUnidadesDtos = stockUnidadesService.getAllTablaStockUnidadesDto();
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
