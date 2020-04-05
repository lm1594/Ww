package lkm.ww.comn.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 공통코드VO
 * @author lkm
 * @since 2020. 04. 02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CodeVO extends BaseVO {
	
	private String codeDiv;								// 코드구분
	private String codeDnm;								// 코드구분명
	private String codeVal;								// 코드
	private String codeNm;								// 코드명
	private String codeUpr;								// 상위코드
	private String codeTxt;								// 코드비고
	private String codeEtc;								// 코드기타
	private String codeCompare;							// 코드비교값
	private String codeUse;								// 코드사용여부
	private String codeDel;								// 코드삭제여부
	private String oldOwner;							// 과거 시스템 구분(삭제예정)
	private String oldTbl;								// 과거 테이블명(삭제예정)
	private String oldDiv;								// 과거 코드구분(삭제예정)
	private String oldVal;								// 과거 코드(삭제예정)
	private String codeRid;								// 코드등록자
	private String codeRdate;							// 코드등록일
	private String codeEid;								// 코드수정자
	private String codeEdate;							// 코드수정일
	private String codeOrder;							// 정렬순서
	private String level;								// 트리레벨
	private String isleaf;								// 말단노드
	private String codeStep;							// 코드검색레벨구분
	private String codeSort;							// 코드정렬 DESC, ASC
	private String oldISssUseYn;						// 과거 SSS 사용 여부
}
