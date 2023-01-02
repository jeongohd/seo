package kr.co.project.member;

import javax.servlet.http.HttpSession;

public interface MemberService {
	int insert(MemberVO vo);
	int emailDupCheck(String email);
	boolean loginCheck(MemberVO vo, HttpSession sess);
	MemberVO findEmail(MemberVO vo);
	MemberVO findPwd(MemberVO vo);
}
