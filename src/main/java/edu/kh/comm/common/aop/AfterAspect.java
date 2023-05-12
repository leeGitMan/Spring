package edu.kh.comm.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class AfterAspect {
	
	private Logger logger = LoggerFactory.getLogger(BeforeAspect.class);
	
	
	@After("CommonPointcut.implPointcut()")
	public void serviceEnd(JoinPoint jp) {
		
	
		// jp.getTarget() : aop가 적용된 객체(각종 ServiceImpl)
		String className = jp.getTarget().getClass().getSimpleName(); // 간단한 클래스 명 (패키지 명 제외)
											// class   class명
		
							// jp.getSignature는 수행되는 메서드 정보를 담고 있다.
		String methodName = jp.getSignature().getName();
		
		
		String str = "End : " + className + " - " + methodName + "\n";
		str += "---------------------------\n";
		
		logger.info(str);
	}

}