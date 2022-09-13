package site.metacoding.red.web.dto.response.boards;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingDto {
	private boolean isNotResult;
	private String keyword;
	private Integer currentBlock; // 첫번째 블락 (6페이지로 가는 순간 2번째 블락), 변수
	private Integer blockCount; // 한 페이지에 페이지 갯수(5) 1-5, 6-10, 상수
	private Integer startPageNum; //1->6->11, 변수
	private Integer lastPageNum; //5->10->15, 변수
	private Integer totalCount;
	private Integer totalPage; // 23 / 10 나머지 있으면 +1
	private Integer currentPage;
	private boolean isLast;
	private boolean isFirst;
	
	private List<MainDto> mainDtos;
	
	public void makeBlockInfo(String keyword) {
		this.keyword = keyword;
		this.blockCount = 5;

		this.currentBlock = currentPage / blockCount;
		this.startPageNum = 1 + blockCount * currentBlock;
		this.lastPageNum = 5 + blockCount * currentBlock;

		if (totalPage < lastPageNum) {
			this.lastPageNum = totalPage;
		}
	}
}
