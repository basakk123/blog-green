package site.metacoding.red.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.ListPageDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Service
public class BoardsService {
	
	private final BoardsDao boardsDao;

	public PagingDto 게시글목록보기(Integer page, String keyword) {
		//페이징 해야됨
		if (page == null) {
			page = 0;
		}
		int startNum = page * 3;
		List<MainDto> boardsList = boardsDao.findAll(startNum, keyword);
		PagingDto pagingDto = boardsDao.paging(page, keyword);
		if (boardsList.size() == 0) 
		pagingDto.setNotResult(true);
		pagingDto.makeBlockInfo(keyword);
		pagingDto.setMainDtos(boardsList);

		return pagingDto;
		
	}
	
	public Boards 게시글상세보기(Integer id) {
		Boards boardsPS = boardsDao.findById(id);
		return boardsPS;
	}
	
	public void 게시글수정하기(Integer id, site.metacoding.red.web.dto.request.boards.UpdateDto updateDto) {
		Boards boardsPS = boardsDao.findById(id);
		boardsPS.글수정(updateDto);
		boardsDao.update(boardsPS);		
	}
	
	public void 게시글삭제하기(Integer id) {
		boardsDao.deleteById(id);
	}
	
	public void 게시글쓰기(Integer usersId, WriteDto writeDto) {
		boardsDao.insert(writeDto.toEntity(usersId));
	}
	
}
