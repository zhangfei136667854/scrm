layui.use(['table','util','form'],function(){
	var $ = layui.$;
	var form = layui.form;
	var table = layui.table;
	var util = layui.util;
	// 自定义校验
	form.verify({
		// 至少选中一个
		onechecked:function(value,item){
			// 得到需要校验的所有的被选中的checkbox的数量
			var checkedCount = $(item).find(':checkbox:checked').length;
			if(checkedCount==0){
				$(item).addClass('layui-form-danger');
				setTimeout(() => {
					$(item).removeClass('layui-form-danger');
				}, 2000);
				return '请至少选中一个角色信息';
			}
		},
		checkusercode:function(value,item){
			// 调用通用的唯一性校验的方法 #调用这个方法 一定要前面带 return
			return checkUnique(value,item,'user/checkcode');
		}
	});
	form.render();
	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'user', // 数据接口 layui会自动封装成 role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols: [[ // 表头
		      {field: 'rowId', title: 'ID', sort: true, fixed: 'left'}
		      ,{field: 'userKind', title: '用户类型',width:100,templet:'#userKindTpl'}
		      ,{field: 'userLevel', title: '用户级别',width:100,templet:'#userLevelTpl'}
		      ,{field: 'userName', title: '用户名称',width:100}
		      ,{field: 'userCode', title: '登录账号',width:130} // 此次不设置width 让其自动适应
		      ,{field: 'userPhone', title: '手机号码',width:130}
		      ,{field: 'loginCount', title: '登陆次数',width:90}
		      ,{field: 'loginDate', title: '最后登录时间',width:160,templet: function (d) {
                  return util.toDateString(d.createDate);
              }, }
		      ,{field: 'isLock', title: '用户状态',templet:'#isLockTpl',width:100}
		      ,{title: '操作', width: 140,templet:'#roleBtnTpl'}
		    ]],done: function(res, curr, count){
		    	$("[data-field='rowId']").css("display","none");}
	});
	// 绑定用户级别事务条
	form.on('select(userLevel)',function(data){
   	 $("#parentCode option").remove();// id下面的标签清空
   	 var userLevel = data.value;
   	 $.ajax({
   			 url:'user/findByUserLevel/'+userLevel,
   			 dataType:'html',
   			 success:function(htmlData){// 要上一级角色用户的的信息
   				 $("#parentCode").html(htmlData);
   				form.render('select');
   				 
   	 }
  })
});
	 //监听开关事件
    form.on('switch(switchTest)', function (data) {
    	var isLock=1;
    	var rowId=data.value;
        console.log(data.elem.checked); //开关是否开启，true或者false
        if(data.elem.checked){
        	isLock=0;
        }
		$.ajax({
			type:'put',
			url:'user/dolock',
			data:"rowId="+rowId+"&isLock="+isLock,
			success:function(result){
				if(result){
					table.reload('list_table');
				}
			}
		});
    });
    table.on('tool()',function(obj){
		var $tr = $(obj.tr);//获得当前行 tr 的 DOM 对象（如果有的话）
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		//通过data将要修改的数据的主键 取出
		var rowId = data.rowId;
		switch (layEvent) {
		case 'user_edit':
			//尝试取出修改时需要进行回调的方法名称,不是所有的页面都有。
			var callback4Edit = $tr.find('a[lay-event="edit"]').data('callback');
			//打开通用的layer弹层
			var url = $('#hideURL').val()+'/form';
			var title = $('#hideTitle').val()+'修改';
			//调用通用的弹出层的方法，成功后会回调done方法
			openBaseLayer(url,title).done(function(){
				
				//给表单赋值 form_add_edit = <form lay-filter="form_add_edit">
				form.val("form_add_edit",data);
				$("#pass_div").attr('style','display:none');
				if(data.parentName){
					$("#parentCode").html('<option value="'+data.parentCode+'">'+data.parentName+'</option>');

				}else{
					$("#parentCode").html('<option value="'+1+'">'+"中享思途"+'</option>');

				}
				form.render('select');
				//让密码那一栏隐身
				//为了唯一性的校验，修改的时候设置一个原来的数据
				//$.data('old')  = <input data-old=''/>
				//处理如果表单中如果有需要进行唯一性的校验
				$.each($('.check-unique'),function(index,item){
					var $item = $(item);
					var input_name = $item.attr('name');
					$item.data('old',data[input_name]);
				});
				// 让form表单渲染一下。 form_add_edit = <form lay-filter="form_add_edit">
				form.render(null, 'form_add_edit');
				
				//判断如果 修改form页面弹出后，需要回调的方法名称不为空。
				if(callback4Edit){
					//尝试调用一下额外配置的为修改使用的回调函数 
					eval(callback4Edit);
				}
			});
			break;
		
		
		}
	});
	
	
});