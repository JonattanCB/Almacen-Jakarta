package org.Almacen.Siman.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.Almacen.Siman.DAO.IAccesoDao;

@LocalBean
@Stateless
public class AuthorizationService {

    @Inject
    private IAccesoDao iaccesoDao;

}
