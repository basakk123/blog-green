package site.metacoding.red.web.dto.response.boards;

import java.util.List;

import lombok.Getter;

@Getter
public class ListPageDto {
	private Integer id;
	private String title;
	private String username;
	
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
	
	public void toListPageDto(MainDto mainDto, PagingDto pagingDto){
		this.id = mainDto.getId();
		this.title = mainDto.getTitle();
		this.username =mainDto.getUsername();
		
		this.keyword = pagingDto.getKeyword();
		this.currentBlock = pagingDto.getCurrentBlock();
		this.blockCount = pagingDto.getBlockCount();
		this.startPageNum = pagingDto.getStartPageNum();
		this.lastPageNum = pagingDto.getLastPageNum();
		this.totalCount = pagingDto.getTotalCount();
		this.totalPage = pagingDto.getTotalPage();
		this.currentPage = pagingDto.getCurrentPage();
		this.isLast = pagingDto.isLast();
		this.isFirst = pagingDto.isFirst();
		
	}
}
