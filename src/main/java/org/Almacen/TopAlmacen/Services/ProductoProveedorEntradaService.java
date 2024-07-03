package org.Almacen.TopAlmacen.Services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IProductoProveedorEntradaDao;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.CreateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.ProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.DTO.ProductoProveedorEntrada.UpdateProductoProveedorEntradaDto;
import org.Almacen.TopAlmacen.Mappers.ProductoProveedorEntradaMapper;
import org.Almacen.TopAlmacen.Model.ProductoProveedorEntrada;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ProductoProveedorEntradaService {
    @Inject
    private IProductoProveedorEntradaDao iProductoProveedorEntradaDao;

    @Transactional
    public List<ProductoProveedorEntradaDto> findAll() {
        var entradas = iProductoProveedorEntradaDao.getAll();
        return entradas.stream().map(ProductoProveedorEntradaMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public ProductoProveedorEntradaDto findById(int id) {
        var entrada = iProductoProveedorEntradaDao.getById(id);
        return ProductoProveedorEntradaMapper.toDto(entrada);
    }

    @Transactional
    public ProductoProveedorEntrada create(CreateProductoProveedorEntradaDto c) {
        var prodcu = ProductoProveedorEntradaMapper.fromCreate(c);
        return iProductoProveedorEntradaDao.create(prodcu);
    }

    @Transactional
    public ProductoProveedorEntrada update(UpdateProductoProveedorEntradaDto u, int oc) {
        return iProductoProveedorEntradaDao.update(u, oc);
    }

    @Transactional
    public void delete(int id) {
        iProductoProveedorEntradaDao.delete(id);
    }
}
