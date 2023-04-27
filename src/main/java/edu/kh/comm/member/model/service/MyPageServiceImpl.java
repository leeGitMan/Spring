package edu.kh.comm.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.dao.MyPageDAO;

@Service
public class MyPageServiceImpl implements MyPageService{
	
	
	@Autowired
	private MyPageDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public int changePw(String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public int changePw(String pw) {
//		
//		int result = dao.changePw(pw);
//		
//		if(result > 0) {
//			
//		}
//		
//		return ;
//	}

}
