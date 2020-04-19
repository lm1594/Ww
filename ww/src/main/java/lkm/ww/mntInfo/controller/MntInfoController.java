package lkm.ww.mntInfo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lkm.ww.comn.api.util.PublicApiUtil;
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
	 * 산 정보 조회 목록 Ajax
	 * @param MntInfoVO
	 * @return List<MntInfoVO>
	 * @throws Exception
	 */
	@RequestMapping(value = "/mntInfoListAjax.do")
	public ModelAndView mntInfoListAjax(HttpServletRequest requeset, @ModelAttribute("pvo") MntInfoVO pvo, Map<String, Object> map) throws Exception {
		ModelAndView mav = new ModelAndView("ww/mntInfo/mntInfoListAjax");
		
		// 산정보 목록 검색
		List<MntInfoVO> list = PublicApiUtil.getMntInfoList(pvo);
		
		// 산 이미지 정보 SET
		List<MntInfoVO> imgRVO = new ArrayList<MntInfoVO>();
		for(int i=0 ; i<list.size() ; i++) {
			imgRVO = PublicApiUtil.getMntImg(list.get(i));
			if(imgRVO.size() > 0) {
				MntInfoVO resultVO = list.get(i);
				resultVO.setImgfilename(imgRVO.get(0).getImgfilename());
				list.set(i, resultVO);
			}
		}
		
		mav.addObject("list", list);
		return mav;
	}
}
