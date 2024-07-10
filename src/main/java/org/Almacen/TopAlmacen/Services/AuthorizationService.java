package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.Almacen.TopAlmacen.DAO.IAccesoDao;
import org.Almacen.TopAlmacen.Model.Acceso;

import java.util.List;

@LocalBean
@Stateless
public class AuthorizationService {

    @Inject
    private IAccesoDao iaccesoDao;

}
