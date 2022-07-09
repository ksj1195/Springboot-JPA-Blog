let index = {
    init: function () {
        // on("이벤트", 실행함수)
        // function(){}이 아닌 ()=>{}를 사용한 이유 = this를 바인딩하기 위함
        $("#btn-save").on("click", ()=>{
            this.save();
        });
        $("#btn-update").on("click", ()=>{
            this.update();
        });
    },

    save: function () {
        // alert("uesr의 save 함수 호출");
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        // console.log(data);
        
        // ajax 호출시 default가 비동기 호출임.
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 서버가 JSON을 리턴해주면 자동으로 자바 오브젝트로 변환
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body 데이터
            // console.log(data); => 자바스크립트 오브젝트
            // console.log(JSON.stringify(data)); => JSON 문자열
            contentType: "application/json; charset=utf8", // body 데이터가 어떤 타입인지(MIME 타입)
            dataType: "json" // 서버에 요청을 해서 응답이 왔을 때 기본적으로 모든것이 문자열(그런데 생긴게 json 형태? => javascript 오브젝트로 변경)
        }).done(function(resp) { // 응답 결과가 성공이면 done
            if(resp.status === 500) {
                alert("회원가입에 실패하였습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href="/"
            }
        }).fail(function(error) { // 응답 결과가 실패면 fail
            alert(JSON.stringify(error));
        });
    },

    update: function () {
        // alert("uesr의 save 함수 호출");
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
            // 회원가입 수행 요청
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data), // http body 데이터
            // console.log(data); => 자바스크립트 오브젝트
            // console.log(JSON.stringify(data)); => JSON 문자열
            contentType: "application/json; charset=utf8", // body 데이터가 어떤 타입인지(MIME 타입)
            dataType: "json" // 서버에 요청을 해서 응답이 왔을 때 기본적으로 모든것이 문자열(그런데 생긴게 json 형태? => javascript 오브젝트로 변경)
        }).done(function(resp) { // 응답 결과가 성공이면 done
            alert("회원 수정이 완료되었습니다.");
            // console.log(resp);
            location.href="/"
        }).fail(function(error) { // 응답 결과가 실패면 fail
            alert(JSON.stringify(error));
        });
    }
}

index.init();