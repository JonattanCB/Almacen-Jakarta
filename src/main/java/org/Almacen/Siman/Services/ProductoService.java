package org.Almacen.Siman.Services;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.Siman.DAO.IPrecioPorTipoUnidadDao;
import org.Almacen.Siman.DAO.IProductoDao;
import org.Almacen.Siman.DAO.IStockUnidadesDao;
import org.Almacen.Siman.DTO.PrecioPorTipoUnidad.CreatePrecioPorTipoUnidadDto;
import org.Almacen.Siman.DTO.Producto.CreateProductoDto;
import org.Almacen.Siman.DTO.Producto.ProductoDescripcionDto;
import org.Almacen.Siman.DTO.Producto.ProductoDto;
import org.Almacen.Siman.DTO.Producto.UpdateProductoDto;
import org.Almacen.Siman.Mappers.PrecioPorTipoUnidadMapper;
import org.Almacen.Siman.Mappers.ProductoMapper;
import org.Almacen.Siman.Model.PrecioPorTipoUnidad;
import org.Almacen.Siman.Model.Producto;
import org.Almacen.Siman.Model.StockUnidades;
import org.Almacen.Siman.Model.Usuario;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ProductoService implements Serializable {

    @Inject
    private IProductoDao iProductoDao;
    @Inject
    private IStockUnidadesDao iStockUnidadesDao;
    @Inject
    private PrecioPorTipoUnidadService precioPorTipoUnidadService;

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
    public Producto createProducto(CreateProductoDto c, CreatePrecioPorTipoUnidadDto dto, Usuario u) {
        var Producto = ProductoMapper.toProductoFromCreate(c);
        var stockCreated = new StockUnidades();
        stockCreated.setProducto(Producto);
        stockCreated.setCantidadStockUnidad(0);
        Producto p = iProductoDao.create(Producto);
        iStockUnidadesDao.create(stockCreated);
        dto.setProducto(p);
        precioPorTipoUnidadService.crearUnidadBasica(dto, u);
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

    public List<ProductoDescripcionDto> getAllProductosDescripcipDto() {
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

    @Transactional
    public int cantidadProductos(){
       return iProductoDao.CantidadProductos();
    }

    @Transactional
    public int cantidadProductosStatus(String estado){
        return  iProductoDao.cantidadProductosStatus(estado);
    }
}
