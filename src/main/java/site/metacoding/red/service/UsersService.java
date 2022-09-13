package site.metacoding.red.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.CheckDto;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;


@RequiredArgsConstructor
@Service
public class UsersService {
	
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;
	
	public void 회원가입(JoinDto joinDto) { // username, password, email (id, createdAt은 안받음)
		// 1. 디티오를 엔티티로 변경하는 코드
		Users users = joinDto.toEntity();
		// 2. 엔티티로 디비 수행
		usersDao.insert(users);
	}

	public Users 로그인(LoginDto loginDto) { // username, password
		Users usersPS = usersDao.findByUsername(loginDto.getUsername());
		
		// if로 usersPS의 password와 디티오 password 비교
		if(usersPS.getPassword().equals(loginDto.getPassword())) {
			return usersPS;
		}else {
			return null;
		}	
	}

	public void 회원수정(Integer id, UpdateDto updateDto) { // id, 디티오(password, email)
		// 1. 영속화
		Users usersPS = usersDao.findById(id);
		// 2. 영속화된 객체 변경
		 usersPS.update(updateDto);
		// 3. 디비 수행
		usersDao.update(usersPS);
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public void 회원탈퇴(Integer id) { // users - delete, boards - update 둘 다 연결
		usersDao.deleteById(id);
		
		//해당회원이 적은 글을 모두 찾아서 usersId를 null로 업데이트();
		boardsDao.updateByUsersId(id);
		
	} // 마이바티스에서 for문 돌리면서 업데이트, 

	public boolean 유저네임중복확인(String username) {
		Users usersPS = usersDao.findByUsername(username);
		
		if(usersPS == null) { // 아이디가 중복 안됨
			return false;
		}else { // 아이디가 중복됨
			return true;
		}
		// 있으면 true, 없으면 false 리턴
	}

	public Users 회원정보보기(Integer id) {
		Users usersPS = usersDao.findById(id);
		return usersPS;
	}
}
