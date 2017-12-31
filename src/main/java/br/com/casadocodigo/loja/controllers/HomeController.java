package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
//	@Autowired
//	private UserDetailsService usuarioDao;
	//private UsuarioDAO usuarioDao;
	
	@RequestMapping("/")
	@Cacheable(value="produtosHome")
	public ModelAndView index() {
		List<Produto> produtos = produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}
	
	
//	@ResponseBody
//	@RequestMapping("/url-magica-maluca-akdjfaklsjd9a8e45j4494j5iajemm3kh6gb53p56i5uj43jmjdf8as5j")
//	public String urlMagicaMaluca() {
//		System.out.println("Entou no método url maluca");
//	    Usuario usuario = new Usuario(); 
//	    usuario.setNome("Admin");
//	    usuario.setEmail("admin@cdc.com.br");
//	    usuario.setSenha("$2y$10$SBm5iMaRL/9iCt80vcH9M.goK1y37jhFjT2CCIzsp4njNcqRqtNkm");
//	    usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
//
//	    ((UsuarioDAO) usuarioDao).gravar(usuario);
//	    System.out.println("gravou usuário");
//
//	    return "Url Mágica executada";
//	}
}
