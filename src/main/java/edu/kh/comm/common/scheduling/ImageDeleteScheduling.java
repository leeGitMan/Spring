package edu.kh.comm.common.scheduling;


import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.kh.comm.board.model.service.BoardService;

@Component
public class ImageDeleteScheduling {
	private Logger logger = LoggerFactory.getLogger(ImageDeleteScheduling.class);
	
	// BOARD_IMG 테이블에서 삭제 되었으나
	// 서버/resources/images/board 폴더에 존재하는
	// 이미지 파일을 정시마다 삭제
	
	// 1) BOARD_IMG 존재하는 모든 이미지 목록 조회
	
	// 2) /resources/images/board 폴더에 존재하는 모든 이미지 파일 목록 조회
	
	// 3) 두 목록을 비교해서, 일치하지 않는 이미지 파일 삭제
	// DB에 없는데 서버에 있으면 삭제하란 말
	
	@Autowired
	private BoardService service;
	
	// 서버 폴더 경로 얻어오기
	@Autowired
	private ServletContext application; // application scope 객체 -> 서버 폴더 경로 얻어오기에 사용
	
	
	// 스케쥴링에 사용되는 메서드는 무조건 public void 메서드명 매개변수 없는 것이어야 한다.
	
	@Scheduled(cron = "0 * * * * *") // 매 분마다(테스트 용)
	public void serverImageDelte() {
		
		List<String> dbList = service.selectDBList();
		
		String folderPath = application.getRealPath("/resources/images/board");
		
		File path = new File(folderPath); // resources/imags/board 폴더를 참조하는 객체
		
		File[] arr = path.listFiles(); // path가 참조하는 폴더에 있는 모든 파일을 얻어와 File 배열 반환
		
		List<File> serverList = Arrays.asList(arr); // arr을 List로 반환
		
		if(!serverList.isEmpty()) { // 서버에 이미지 파일이 있을 때  비교/삭제 진행
			
			for(File serverImage :  serverList) {
				String name = "/resources/images/board" + serverImage.getName(); // 파일명만 얻어오기
				
				if(dbList.indexOf(name) == -1) { // List에 value 와 같은 값이 있으면 인덱스 반호나 / 없으면  -1 반환
					
					//dbList에는 없는데, serverList에 있는 것
					logger.info(serverImage.getName() + " 삭제");
					serverImage.delete();
				}
			}
			logger.info("================ 서버 이미지 삭제 완료 ================");
		}
	}
}
