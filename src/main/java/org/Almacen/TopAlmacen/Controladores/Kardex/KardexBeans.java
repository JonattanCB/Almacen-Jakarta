package org.Almacen.TopAlmacen.Controladores.Kardex;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.Services.KardexService;
import org.Almacen.TopAlmacen.Services.ProductoService;
import org.Almacen.TopAlmacen.Util.ItemKardexTemp;
import org.Almacen.TopAlmacen.Util.KardexTemp;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@Named("KardexBeans")
@ViewScoped
public class KardexBeans implements Serializable {

    @Inject
    private ProductoService productoService;

    @Inject
    private KardexService kardexService;

    private int idproducto;

    private boolean validarBtns;

    private List<ProductoDescripcionDto> productoDescripcionDtos;

    private List<Date> fechasSeleccionadas;

    private List<ItemKardexTemp> itemKardexTemps;

    private List<ItemKardexTemp> itemKardexTempsSeleccion;

    @PostConstruct
    private  void init(){
        fechasSeleccionadas = new ArrayList<>();
        itemKardexTemps = new ArrayList<>();
        loadProductoDescripcionDtos();
        validarBotones();
    }

    private void loadProductoDescripcionDtos(){
        this.productoDescripcionDtos = productoService.productoDescripcionDtos();
    }

    public void buscarKardex(){
        LocalDate startDate = fechasSeleccionadas.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate;
        if (fechasSeleccionadas.size() ==1){
            endDate = fechasSeleccionadas.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }else{
           endDate = fechasSeleccionadas.get(fechasSeleccionadas.size() - 1).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        KardexTemp kardex = kardexService.generarKardex(idproducto, startDate, endDate);
        itemKardexTemps = kardex.getItems();
        PrimeFaces.current().ajax().update( ":form-datos:tabla");
    }


    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        ItemKardexTemp c = (ItemKardexTemp) value;
        return (String.valueOf(c.getFecha()).toLowerCase().contains(filterText));
    }

    public void validarBotones(){
        if (fechasSeleccionadas.isEmpty() || idproducto ==0 || productoDescripcionDtos.isEmpty()){
            validarBtns = true;
        }else{
            validarBtns = false;
        }
    }

}
