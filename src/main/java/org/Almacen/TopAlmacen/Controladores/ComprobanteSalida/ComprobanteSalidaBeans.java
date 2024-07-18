package org.Almacen.TopAlmacen.Controladores.ComprobanteSalida;


import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.ComprobanteSalida.ComprobanteSalidaDto;
import org.Almacen.TopAlmacen.DTO.Requerimiento.RequerimientoDto;
import org.Almacen.TopAlmacen.Mappers.RequerimientoMapper;
import org.Almacen.TopAlmacen.Services.RequerimientoService;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Named("ComprobanteSalidaBeans")
@ViewScoped
public class ComprobanteSalidaBeans implements Serializable {

    @Inject
    private RequerimientoService requerimientoService;

    private RequerimientoDto requerimientodto;

    private ComprobanteSalidaDto comprobanteSalidaDto;

    private String idRequerimiento;

    private String fechaActual;

    private boolean comboRequerimiento;

    private List<RequerimientoDto> listRequermientoAprobado;

    @PostConstruct
    private void init(){

    }

    public void registrarComprobateSalida(){
        comprobanteSalidaDto = new ComprobanteSalidaDto();
       listRequermientoAprobado = requerimientoService.getAllAprobed().stream().map(RequerimientoMapper::toDto).collect(Collectors.toList());
       validacionRequerimiento(1);
       fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void CargarRequerimiento(){
        //requerimientodto = requerimientoService.getRequerimiento();
        comprobanteSalidaDto.setUnidadDependencia(requerimientodto.getUnidadDependencia());
        comprobanteSalidaDto.setParaUso(requerimientodto.getRazonEntrada());
        validacionRequerimiento(2);
    }

    private void validacionRequerimiento(int opcion){
        switch (opcion){
            case 1:
                comboRequerimiento = false;
                break;
            case 2:
                comboRequerimiento= true;
                break;
            default:
                comboRequerimiento=false;
                break;
        }
    }

}
