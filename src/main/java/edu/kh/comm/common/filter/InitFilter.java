package edu.kh.comm.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebFilter(filterName="initFilter", urlPatterns="/*")
public class InitFilter extends HttpFilter implements Filter {
	
	// print 구문 사용하다 걸리면 바보
	// -> Logger 사용
	// Logger 객체 생성 (해당 클래스에 대한 log를 출력하는 객체)
	private Logger logger = LoggerFactory.getLogger(InitFilter.class);
       
    // 필터가 생성될 때 실행
	public void init(FilterConfig fConfig) throws ServletException {
		// logger를 이용해서 출력하는 방법
		// trace, debug, info, warn, error
		
		// debug : 개발의 흐름을 파악할 때(이게 실행이 되었는지, 파라미터가 현재 무슨 값을 가지는지 
		// 확인할 때) 사용 
		// info :  메서드 실행할 때 정보 전달
		
		// 이 필터가 생성이 되었다 라는 정보를 출력하고 싶다! info가 제일 적절함
		logger.info("초기화 필터 생성");
	}
	

	// 필터가 파괴댈 때 실행 (서버는 켜져있는데, 백엔드 코드가 수정되었을 때)
	public void destroy() {
		
		logger.info("초기화 필터 파괴");
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// application 내장 객체 얻어오기
		ServletContext application = request.getServletContext();
				
		// 최상위 주소 얻어오기
		String contextPath =  ( (HttpServletRequest)request ).getContextPath();
									// 다운캐스팅
		// 세팅
		application.setAttribute("contextPath", contextPath);
		
		chain.doFilter(request, response);
	}

	

}
