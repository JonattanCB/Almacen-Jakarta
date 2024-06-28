package org.Almacen.TopAlmacen.Services;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.Almacen.TopAlmacen.DAO.IProductoDao;
import org.Almacen.TopAlmacen.DTO.Categoria.UpdateCategoriaDto;
import org.Almacen.TopAlmacen.DTO.Marca.CreateMarcaDto;
import org.Almacen.TopAlmacen.DTO.Marca.MarcaDto;
import org.Almacen.TopAlmacen.DTO.Producto.CreateProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.ProductoDto;
import org.Almacen.TopAlmacen.DTO.Producto.UpdateProductoDto;
import org.Almacen.TopAlmacen.Mappers.MarcaMapper;
import org.Almacen.TopAlmacen.Mappers.ProductoMapper;
import org.Almacen.TopAlmacen.Model.Categoria;
import org.Almacen.TopAlmacen.Model.Marca;
import org.Almacen.TopAlmacen.Model.Producto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ProductoService implements Serializable {

    @Inject
    private IProductoDao iProductoDao;

    @Transactional
    public List<ProductoDto> getAllProducto() {
        List<Producto> productos = iProductoDao.getAll();
        return productos.stream()
                .map(c -> new ProductoDto(c.getId(), c.getNombre(), c.getColor(), c.getPeso(), c.getCategoria(), c.getMarca(), c.getFechaRegistro()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Producto createProducto(CreateProductoDto createMarcaDto) {
        var Producto = ProductoMapper.toProductoFromCreate(createMarcaDto);
        return iProductoDao.create(Producto);
    }

    @Transactional
    public Producto updateProducto(int id, UpdateProductoDto updateProductoDto) {
        return  iProductoDao.update(updateProductoDto,id);
    }

    @Transactional
    public ProductoDto getProductoById(int id){
        var producto = iProductoDao.getById(id);
        return ProductoMapper.toDto(producto);
    }

    @Transactional
    public Producto deleteProducto(int id) {
        return iProductoDao.delete(id);
    }

}
