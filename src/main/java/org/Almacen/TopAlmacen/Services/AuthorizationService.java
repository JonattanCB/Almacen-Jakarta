package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.Almacen.TopAlmacen.DAO.IAccesoDao;
import org.Almacen.TopAlmacen.DAO.IPermisoDao;
import org.Almacen.TopAlmacen.DAO.IRolDao;
import org.Almacen.TopAlmacen.Model.Acceso;
import org.Almacen.TopAlmacen.Model.Permiso;

import java.util.List;

@LocalBean
@Stateless
public class AuthorizationService {

    @Inject
    private IAccesoDao iaccesoDao;

    @Inject
    private IPermisoDao ipermisoDao;

    public boolean tienePermiso(int rolId, String url, String permisoNombre) {
        List<Acceso> accesos = iaccesoDao.findByRolId(rolId);
        for (Acceso acceso : accesos) {
            if (acceso.getURL().equals(url)) {
                List<Permiso> permisos = ipermisoDao.findByAccesoId(acceso.getId());
                for (Permiso permiso : permisos) {
                    if (permiso.getNombre().equals(permisoNombre)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}