package lkm.ww.comn.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lkm.ww.comn.logger.Logger;
import lkm.ww.comn.util.LogUtil;
import lkm.ww.comn.util.StringUtil;
import lkm.ww.mntInfo.domain.ImgSort;
import lkm.ww.mntInfo.domain.MntInfoVO;


/**
 * 공공API 유틸
 * @author 이경민
 * @since 2020.04.04
 */
public class PublicApiUtil {
	private static final String MNT_INFO_API_URL = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI";						// 산정보 목록
	private static final String FRTRL_SECTN_API_URL = "http://apis.data.go.kr/1400000/service/cultureInfoService/frtrlSectnOpenAPI";					// 숲길구간
	private static final String MNT_INFO_IMG_API_URL = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoImgOpenAPI";						// 산 이미지 검색 
	private static final String FRTRL_SPOT_API_URL = "http://apis.data.go.kr/1400000/service/cultureInfoService/frtrlSpotOpenAPI";
	private static final String SERVICE_KEY = "o5vWBCY%2Bf9RCEQtMiHFni4vaElGViNFI2q40lXXF5Ua2POTHxDiXG2spanEigZ8pGv%2Bce6%2B4%2Fa6uL2887qutHQ%3D%3D";
	
	/**
	 * 산정보 목록 검색
	 * @param MntInfoVO
	 * @return List<MntInfoVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<MntInfoVO> getMntInfoList(MntInfoVO pvo) throws Exception {
		
		Logger.getLogger().debug("######### 산정보 목록 검색 API 시작");
		
		//---------------------------------------------------------------
		// 공공데이터포탈 API (XML)
		//---------------------------------------------------------------
		StringBuilder urlBuilder = new StringBuilder(MNT_INFO_API_URL); 																			// URL
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+SERVICE_KEY); 														// Service Key
        urlBuilder.append("&" + URLEncoder.encode("searchWrd","UTF-8") + "=" + URLEncoder.encode(StringUtil.nvl(pvo.getMntiname(),""), "UTF-8")); 	// Parameter
        URL url = new URL(urlBuilder.toString());																		
        Logger.getLogger().debug("###url=>"+url);
        
        Map<String, Object> resultMap = apiProc(url);
        Map<String, Object> dataResponse = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> header = (Map<String, Object>) dataResponse.get("header");
        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");

        Map<String, Object> items = new HashMap<String, Object>();
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
        String numOfRows = null;
        String pageNo = null;
        String totalCount = null;

        numOfRows = body.get("numOfRows").toString();
        pageNo = body.get("pageNo").toString();
        totalCount = body.get("totalCount").toString();
        
        List<MntInfoVO> listPage = new ArrayList<MntInfoVO>();
        if(Integer.parseInt(totalCount) > 0) {
            items = (Map<String, Object>) body.get("items");
            if(items.get("item") instanceof java.util.LinkedHashMap) {
        		// 데이터가 1건인 경우 LinkedHashMap으로 옴
        		Map<String, Object> map = (Map<String, Object>)items.get("item");
        		itemList.add(map);
        	}else {
        		itemList = (List<Map<String, Object>>) items.get("item");
        	}
            
        	for(Map<String, Object> temp : itemList) {
            	MntInfoVO resultVO = new MntInfoVO();
            	resultVO.setMntilistno(String.valueOf(temp.get("mntilistno")));			// 산정보코드
            	resultVO.setMntitop(String.valueOf(temp.get("mntitop")));				// 100대명산선정이유
            	resultVO.setMntiname(String.valueOf(temp.get("mntiname")));				// 산명
            	resultVO.setMntisname(String.valueOf(temp.get("mntisname")));			// 산정보부제
            	resultVO.setMntiadd(String.valueOf(temp.get("mntiadd")));				// 소재지
            	resultVO.setMntihigh(String.valueOf(temp.get("mntihigh")));				// 높이
            	resultVO.setMntiadmin(String.valueOf(temp.get("mntiadmin")));			// 관리주체
            	resultVO.setMntiadminnum(String.valueOf(temp.get("mntiadminnum")));		// 관리자전화번호
            	resultVO.setMntisummary(String.valueOf(temp.get("mntisummary")));		// 산정보상세정보내용
            	resultVO.setMntidetails(String.valueOf(temp.get("mntidetails")));		// 산정보설명

                resultVO.setResultCode(String.valueOf(header.get("resultCode")));		// 결과코드
                resultVO.setResultMsg(String.valueOf(header.get("resultMsg")));			// 결과메세지
                resultVO.setNumOfRows(numOfRows);										// 페이지 로우수
                resultVO.setPageNo(pageNo);												// 페이지 넘버
                resultVO.setTotalCount(Integer.parseInt(totalCount));					// 총 로우수
                listPage.add(resultVO);
            }
        }
        
        Logger.getLogger().debug("######### 산정보 목록 검색 API 종료");
        
        return listPage;
	}
	
	/**
	 * 숲길구간 검색
	 * @param MntInfoVO
	 * @return List<MntInfoVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<MntInfoVO> getFrtrlSectn(MntInfoVO pvo) throws Exception {
		Logger.getLogger().debug("######### 숲길구간명 검색 API 시작");
		
		//---------------------------------------------------------------
		// 공공데이터포탈 API (XML)
		//---------------------------------------------------------------
		StringBuilder urlBuilder = new StringBuilder(FRTRL_SECTN_API_URL); 																				// URL
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+SERVICE_KEY); 															// Service Key
        urlBuilder.append("&" + URLEncoder.encode("mntiListNo","UTF-8") + "=" + URLEncoder.encode(StringUtil.nvl(pvo.getMntilistno(),""), "UTF-8")); 	// Parameter
        URL url = new URL(urlBuilder.toString());																		
        Logger.getLogger().debug("###url=>"+url);
        
        Map<String, Object> resultMap = apiProc(url);
        Map<String, Object> dataResponse = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> header = (Map<String, Object>) dataResponse.get("header");
        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");

        Map<String, Object> items = new HashMap<String, Object>();
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
        String numOfRows = null;
        String pageNo = null;
        String totalCount = null;
        
        numOfRows = body.get("numOfRows").toString();
        pageNo = body.get("pageNo").toString();
        totalCount = body.get("totalCount").toString();
        
        List<MntInfoVO> listPage = new ArrayList<MntInfoVO>();
        if(Integer.parseInt(totalCount) > 0) {
            items = (Map<String, Object>) body.get("items");
            if(items.get("item") instanceof java.util.LinkedHashMap) {
        		// 데이터가 1건인 경우 LinkedHashMap으로 옴
        		Map<String, Object> map = (Map<String, Object>)items.get("item");
        		itemList.add(map);
        	}else {
        		itemList = (List<Map<String, Object>>) items.get("item");
        	}
            
        	for(Map<String, Object> temp : itemList) {
            	MntInfoVO resultVO = new MntInfoVO();
            	resultVO.setFrtrlSectnNm(String.valueOf(temp.get("frtrlsectnnm")));		// 숲길구간명
            	listPage.add(resultVO);
            	
            }
        }
        
        Logger.getLogger().debug("######### 숲길구간명 검색 API 종료");
        
		return listPage;
	}
	
	/**
	 * 산 이미지 검색
	 * @param MntInfoVO
	 * @return List<MntInfoVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<MntInfoVO> getMntImg(MntInfoVO pvo) throws Exception {
		Logger.getLogger().debug("######### 산 이미지 정보 검색 API 시작");
		
		//---------------------------------------------------------------
		// 공공데이터포탈 API (XML)
		//---------------------------------------------------------------
		StringBuilder urlBuilder = new StringBuilder(MNT_INFO_IMG_API_URL); 																				// URL
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+SERVICE_KEY); 															// Service Key
        urlBuilder.append("&" + URLEncoder.encode("mntiListNo","UTF-8") + "=" + URLEncoder.encode(StringUtil.nvl(pvo.getMntilistno(),""), "UTF-8")); 	// Parameter
        URL url = new URL(urlBuilder.toString());																		
        Logger.getLogger().debug("###url=>"+url);
        
        Map<String, Object> resultMap = apiProc(url);
        Map<String, Object> dataResponse = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> header = (Map<String, Object>) dataResponse.get("header");
        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");
        
        Map<String, Object> items = new HashMap<String, Object>();
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
        String numOfRows = null;
        String pageNo = null;
        String totalCount = null;
        
        numOfRows = body.get("numOfRows").toString();
        pageNo = body.get("pageNo").toString();
        totalCount = body.get("totalCount").toString();
        
        List<MntInfoVO> listPage = new ArrayList<MntInfoVO>();

        if(Integer.parseInt(totalCount) > 0) {
        	items = (Map<String, Object>) body.get("items");
        	if(items.get("item") instanceof java.util.LinkedHashMap) {
        		// 데이터가 1건인 경우 LinkedHashMap으로 옴
        		Map<String, Object> map = (Map<String, Object>)items.get("item");
        		itemList.add(map);
        	}else {
        		itemList = (List<Map<String, Object>>) items.get("item");
        	}
        	
            for(Map<String, Object> temp : itemList) {
            	MntInfoVO resultVO = new MntInfoVO();
            	resultVO.setImgno(String.valueOf(temp.get("imgno")));				// 이미지 순번
            	resultVO.setImgname(String.valueOf(temp.get("imgname")));			// 이미지 명
            	resultVO.setImgfilename("www.forest.go.kr/images/data/down/mountain/"+String.valueOf(temp.get("imgfilename")));	// 이미지 파일경로
            	listPage.add(resultVO);
            	
            }
            
            // 이미지 순번 오름차순으로 정렬
            ImgSort comp = new ImgSort();
            Collections.sort(listPage, comp);
        }
        
        Logger.getLogger().debug("######### 산 이미지 정보 검색 API 종료");
        
		return listPage;
	}
	
	/**
	 * 숲길 지점 검색
	 * @param MntInfoVO
	 * @return List<MntInfoVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<MntInfoVO> getMngmeSpot(MntInfoVO pvo) throws Exception {
		Logger.getLogger().debug("######### 숲길 지점 검색 API 시작");
		
		//---------------------------------------------------------------
		// 공공데이터포탈 API (XML)
		//---------------------------------------------------------------
		StringBuilder urlBuilder = new StringBuilder(FRTRL_SPOT_API_URL); 																				// URL
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+SERVICE_KEY); 															// Service Key
        urlBuilder.append("&" + URLEncoder.encode("mntiListNo","UTF-8") + "=" + URLEncoder.encode(StringUtil.nvl(pvo.getMntilistno(),""), "UTF-8")); 	// Parameter
        URL url = new URL(urlBuilder.toString());																		
        Logger.getLogger().debug("###url=>"+url);
        
        Map<String, Object> resultMap = apiProc(url);
        Map<String, Object> dataResponse = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> header = (Map<String, Object>) dataResponse.get("header");
        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");

        Map<String, Object> items = new HashMap<String, Object>();
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
        String numOfRows = null;
        String pageNo = null;
        String totalCount = null;

        numOfRows = body.get("numOfRows").toString();
        pageNo = body.get("pageNo").toString();
        totalCount = body.get("totalCount").toString();
        
        List<MntInfoVO> listPage = new ArrayList<MntInfoVO>();
        if(Integer.parseInt(totalCount) > 0) {
            items = (Map<String, Object>) body.get("items");
            if(items.get("item") instanceof java.util.LinkedHashMap) {
        		// 데이터가 1건인 경우 LinkedHashMap으로 옴
        		Map<String, Object> map = (Map<String, Object>)items.get("item");
        		itemList.add(map);
        	}else {
        		itemList = (List<Map<String, Object>>) items.get("item");
        	}
            
        	for(Map<String, Object> temp : itemList) {
            	MntInfoVO resultVO = new MntInfoVO();
            	resultVO.setMngmeSpotSeq(String.valueOf(temp.get("mngmeSpotSeq")));			// 관리지점순번
            	resultVO.setMngmeSpotTpcd(String.valueOf(temp.get("mngmeSpotTpcd")));		// 관리지점구분코드
            	resultVO.setMngmeSpotXcrdVal(String.valueOf(temp.get("mngmeSpotXcrdVal")));	// 관리지점 X좌표값
            	resultVO.setMngmeSpotYcrdVal(String.valueOf(temp.get("mngmeSpotYcrdVal")));	// 관리지점 Y좌표값
            	resultVO.setMngmeSpotHaslv(String.valueOf(temp.get("mngmeSpotHaslv")));		// 관리지점 해발고도
            	listPage.add(resultVO);
            	
            }
        }
        
        Logger.getLogger().debug("######### 숲길 지점 검색 API 종료");
        
		return listPage;
	}
	
	public static void main(String[] args) throws Exception {
		// 산정보 목록 검색 테스트 ex)불암산 No 113500101
		MntInfoVO pvo = new MntInfoVO();
		pvo.setMntiname("북");
		System.out.println(getMntInfoList(pvo));
		
		// 숲길구간 테스트
		//pvo.setMntilistno("113500101");
		//System.out.println(getFrtrlSectn(pvo));
		
		// 산이미지 정보 검색 테스트
		//pvo.setMntilistno("113500101");
		//System.out.println(getMntImg(pvo));
		
		// 숲길 지점 정보 테스트
		//pvo.setMntilistno("113500101");
		//System.out.println(getMngmeSpot(pvo));
	}
	
	/**
	 * API 데이터 처리(공통)
	 * @param URL
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public static Map<String, Object> apiProc(URL url) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        Logger.getLogger().debug("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        //---------------------------------------------------------------
        // XML내용을 JSON으로 변환
        //---------------------------------------------------------------
        JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
        String xmlJSONObjString = xmlJSONObj.toString();
        Logger.getLogger().debug("### xmlJSONObjString : " + xmlJSONObjString);
        
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map = objectMapper.readValue(xmlJSONObjString, new TypeReference<Map<String, Object>>(){});
        
        return map;
	}
}
