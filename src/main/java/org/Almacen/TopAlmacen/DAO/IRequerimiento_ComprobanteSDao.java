package org.Almacen.TopAlmacen.DAO;

import org.Almacen.TopAlmacen.Model.Requerimiento_ComprobanteS;

import java.util.List;

public interface IRequerimiento_ComprobanteSDao {
    List<Requerimiento_ComprobanteS> getAll();

    Requerimiento_ComprobanteS getById(int id);

    void add(Requerimiento_ComprobanteS r);
}
