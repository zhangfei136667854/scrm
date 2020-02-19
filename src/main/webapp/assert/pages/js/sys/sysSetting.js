layui.use(['form','layer'],function(){
	var $ = layui.$;
	var form = layui.form ;
	var layer = layui.layer;
	//绑定表单提交
	form.on('submit(but_resource_submit)',function(data){
		
		$.ajax({
			type:'put',
			url:"sysSetting",
			data:$(data.form).serialize(),
			success:function(result){
				if(result){
					layer.msg("系统参数配置成功");
				}
			}
		});
		return false ; 
	});
});