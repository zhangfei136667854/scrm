layui.use(['form'],function(){
	var $=layui.$;
	var form=layui.form;
	var layer=layui.layer;
	//绑定表单提交
	form.on('submit(but_sysconfig_set)',function(){
		$.ajax({
			type:'put',
			url:'sysconfig',
			data:$('#form_sysconfig').serialize(),
			success:function(result){
				if(result){
					layer.msg('系统参数设置成功');
				}
			}
		});
		return false;
	});
});