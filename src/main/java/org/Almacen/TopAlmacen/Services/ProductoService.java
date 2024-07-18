package org.Almacen.TopAlmacen.Services;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.TopAlmacen.DAO.IProductoDao;
import org.Almacen.TopAlmacen.DAO.IStockUnidadesDao;
import org.Almacen.TopAlmacen.DTO.Producto.CreateProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.UpdateProductoDto;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Model.Producto;
import org.Almacen.TopAlmacen.Model.StockUnidades;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ProductoService implements Serializable {

    @Inject
    private IProductoDao iProductoDao;
    @Inject
    private IStockUnidadesDao IStockUnidadesDao;

    @Transactional
    public List<ProductoDto> getAllProducto() {
        List<Producto> productos = iProductoDao.getAll();
        return productos.stream()
                .map(c -> new ProductoDto(c.getId(), c.getNombre(), c.getColor(), c.getPeso(), c.getCategoria(), c.getMarca(), c.getFechaRegistro(), c.getEstado(), c.getStockUnidades()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductoDto> getAllFalseEstao() {
        var productos = iProductoDao.getAllFalseEstado();
        return productos.stream().map(p -> new ProductoDto(p.getId(), p.getNombre(), p.getColor(), p.getPeso(), p.getCategoria(), p.getMarca(), p.getFechaRegistro(), p.getEstado(), p.getStockUnidades())).collect(Collectors.toList());
    }

    @Transactional
    public Producto createProducto(CreateProductoDto c) {
        var Producto = ProductoMapper.toProductoFromCreate(c);
        var stockCreated = new StockUnidades();
        stockCreated.setProducto(Producto);
        stockCreated.setCantidadStockUnidad(0);
        iProductoDao.create(Producto);
        IStockUnidadesDao.create(stockCreated);
        return Producto;
    }

    @Transactional
    public Producto updateProducto(int id, UpdateProductoDto updateProductoDto) {
        return iProductoDao.update(updateProductoDto, id);
    }

    @Transactional
    public ProductoDto getProductoById(int id) {
        var producto = iProductoDao.getById(id);
        return ProductoMapper.toDto(producto);
    }

    @Transactional
    public void delete(int id) {
        iProductoDao.delete(id);
    }


    @Transactional
    public List<ProductoDescripcionDto> productoDescripcionDtos() {
        List<Producto> productos = iProductoDao.getAll();
        return productos.stream().map(c -> new ProductoDescripcionDto(c.getId(), ProductoMapper.toConcatProduct(c))).collect(Collectors.toList());
    }

    public List<ProductoDescripcionDto> getAllProductosDescripcipDto(){
        List<Producto> productos = iProductoDao.getAllbyProductos();
        return productos.stream().map(c -> new ProductoDescripcionDto(c.getId(), ProductoMapper.toConcatProduct(c))).collect(Collectors.toList());
    }

    @Transactional
    public void ChangeStateACTIVO(int id) {
        iProductoDao.changeState(id, "ACTIVO");
    }

    @Transactional
    public void ChangeStateINACTIVO(int id) {
        iProductoDao.changeState(id, "INACTIVO");
    }
}
