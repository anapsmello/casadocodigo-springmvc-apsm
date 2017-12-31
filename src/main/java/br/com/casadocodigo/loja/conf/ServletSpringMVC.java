package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Configurarmos o servidor Tomcat para que 
 * ele passe as requisições para o SpringMVC usando Servlet, métodos 
 * getServletConfigClasses e getServletMappings
 * @author AnaPaula
 *
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	// comentado para poder fazer o deploy no heroku TODO ver como  ativar o profile dev e manter compativel com heroku
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		super.onStartup(servletContext);
//		servletContext.addListener(new RequestContextListener());
//		servletContext.setInitParameter("spring.profiles.active", "dev");
//	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class, JPAProductionConfiguration.class};
	}

	/* Disponibiliza as configurações para o Spring
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		//OBS: antes retornava AppWebConfiguration e JPAConfiguration, mas como SecurityConfiguration deve ser retornado 
		// no getRootConfigClasses e SecurityConfiguration depende do Dao de usuários, ambas passaram a ser retornadas no outro método 
		return new Class[]{};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	/* Configurando o encoding da aplicação
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletFilters()
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		
		// é possível incluir no retorno 'new OpenEntityManagerInViewFilter()'
		// porém ele realiza uma consulta adicional para cada item da lista que 
		// seria feito o fetch
		return new Filter[] {encodingFilter}; //, new OpenEntityManagerInViewFilter()
	}
	
	/*
	 * Usado para permitir receber os dados como multipart 
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#customizeRegistration(javax.servlet.ServletRegistration.Dynamic)
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}
