package org.Almacen.TopAlmacen.Controladores.Historia;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.MovimientoStock.MovimientoStockDto;
import org.Almacen.TopAlmacen.Services.MovimientoStockService;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("HistoriaMovimientoBeans")
@ViewScoped
public class HistoriaMovimientoBeans implements Serializable {

    @Inject
    private MovimientoStockService movimientoStockService;

    private List<MovimientoStockDto> movimientoStockDtos;

    private List<MovimientoStockDto> movimientoStockDtosSeleccion;

    @PostConstruct
    private void init(){
        loadMoviminetoStock();
    }

    private void loadMoviminetoStock(){
        try {
            movimientoStockDtos = movimientoStockService.getAllMovimientoStock();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        MovimientoStockDto c = (MovimientoStockDto) value;
        return c.getTipoUnidad().toLowerCase().contains(filterText);
    }


}
