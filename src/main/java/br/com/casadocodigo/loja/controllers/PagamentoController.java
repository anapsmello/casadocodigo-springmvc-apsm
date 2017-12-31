package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private MailSender sender;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST, name="finalizar")
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {
		return () -> {
			try {
				String url = "http://book-payment.herokuapp.com/payment";
				String response = restTemplate.postForObject(url, new DadosPagamento(carrinho.getTotal()), String.class);
				System.out.println(response);
				
//				enviaEmailCompraProduto(usuario); -> @AuthenticationPrincipal Usuario usuario
				
				model.addFlashAttribute("sucesso", response);
				return new ModelAndView("redirect:/produtos");
			} catch(HttpClientErrorException e) {
				e.printStackTrace();
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
		};
	}

//	private void enviaEmailCompraProduto(Usuario usuario) {
//		SimpleMailMessage email = new SimpleMailMessage();
//		email.setSubject("Compra finalizada com sucesso");
//		email.setTo("aninha.bcc@gmail.com");
//		email.setText("Compra aprovada com sucesso!");
//		email.setFrom("aninha.cadastros@gmail.com");
//		sender.send(email);
//	}
}
