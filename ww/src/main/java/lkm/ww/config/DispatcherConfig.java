package lkm.ww.config;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import lkm.ww.comn.abstractview.NULLView;
import lkm.ww.comn.file.CSVDownload;
import lkm.ww.comn.file.ExcelDownload;
import lkm.ww.comn.file.FileDownload;
import lkm.ww.comn.file.PDFDownload;
import lkm.ww.comn.file.TxtDownload;
import lkm.ww.comn.file.WordDownload;
import lkm.ww.comn.web.filter.CORSFilter;
import lkm.ww.comn.web.filter.ClickJackFilter;
import lkm.ww.comn.web.filter.XSSFilter;

/**
 * Dispatcher 설정
 * @author lkm
 * @since 2020.04.02
 */
@Configuration
public class DispatcherConfig implements WebMvcConfigurer{
	
	@Bean
	public WebContentInterceptor webContentInterceptor() {
		WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
		webContentInterceptor.setCacheSeconds(0);
		return webContentInterceptor;
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
	    return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}
	
	//------------------------------------------------------------------------------------------------
	// 한글 처리 필터
	//------------------------------------------------------------------------------------------------
	@Bean
	public Filter characterEncodingFilter() {
	    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	    characterEncodingFilter.setEncoding("UTF-8");
	    characterEncodingFilter.setForceEncoding(true);
	    return characterEncodingFilter;
	}
	@Bean
	public FilterRegistrationBean<Filter> characterEncodingFilterBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(characterEncodingFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	//------------------------------------------------------------------------------------------------
	// multiapart 파일 업로드 필터
	//------------------------------------------------------------------------------------------------
	@Bean
	public Filter multipartFilter() {
		MultipartFilter multipartFilter = new MultipartFilter();
		return multipartFilter;
	}
	@Bean
	public FilterRegistrationBean<Filter> multipartFilterBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(multipartFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	//------------------------------------------------------------------------------------------------
	// Custom Wrapper를 사용한 XSS 필터 / 제외 컨텐츠는 XSS 필터에 걸리지 않도록 하기 위함.
	//------------------------------------------------------------------------------------------------
	@Bean
	public FilterRegistrationBean<Filter> xssFilterBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new XSSFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	//------------------------------------------------------------------------------------------------
	// ClickJack 필터
	//------------------------------------------------------------------------------------------------
	@Bean
	public FilterRegistrationBean<Filter> clickJackFilterBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new ClickJackFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	//------------------------------------------------------------------------------------------------
	// CORS 필터
	//------------------------------------------------------------------------------------------------
	@Bean
	public FilterRegistrationBean<Filter> corsFilterBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new CORSFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	//------------------------------------------------------------------------------------------------
	// Resolver
	//------------------------------------------------------------------------------------------------
	@Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100*1024*1024);
        return multipartResolver;
    }

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		resolver.setRedirectHttp10Compatible(false);
		resolver.setOrder(2);
		return resolver;
	}

	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(0);
		return beanNameViewResolver;
	}

	@Bean(name = "tilesViewResolver")
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setViewClass(TilesView.class);
		urlBasedViewResolver.setOrder(1);
		return urlBasedViewResolver;
	}

	@Bean(name = "tilesConfigurer")
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml"});
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	
	
	//------------------------------------------------------------------------------------------------
	// View
	//------------------------------------------------------------------------------------------------
	@Bean(name = "nullView")
	public NULLView nullView() {
		return new NULLView();
	}

	@Bean(name = "jsonView")
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	@Bean(name = "fileDownload")
	public FileDownload fileDownload() {
		return new FileDownload();
	}

	@Bean(name = "csvDownload")
	public CSVDownload csvDownload() {
		return new CSVDownload();
	}

	@Bean(name = "excelDownload")
	public ExcelDownload excelDownload() {
		return new ExcelDownload();
	}

	@Bean(name = "wordDownload")
	public WordDownload wordDownload() {
		return new WordDownload();
	}

	@Bean(name = "pdfDownload")
	public PDFDownload pdfDownload() {
		return new PDFDownload();
	}
	
	@Bean(name = "txtDownload")
	public TxtDownload txtDownload() {
		return new TxtDownload();
	}
}
