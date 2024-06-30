package org.Almacen.TopAlmacen.Controladores.Empresa;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.Almacen.TopAlmacen.DTO.Empresa.CreateEmpresaDto;
import org.Almacen.TopAlmacen.DTO.Empresa.EmpresaDto;
import org.Almacen.TopAlmacen.DTO.Empresa.UpdateEmpresaDto;
import org.Almacen.TopAlmacen.DTO.TipoEmpresa.TipoEmpresaDto;
import org.Almacen.TopAlmacen.Mappers.EmpresaMapper;
import org.Almacen.TopAlmacen.Mappers.TipoEmpresaMapper;
import org.Almacen.TopAlmacen.Services.EmpresaService;
import org.Almacen.TopAlmacen.Services.TipoEmpresaService;
import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@Named("EmpresaBeans")
@SessionScoped
public class EmpresaBeans implements Serializable {

    @Inject
    private EmpresaService empresaService;

    @Inject
    private TipoEmpresaService tipoEmpresaService;

    private EmpresaDto empresaDto;

    private String NroRUC;

    private  int idTipoEmpresa;

    private boolean EscribirDatos;

    private boolean btnRegistar;

    private boolean btnEditar;

    private boolean DatoRuc;

    private List<EmpresaDto> empresaDtoList;

    private List<EmpresaDto> empresaDtoListSeleccionar;

    private List<TipoEmpresaDto>  tipoEmpresaList;

    @PostConstruct
    private void init(){
        loadEmpresa();
    }

    private  void loadEmpresa(){
        try {
            empresaDtoList = empresaService.getAllEmpresa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nuevoEmpresa(){
        empresaDto = new EmpresaDto();
        tipoEmpresaList = tipoEmpresaService.getAllTipoEmpresa();
        idTipoEmpresa = 0;
        verificiadorAccion(1);
    }


    private void cargarEmpresa(){
        empresaDto = empresaService.getEmpresa(getNroRUC());
        idTipoEmpresa = empresaDto.getTipoEmpresa().getId();
        tipoEmpresaList = tipoEmpresaService.getAllTipoEmpresa();
    }

    public void cargarEmpresaEdiccion(){
        cargarEmpresa();
        verificiadorAccion(2);
    }

    public void cargarEmpresaVista(){
        cargarEmpresa();
        verificiadorAccion(3);
    }

    public void createEmpresa(){
        boolean empresaExists = empresaService.EmpresaExists(empresaDto.getNroRUC());
        CreateEmpresaDto createEmpresaDto = new CreateEmpresaDto();
        createEmpresaDto.setNroRUC(empresaDto.getNroRUC());
        createEmpresaDto.setNombre(empresaDto.getNombre());
        createEmpresaDto.setDireccion(empresaDto.getDireccion());
        createEmpresaDto.setTipoEmpresa(TipoEmpresaMapper.toTipoEmpresa(tipoEmpresaService.getTipoEmpesa(idTipoEmpresa)));
        if (empresaExists){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( "Advertencia!! La empresa con este RUC ya existe "));
            PrimeFaces.current().ajax().update(":form-datos:messages");
        }else{
            empresaService.createEmpresa(createEmpresaDto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La Empresa "+createEmpresaDto.getNombre()+" ha sido registrado exitosamente en el sistema!"));
            loadEmpresa();
            PrimeFaces.current().executeScript("PF('dialogsa').hide()");
            PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
        }
    }

    public void updateEmpresa(){
        UpdateEmpresaDto updateEmpresaDto = new UpdateEmpresaDto();
        updateEmpresaDto.setNroRUC(empresaDto.getNroRUC());
        updateEmpresaDto.setNombre(empresaDto.getNombre());
        updateEmpresaDto.setDireccion(empresaDto.getDireccion());
        updateEmpresaDto.setTipoEmpresa(TipoEmpresaMapper.toTipoEmpresa(tipoEmpresaService.getTipoEmpesa(idTipoEmpresa)));
        empresaService.updateEmpresa(NroRUC,updateEmpresaDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡La Empresa "+updateEmpresaDto.getNombre()+" ha sido actualizado exitosamente en el sistema!"));
        loadEmpresa();
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public void deleteEmpresa(){
        EmpresaDto dto = EmpresaMapper.toDto(empresaService.deleteEmpresa(NroRUC));
        loadEmpresa();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡El producto " + dto.getNombre() + " ha sido eliminado exitosamente del sistema!"));
        PrimeFaces.current().executeScript("PF('dialogsa').hide()");
        PrimeFaces.current().ajax().update(":form-datos:messages", ":form-datos:tabla");
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        EmpresaDto e = (EmpresaDto) value;
        return   e.getNroRUC().toLowerCase().contains(filterText)
                || e.getNombre().toLowerCase().contains(filterText)
                || (e.getTipoEmpresa().getAbrev()).toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private  void verificiadorAccion(int opcion){
        switch (opcion){
            case 1:
                EscribirDatos = false;
                btnRegistar = true;
                btnEditar = false;
                DatoRuc = false;
                break;
            case 2:
                EscribirDatos = false;
                btnRegistar = false;
                btnEditar = true;
                DatoRuc = true;
                break;
            case 3:
                EscribirDatos = true;
                btnRegistar = false;
                btnEditar = false;
                DatoRuc = true;
                break;
        }
    }
}
