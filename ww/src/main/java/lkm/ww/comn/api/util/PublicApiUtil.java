package lkm.ww.comn.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lkm.ww.mntInfo.domain.MntInfoVO;


/**
 * 공공API 유틸
 * @author 이경민
 * @since 2020.04.04
 */
public class PublicApiUtil {
	public static final String API_URL = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI";
	public static final String SERVICE_KEY = "o5vWBCY%2Bf9RCEQtMiHFni4vaElGViNFI2q40lXXF5Ua2POTHxDiXG2spanEigZ8pGv%2Bce6%2B4%2Fa6uL2887qutHQ%3D%3D";
	
	/**
	 * 산정보 목록 검색
	 * @param Map<String, Object>
	 * @throws Exception
	 */
	public static List<MntInfoVO> getMntInfoList(MntInfoVO pvo) throws Exception {
		
		//---------------------------------------------------------------
		// 공공데이터포탈 API (XML)
		//---------------------------------------------------------------
		StringBuilder urlBuilder = new StringBuilder(API_URL); 																	// URL
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+SERVICE_KEY); 									// Service Key
        urlBuilder.append("&" + URLEncoder.encode("searchWrd","UTF-8") + "=" + URLEncoder.encode(pvo.getMntiname(), "UTF-8")); 	// Parameter
        URL url = new URL(urlBuilder.toString());																		
        System.out.println("###url=>"+url);
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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
        System.out.println("### xmlJSONObjString : " + xmlJSONObjString);
        
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map = objectMapper.readValue(xmlJSONObjString, new TypeReference<Map<String, Object>>(){});
        Map<String, Object> dataResponse = (Map<String, Object>) map.get("response");
        Map<String, Object> header = (Map<String, Object>) dataResponse.get("header");
        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");
        Map<String, Object> items = null;
        List<Map<String, Object>> itemList = null;
        String numOfRows = null;
        String pageNo = null;
        String totalCount = null;
        
        items = (Map<String, Object>) body.get("items");
        itemList = (List<Map<String, Object>>) items.get("item");
        numOfRows = body.get("numOfRows").toString();
        pageNo = body.get("pageNo").toString();
        totalCount = body.get("totalCount").toString();
        
        List<MntInfoVO> listPage = new ArrayList<MntInfoVO>();
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
        
        return listPage;
	}
}
