function commentsPage(){
	showPage('/page/comments.html');
}

function loginPage(){
	showPage('/page/login.html');
}

function registerPage(){
	showPage('/page/registered.html');
}

function showPage(page){
	$.get(page + "?t="+Date.now(),function(data,status){
		$('body').html(data);
	});
}

function checkLogin(){
	$.get("/login?t="+Date.now(),function(data,status){
		var loginUser = data;
		if(loginUser){
			$('#account').show();
			$('#loginBtn').hide();
			$('#usernameInfo').text(loginUser.username);
			$('#emailInf').text(loginUser.email);
		}else{
			$('#account').hide();
			$('#loginBtn').show();
		}
	});
}

function sendLoginPost(param){
	$.post("/login", param,
	        function(data, status) {
	           commentsPage();
	        }).error(function (data) {
	            Tip(data.responseText);
	        });
}

function sendCheckAccountPost(param, step){
	// 验证用户名,email 是否已存在
    $.post("/register/checkAccount", param,
        function(data, status) {
            step.nextStep();
        }).error(function (data) {
            Tip(data.responseText);
    });
}

function sendRegister(param, step){
	$.post("/register", param,
        function(data, status) {
           var yes=step.nextStep();
           function lazyGo() {
               var sec = $("#sec").text();
               $("#sec").text(--sec);
               if (sec > 0)
                   setTimeout(lazyGo, 1000);
               else
            	   loginPage();
           }
           $(function () {setTimeout(lazyGo, 1000); });
        }).error(function (data) {
            Tips(data.responseText);
        });
}



