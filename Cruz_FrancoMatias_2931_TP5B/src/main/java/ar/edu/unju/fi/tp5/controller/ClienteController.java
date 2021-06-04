package ar.edu.unju.fi.tp5.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.tp5.model.Cliente;
import ar.edu.unju.fi.tp5.model.Compra;
import ar.edu.unju.fi.tp5.model.Producto;
import ar.edu.unju.fi.tp5.servicee.IClienteService;
import ar.edu.unju.fi.tp5.servicee.ICompraService;
import ar.edu.unju.fi.tp5.servicee.IProductoService;






@Controller
public class ClienteController {
	 
	
	
	
	
	
	
	@Autowired 
	@Qualifier("clienteServiceMySql")
    IClienteService clienteService;
   
   @Autowired 
   @Qualifier("compraServiceMySql")
   ICompraService compraService;
   
   @Autowired 
   @Qualifier("productoServiceMySql")
   IProductoService productoService;
   
 @GetMapping("/cliente/nuevo")
	public String getNuevoClientePage(Model model) {
		model.addAttribute("cliente",clienteService.getCliente());
		
		//la lista para mostrar productos
		
		List<Producto> productos = productoService.getAllProductos();
		model.addAttribute("productos",productos);
		
		
		return "nuevocliente";
		}
  
  
	 @PostMapping("/cliente/guardar")
	public ModelAndView guardarCliente(@Valid @ModelAttribute("cliente")Cliente cliente,BindingResult resultadoValid) {
		 ModelAndView modelView;
		 if(resultadoValid.hasErrors()){ // si tiene errores
			 modelView= new ModelAndView("nuevocliente");
			//la lista para mostrar productos
				List<Producto> productos = productoService.getAllProductos();
				modelView.addObject("productos",productos);
		          return modelView;
		 } else 
		 { //no hay errores
			   modelView = new ModelAndView("lista-clientes");
			 clienteService.addCliente(cliente);
		modelView.addObject("clientes",clienteService.getAllClientes());
		return modelView;
	     } 
	}
	
 @GetMapping("/cliente/listado")
 public String getClientesPAGE(Model model) {
	 model.addAttribute("cliente",clienteService.getCliente());
	 model.addAttribute("clientes",clienteService.getAllClientes());
	 return "lista-clientes";
 }
 
 
 @GetMapping("/cliente/editar/{id}")
 
 public ModelAndView getClienteEditPage(@PathVariable(value="id")Long id) {
	 ModelAndView modelView = new ModelAndView("nuevocliente");
	 Optional<Cliente> cliente = clienteService.getClientePorId(id);
	 modelView.addObject("cliente",cliente);
	 
	 List<Producto> productos = productoService.getAllProductos();
	 modelView.addObject("productos",productos);
	 return modelView;
	 
	 
	 
 }
 
 @GetMapping("/cliente/eliminar/{id}")
 
 public ModelAndView getClienteDelPage(@PathVariable(value="id")Long id) {
	 ModelAndView modelView = new ModelAndView("redirect:/cliente/listado");
	 clienteService.eliminarCliente(id);
	 return modelView;
	 
	 
	 
 }
 
@GetMapping("/index")
	public String getTestCirculo() {
		return "index";
	}

@GetMapping("/cliente/busqueda")

     public String buscarCompraPorFiltro(@RequestParam(name="nombreApellido") String nombreApellido, @RequestParam(name="dni") int dni,Model model, @ModelAttribute(name="cliente") Cliente cliente) {
	           model.addAttribute("cliente",clienteService.getCliente());
               model.addAttribute("clientes",clienteService.buscarClientes(nombreApellido, dni));
          return "lista-clientes";       
   }
	





}