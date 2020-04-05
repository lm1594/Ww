package lkm.ww.comn.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Page Value Object
 * @author lkm
 * @since 2020. 04. 02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageVO {
	static final int PAGE_ROWS_SIZE = 10;
	static final int PAGE_UNIT_SIZE = 10;
	
	private String rnum;								// 로우넘버
	private String pageNo;								// 페이지번호
	private int totalCount;								// 전체 수
	private String numOfRows;							// 페이지로우
	private String pageUnit;							// 페이지유닛
	
	public PageVO() {
		this(PAGE_ROWS_SIZE, PAGE_UNIT_SIZE);
	}

	public PageVO(int pageRowsSize, int pageUnitSize) {
		this.pageNo = String.valueOf(1);
		this.numOfRows = String.valueOf(pageRowsSize);
		this.pageUnit = String.valueOf(pageUnitSize);
	}
}
