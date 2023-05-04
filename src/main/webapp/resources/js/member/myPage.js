// 내 정보 수정 유효성 검사
function infoValidate(){

    const memberNickname = document.getElementById("memberNickname");
    const memberTel = document.getElementById("memberTel");

    const regExp1 = /^[a-zA-Z0-9가-힣]{2,10}$/;        // 닉네임 정규식
    const regExp2 = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/; // 전화번호 정규식

    // 닉네임 유효성 검사
    if(memberNickname.value.length == 0){ // 미작성 시 : 닉네임을 입력해주세요.
        alert("닉네임을 입력해주세요.");
        memberNickname.focus();
        return false;
    }

    if(!regExp1.test(memberNickname.value)){ // 유효하지 않은 경우
        alert("닉네임은 영어/숫자/한글 2~10 글자 사이로 작성해주세요.");
        memberNickname.focus();
        return false;
    }

    // 전화번호 유효성 검사
    if(memberTel.value.length == 0){ // 미작성 시
        alert("전화번호를 입력해주세요.(- 제외)");
        memberTel.focus();
        return false;
    }

    if(!regExp2.test(memberTel.value)){ // 유효하지 않은 경우
        // alert(" 전화번호 형식이 올바르지 않습니다.");
        // memberTel.focus();
        // return false;

        return printAlert(memberTel, "전화번호 형식이 올바르지 않습니다.");
    }

    return true; // true를 반환해서 form 제출 수행
}


// 경고 출력 + 포커스 + false 반환  함수
function printAlert(el, message){ // 매개변수 el은 요소
    alert(message);
    el.focus();
    return false;
}



