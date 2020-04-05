package lkm.ww.test.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 테스트 컨트롤러
 * @author 이경민
 * @since 2020.04.03
 */
@Controller
@RequestMapping("/ww/test")
public class TestController {
	
	@RequestMapping({"/test1.do", "/test2.do", "/test3.do"
					, "/test4.do", "/test5.do", "/test6.do"
					, "/test7.do"})
	public ModelAndView test(HttpServletRequest request) {
		String pageName = request.getRequestURI().split("/")[3].replaceAll(".do", "");
		ModelAndView mav = new ModelAndView("ww/test/"+pageName);
		return mav;
	}
}
