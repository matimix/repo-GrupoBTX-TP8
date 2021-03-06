package ar.edu.unju.fi.tp5.servicee;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp5.model.Cliente;





public interface IClienteService {

	public void addCliente(Cliente cliente);
	
	public Cliente getUltimoCliente();
	
	public Cliente getCliente();
	
	public List<Cliente> getAllClientes();
	
	public Cliente getClientePordni(int dni);
	
	public Optional<Cliente> getClientePorId(Long id);
	
	public void eliminarCliente(Long id);
	
	
	//nuevo para compra
	public List<Cliente> buscarClientes(String nombre, int dni);
	
	
}
