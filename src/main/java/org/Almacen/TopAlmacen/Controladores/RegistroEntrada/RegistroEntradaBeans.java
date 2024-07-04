package org.Almacen.TopAlmacen.Controladores.RegistroEntrada;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Categoria.CategoriaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Services.DetalleProductoProveedorEntradaService;
import org.Almacen.TopAlmacen.Services.ProductoProveedorEntradaService;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("RegistroEntradaBeans")
@ViewScoped
public class RegistroEntradaBeans implements Serializable {

    @Inject
    private ProductoProveedorEntradaService productoProveedorEntradaService;

    @Inject
    private DetalleProductoProveedorEntradaService detalleProductoProveedorEntradaService;


    private List<ProductoProveedorEntradaDto> productoProveedorEntradaDtos;

    private List<ProductoProveedorEntradaDto> productoProveedorEntradaDtosSeleccion;


    @PostConstruct
    private void init(){
        loadRegitroEntrada();
    }

    private void loadRegitroEntrada(){
        try {
            productoProveedorEntradaDtos = productoProveedorEntradaService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void NuevoRegistro(){

    }

    public  void verDatosRegistroEntrada(){

    }

    public void eliminarRegistroEntrada(){

    }


    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        ProductoProveedorEntradaDto c = (ProductoProveedorEntradaDto) value;
        return (c.getOC() >= filterInt && c.getOC() <= filterInt)
                || c.getEmpresa().getNombre().toLowerCase().contains(filterText)
                || c.getFechaRegistro().equals(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }


}

