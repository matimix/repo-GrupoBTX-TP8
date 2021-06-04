package ar.edu.unju.fi.tp5.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tp5.model.Cliente;




public interface IClienteRepository extends CrudRepository<Cliente,Long> {
      public Cliente findByDni(int dni);
      
      public List <Cliente> findByProductoNombreAndDniGreaterThanEqual(String nombreApellido,int dni);
      public List <Cliente> findByDniGreaterThanEqual(int dni);
}
