(()=>{
	if(document.getElementById("openChatRoom") != null){

		document.getElementById("openChatRoom").addEventListener("click", () => {
			const modal = document.querySelector('.modal');
			modal.classList.add('show');
			modal.classList.remove('hide');
		});

		document.getElementById("modal-close").addEventListener("click", () => {
			const modal = document.querySelector('.modal');
			modal.classList.add('hide');
			modal.classList.remove('show');
		});

		document.getElementById("open-form").addEventListener("submit", e => {
			const chatRoomTitle = document.getElementById("chatRoomTitle");
			if( chatRoomTitle.value.trim().length == 0){
				alert("채팅방 제목을 입력해주세요.");
				e.preventDefault(); // form태그 기본 이벤트 제거
			}
		});
	}
})();

// -------------------------------------------------------------------------

// 페이지 로딩 완료 시 채팅창을 제일 밑으로 내리기
(function(){

	const display = document.getElementsByClassName("display-chatting")[0];
	
	if(display != null){
		display.scrollTop = display.scrollHeight;
	}

})();



// 채팅 메세지 보내기
document.getElementById("send").addEventListener("click", sendMessage);


// 채팅 보내기 함수
function sendMessage(){

	// 채팅이 입력되는 textarea
	const inputChatting = document.getElementById("inputChatting"); 

	if(inputChatting.value.trim().length == 0){
		// 입력이 되지 않은 경우
		alert("채팅을 입력해주세요.");
		
		inputChatting.value = ""; // 공백문자 삭제
		inputChatting.focus();

	} else{
		// 입력이 된 경우

		// 메세지 입력 시 필요한 데이터를 js객체로 생성
		const chatMessage = {
			"memberNo" : memberNo,
			"memberEmail" : memberEmail,
			"memberNickname" : memberNickname,
			"chatRoomNo" : chatRoomNo,
			"message" : inputChatting.value
		};


		// JSON.parse(문자열) : JSON -> JS Object
		// JSON.stringify(객체) :  JS Object -> JSON
		//console.log(chatMessage);
		//console.log(    JSON.stringify(chatMessage)   );

		// chattingSock(웹소켓 객체)을 이용하여 메세지 보내기
		// chattingSock.send(값) : 웹소켓 핸들러로 값을 보냄
		chattingSock.send( JSON.stringify(chatMessage) );

		inputChatting.value = ""; // 입력된 채팅 내용 삭제
	}
}

// 웹소켓 핸들러에서
// s.sendMessage( new TextMessage(message.getPayload()) );
// 구문이 수행되어 메세지가 전달된 경우

chattingSock.onmessage = function(e){
	// 매개변수 e : 발생한 이벤트에 대한 정보를 담고있는 객체
	// e.data : 전달된 메세지 (message.getPayload())   (JSON 형태)

	// 전달 받은 메세지를 JS 객체로 변환
	const chatMessage = JSON.parse(e.data);  // JSON -> JS Object
	console.log(chatMessage);


	const li = document.createElement("li");

	const p = document.createElement("p");
	p.classList.add("chat");
	
					// 줄바꿈
	p.innerHTML = chatMessage.message.replace(/\\n/gm , "<br>" ) ; 
	// 내용


	const span = document.createElement("span");
	span.classList.add("chatDate");
	//span.innerText = chatMessage.createDate; // 날짜
	span.innerText = currentTime(); // 날짜

	// 내가 쓴 채팅 : span -> p
	// 남이 쓴 채팅 : p -> span

	// 내가 쓴 채팅일 경우
	if( chatMessage.memberNo ==  memberNo ){
		li.append(span, p);
		li.classList.add("myChat"); // 내가 쓴 글 스타일 적용

	}else{
		li.innerHTML = "<b>"  + chatMessage.memberNickname  +  "</b><br>";
		li.append(p, span);
	}


	// 채팅창
	const display = document.getElementsByClassName("display-chatting")[0];

	// 채팅창에 채팅 추가
	display.append(li);

	// 채팅창 제일 밑으로 내리기
	display.scrollTop = display.scrollHeight;
	// scrollTop : 스크롤 이동
	// scrollHeight : 스크롤되는 요소의 전체 높이
}



// 현재 시간 출력 함수
// 2022년 07월 07일 14:47:22
function currentTime(){
	const now = new Date();

	const time = now.getFullYear() + "년 " 
			   + addZero( now.getMonth() + 1 ) + "월 "
			   + addZero( now.getDate() ) + "일 "
			   + addZero( now.getHours()  ) + ":" 
			   + addZero( now.getMinutes() ) + ":" 
			   + addZero( now.getSeconds() ) + " "; 

	return time;
}

// 10보다 작을 경우 앞에 0을 붙이는 함수
function addZero(temp){
	return temp < 10 ? "0" + temp : temp;;
}