layui.use(['form'],function(){
	var $=layui.$;
	var form=layui.form;
	var layer=layui.layer;
	
	 // 进行登录操作
	
    form.on('submit(btn_login)', function (data) {
        $.ajax({
   		 type:'get',
   		 url:'go/dologin',
   		 data:$(data.form).serialize(),
   		 success:function(result){
   			var code=result.code;
   			if(code==0){
   				layer.msg(result.msg);
   			}else if(code==1){
   				layer.msg('登录成功');
   			 window.location = 'go/index';
   			}
   		 }
   	 });
       
        return false;
    });
});