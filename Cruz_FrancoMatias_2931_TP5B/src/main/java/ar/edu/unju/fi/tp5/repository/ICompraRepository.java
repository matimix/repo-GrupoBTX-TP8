package ar.edu.unju.fi.tp5.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tp5.model.Cliente;
import ar.edu.unju.fi.tp5.model.Compra;



public interface ICompraRepository extends CrudRepository<Compra,Long> {
	public Compra findById(long id);

	public List <Compra> findByProductoNombreAndTotalGreaterThanEqual(String nombre,double total);
    public List <Compra> findByTotalGreaterThanEqual(double total);

}
