package edu.kh.comm.common.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.kh.comm.member.model.vo.Member;

@Component
@Aspect
public class BeforeAspect {
	private Logger logger = LoggerFactory.getLogger(BeforeAspect.class);
	
	// JoinPoint 인터페이스 : 
	// advice가 적용되는 Target Object(ServiceImpl)의
	// 		정보, 전달되는 매개변수, 메서드, 반환 값, 예외 등 얻을 수 있는 메서드를 제공
	
	// (주의사항) JoinPoint 인터페이스는 항상 첫 번째 매개변수로 작성되어야 한다!
	
	@Before("CommonPointcut.implPointcut()")
	public void serviceStart(JoinPoint jp) {
		
		String str = "---------------------------\n";
		// jp.getTarget() : aop가 적용된 객체(각종 ServiceImpl)
		String className = jp.getTarget().getClass().getSimpleName(); // 간단한 클래스 명 (패키지 명 제외)
											// class   class명
		
							// jp.getSignature는 수행되는 메서드 정보를 담고 있다.
		String methodName = jp.getSignature().getName();
		
						// jp.getArgs() : 메서드 호출 시 전달된 매개변수
		String param = Arrays.toString(jp.getArgs());
		
		str += "Start : " + className + " - " + methodName + "\n";
		
		str += "Parameter : " + param + "\n";
		
		
		try {
			// HttpServletRequest 얻어오기
			// 단, 스프링 스케쥴러 동작 시 예외 발생
			
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			
			Member loginMember = (Member) req.getSession().getAttribute("loginMember");
			
			str += "ip : " +  getRemoteAddr(req);
			
			if(loginMember != null) {
				str += " (email : " + loginMember.getMemberEmail() + ")";
			}
			
		}catch(Exception e) {
			str += "[스케쥴러 동작]";
		}
		
		
		
				
		logger.info(str);
		
		
	}
	
	public static String getRemoteAddr(HttpServletRequest request) {

        String ip = null;

        ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }

		return ip;
	}
}
