package org.Almacen.TopAlmacen.Controladores;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.TipoEmpresa.TipoEmpresaDto;
import org.Almacen.TopAlmacen.Model.TipoEmpresa;

import java.io.Serializable;
import java.util.List;

@Data
@Named("TipoEmpresaBeans")
@ViewScoped
public class TipoEmpresaBeans implements Serializable {



    private List<TipoEmpresaDto> tipoEmpresaDtoList;
    private TipoEmpresaDto tipoEmpresaDto;
    private int tipoEmpresaID;


    @PostConstruct
    private void init(){

    }









}
