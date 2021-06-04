package ar.edu.unju.fi.tp5.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp5.model.Cliente;
import ar.edu.unju.fi.tp5.model.Compra;
import ar.edu.unju.fi.tp5.repository.ICompraRepository;
import ar.edu.unju.fi.tp5.servicee.ICompraService;



@Service("compraServiceMySql")
public class CompraServiceMysqlImp implements ICompraService {
	
	@Autowired
	
	private ICompraRepository compraRepository;
	

	@Autowired
	private Compra compra;
	
	
	@Override
	public void addCompra(Compra compra) {
		compraRepository.save(compra);
		
	}

	@Override
	public List<Compra> getAllCompras() {
	    List<Compra> compras = (List<Compra>) compraRepository.findAll();
	    return compras;
	}


	@Override
	public Compra getCompra() {
		// TODO Auto-generated method stub
		return compra;
	}

	@Override
	public List<Compra> buscarCompras(String nombre, double total) {
		List <Compra> compras = new ArrayList<Compra>();   
		if(!nombre.isEmpty() && total>=0) {
			    compras = compraRepository.findByProductoNombreAndTotalGreaterThanEqual(nombre, total);
			  }else if(nombre.isEmpty() && total >=0) {
				       compras = compraRepository.findByTotalGreaterThanEqual(total);
			  }
		   return compras;
	}

}
