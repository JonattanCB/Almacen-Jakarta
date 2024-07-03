package org.Almacen.TopAlmacen.Controladores.Historia;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.HistorialPrecios.HistorialPreciosDto;
import org.Almacen.TopAlmacen.DTO.Rol.RolDto;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Model.HistorialPrecios;
import org.Almacen.TopAlmacen.Services.HistorialPreciosService;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("HistoriaPrecioBeans")
@ViewScoped
public class HistoriaPrecioBeans implements Serializable {

    @Inject
    private HistorialPreciosService historialPreciosService;

    private List<HistorialPreciosDto> listaHistorialPrecios;

    private List<HistorialPreciosDto> listaHistorialPreciosSeleccion;

    private  int idHistorialPrecio;

    @PostConstruct
    private void init(){
        loadHistorialPrecios();
    }

    private void loadHistorialPrecios(){
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
        HistorialPrecios c = (HistorialPrecios) value;
        return (ProductoMapper.toConcatProduct(c.getPrecioPorTipoUnidad().getProducto())).toLowerCase().contains(filterText);
    }


}
