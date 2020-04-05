package lkm.ww.mntInfo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lkm.ww.comn.util.StringUtil;
import lkm.ww.mntInfo.domain.MntInfoVO;
import lkm.ww.mntInfo.service.MntInfoService;

/**
 * 산정보 컨트롤러
 * @author 이경민
 * @since 2020.04.05
 */
@Controller
@RequestMapping(value = "/ww/mntInfo")
public class MntInfoController {
	
	@Autowired
	private MntInfoService mntInfoService;
	
	/**
	 * 산정보 목록 조회 화면
	 * @param MntInfoVO
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/mntInfoList.do")
	public ModelAndView mntInfoList(HttpServletRequest request, @ModelAttribute("pvo") MntInfoVO pvo) throws Exception {
		ModelAndView mav = new ModelAndView("ww/mntInfo/mntInfoList");
		
		// 페이지 수
		if(StringUtil.isNullOrBlank(pvo.getPageNo())) {
			pvo.setPageNo("1");
		}
		
		// 페이지 로우 수
		if(StringUtil.isNullOrBlank(pvo.getNumOfRows())) {
			pvo.setNumOfRows("10");	
		}
		
		return mav;
	}
	
	/**
	 * 산정보 목록 조회 화면 Ajax
	 * @param MntInfoVO
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/mntInfoListAjax.do")
	public ModelAndView mntInfoListAjax(HttpServletRequest request, @ModelAttribute("pvo") MntInfoVO pvo) throws Exception {
		ModelAndView mav = new ModelAndView("ww/mntInfo/mntInfoListAjax");
		System.out.println("##### mntInfoListAjax 실행");
		return mav;
	}
}
