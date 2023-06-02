package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.model.Categoria;
import com.latam.alura.tienda.model.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();
		ProductoDao productoDao=new ProductoDao(em);
		Producto producto=productoDao.consultaPorId(1l);
		System.out.println(producto.getNombre());
		BigDecimal productos=productoDao.consultarPrecioPorNombreProducto("motorola");
		System.out.println(productos);
	}

	private static void registrarProducto() {
		Categoria celulares=new Categoria("celulares");
		
		Producto celular=new Producto("motorola", "usado de alta calidad", new BigDecimal("800"), celulares);

		EntityManager em = JPAUtils.getEntityManager();
		ProductoDao productoDao=new ProductoDao(em);
		CategoriaDao categoriaDao=new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.guardar(celulares);
		productoDao.guardar(celular);
		em.getTransaction().commit();
		em.close();
	}

}