// 비밀번호 변경 제출 시 유효성 검사
function changePwValidate(){

    // 비밀번호 변경 관련 input 요소 얻어오기
    const currentPw = document.getElementsByName("currentPw")[0];
    
    //const currentPw = document.getElementById("currentPw");

    const newPw = document.getElementsByName("newPw")[0];
    const newPwConfirm = document.getElementsByName("newPwConfirm")[0];

    // 비밀번호 정규표현식
    const regEx = /^[\w!@#_-]{6,30}$/;

    // 현재 비밀번호 미작성
    if(currentPw.value.trim().length == 0){
        /*alert("현재 비밀번호를 입력해주세요.");
        currentPw.focus();
        return false;*/

        return printAlert(currentPw, "현재 비밀번호를 입력해주세요.");
    }

    // 새 비밀번호
    // 미작성
    if(newPw.value.trim().length == 0){
        alert("새 비밀번호를 입력해주세요.");
        newPw.focus();
        return false;
    }

    // 유효하지 않은 경우
    if(!regEx.test(newPw.value)){
        alert("영어, 숫자, 특수문자(!,@,#,-,_) 6~30 글자 사이로 작성해주세요.");
        newPw.focus();
        return false;
    }


    // 새 비밀번호 확인
    // 미작성
    if(newPwConfirm.value.trim().length == 0){
        return printAlert(newPwConfirm, "새 비밀번호 확인을 입력해주세요.");
    }


    // 새 비밀번호 != 새 비밀번호 확인
    if(newPw.value != newPwConfirm.value){
        return printAlert(newPwConfirm, "새 비밀번호가 일치하지 않습니다.");
    }

    return true; // 위 조건을 모두 수행하지 않은 경우 true 반환
}




// 회원 탈퇴 유효성 검사
function secessionValidate(){

    const memberPw = document.getElementById("memberPw");
    const agree = document.getElementById("agree");

    // 비밀번호 미작성
    if(memberPw.value.trim().length == 0){
        alert("비밀번호를 입력해주세요.");
        memberPw.focus();
        return false;
    }

    // 약관 동의 체크 여부
    // - 체크박스요소.checked  : 체크 시 true, 해제 시 false 반환

    if( !agree.checked ){ // 체크를 안했을 때
        alert("약관 동의 후 탈퇴 버튼을 클릭해주세요.");
        agree.focus();
        return false;
    }

    // 정말 탈퇴할지 확인
    // - window.confirm("내용") : 대화 상자에 확인/취소 생성
    //      확인 클릭 시 true / 취소 클릭 시 false
    //      window는 생략 가능
    
    if( !confirm("정말 탈퇴 하시겠습니까?") ){ //  취소를 누른 경우
        return false;
    }

    return true;
}



// 회원 프로필 이미지 변경(미리보기)
const inputImage = document.getElementById("input-image");

if(inputImage != null){ // inputImage 요소가 화면에 존재 할 때
 
    // input type="file" 요소는 파일이 선택 될 때 change 이벤트가 발생한다.
    inputImage.addEventListener("change", function(){
       
        // this : 이벤트가 발생한 요소 (input type="file")

        // files : input type="file"만 사용 가능한 속성으로
        //         선택된 파일 목록(배열)을 반환
        //console.log(this.files)
        //console.log(this.files[0]) // 파일목록에서 첫 번째 파일 객체를 선택

        if(this.files[0] != undefined){ // 파일이 선택되었을 때

            const reader = new FileReader();
            // 자바스크립트의 FileReader
            // - 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 사용하는 객체

            reader.readAsDataURL(this.files[0]);
            // FileReader.readAsDataURL(파일)
            // - 지정된 파일의 내용을 읽기 시작함.
            // - 읽어오는게 완료되면 result 속성 data:에 
            //   읽어온 파일의 위치를 나타내는 URL이 포함된다.  
            //  -> 해당 URL을 이용해서 브라우저에 이미지를 볼 수 있다.


            // FileReader.onload = function(){}
            // - FileReader가 파일을 다 읽어온 경우 함수를 수행
            reader.onload = function(e){ // 고전 이벤트 모델
                // e : 이벤트 발생 객체
                // e.target : 이벤트가 발생한 요소(객체) -> FileReader
                // e.target.result : FileReader가 읽어온 파일의 URL

                // 프로필 이미지의 src 속성의 값을 FileReader가 읽어온 파일의 URL로 변경
                const profileImage = document.getElementById("profile-image");

                profileImage.setAttribute("src", e.target.result);
                // -> setAttribute() 호출 시 중복되는 속성이 존재하면 덮어쓰기

                document.getElementById("delete").value = 0;
                // 새로운 이미지가 선택 되었기 때문에 1 -> 0(안눌러짐 상태)으로 변경
            }

        }
    });
}





// 이미지 선택 확인
function profileValidate(){
    const inputImage = document.getElementById("input-image");

    const del = document.getElementById("delete"); // hidden 타입


    if( inputImage.value == ""  &&  del.value == 0 ){ 
        // 빈문자열 == 파일 선택 X / del의 값이 0 == x를 누르지도 않았다
        // --> 아무것도 안하고 변경버튼을 클릭한 경우

        alert("이미지를 선택한 후 변경 버튼을 클릭해주세요.");
        return false;
    }

    return true;
}




// 프로필 이미지 옆 x버튼 클릭 시
document.getElementById("delete-image").addEventListener("click", function(){
    // 0 : 안눌러짐
    // 1 : 눌러짐

    const del = document.getElementById("delete");

    if(del.value == 0){ // 눌러지지 않은 경우

        // 1) 프로필 이미지를 기본 이미지로 변경
        document.getElementById("profile-image").setAttribute("src", contextPath + "/resources/images/user.png");                     

        // 2) input type="file"에 저장된 값(value)에 "" 대입 
        document.getElementById("input-image").value = "";

        del.value = 1; // 눌러진걸로 인식
    }

});


// 다음 주소 API

// 우편번호 찾기 화면을 넣을 element
var element_layer = document.getElementById('layer');

function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_layer.style.display = 'none';
}

function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_layer.style.display = 'none';
        },
        width : '100%',
        height : '100%',
        maxSuggestItems : 5
    }).embed(element_layer);

    // iframe을 넣은 element를 보이게 한다.
    element_layer.style.display = 'block';

    // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
    initLayerPosition();
}

// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
function initLayerPosition(){
    var width = 500; //우편번호서비스가 들어갈 element의 width
    var height = 400; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    element_layer.style.width = width + 'px';
    element_layer.style.height = height + 'px';
    element_layer.style.border = borderWidth + 'px solid';
    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
    element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
}



