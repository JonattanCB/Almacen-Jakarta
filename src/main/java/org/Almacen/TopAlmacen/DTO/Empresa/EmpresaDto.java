package org.Almacen.TopAlmacen.DTO.Empresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.Almacen.TopAlmacen.Model.TipoEmpresa;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDto {
    private String NroRUC;
    private String nombre;
    private TipoEmpresa tipoEmpresa;
    private String direccion;
    private LocalDate fechaRegistro;
}
