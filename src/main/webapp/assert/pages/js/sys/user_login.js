layui.use('form', function() {
	var form =layui.form;
	var $ = layui.$;
	form.on('submit(but_submit)', function(data) {
		$.ajax({
			type:"get",
			url:'user/userLogin',
			data:$(data.form).serialize(),
			success:function(user1){
				if(user1){
					window.location.href="sys/index";
				}
				}
			});
			return false ;
		});
	});
	
