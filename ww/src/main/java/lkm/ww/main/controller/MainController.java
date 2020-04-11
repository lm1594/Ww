package lkm.ww.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 메인페이지 컨트롤러
 * @author 이경민
 * @since 2020.04.11
 */
@Controller
@RequestMapping(value = "/ww/main")
public class MainController {
	
	/**
	 * 메인페이지
	 * @param request, response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/main.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("ww/main/main");
		
		return mav;
	}
}
