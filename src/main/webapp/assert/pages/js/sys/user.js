layui.use(['form','util','table'],function(){
	var $ = layui.$;
	var form = layui.form;
	var table = layui.table;
	var util = layui.util;
	form.render();
	//自定义校验
	/*form.verify({
		checkusercode:function(value,item){
			//调用通用的唯一性校验的方法 #调用这个方法 一定要前面带 return
			return checkUnique(value,item,'user/checkCode');
		}
	});*/
	//table加载
	table.render({
		elem:"#list_table",//要绑定的页面元素
		url:'user',//数据接口layui会自动封装成user/{page}/{limit}
		where:$('#form_search').serialize(),//搜索表单数据
	page:true ,//开启分页
	cols:[[
		  {field: 'rowId', title: 'ID', width:80, sort: true, fixed: 'left'}
	      ,{field: 'userKind', title: '员工类型', width:100,templet:'#userKindTpl'}
	      ,{field: 'userLevel', title: '员工级别', width:120,templet:'#userLevelTpl'}
	      ,{field: 'userName', title: '员工名称',width:100}
	      ,{field: 'userCode', title: '登陆账号',width:120}
	      ,{field: 'loginCount', title: '登陆次数',width:120}
	      ,{field: 'loginDate', width:150,title: '最后登陆时间',templet: function (d) {
              return util.toDateString(d.createDate);
          },}
	      ,{field: 'isLock', title: '员工状态',width:120 ,templet:'#isLockTpl'}//此次不设置width 让其自动适应
	      ,{title: '操作', width: 180,templet:'#userBtnTpl'}
		
	]],done: function(res, curr, count){
		$("[data-field='rowId']").css('display','none');
	}
	});
	
	  form.on('select(userLevel)',function(data){
		   
	    	 $("#parentCode option").remove();//id下面的标签清空
	    	 var userLevel = data.value;
	    	 $.ajax({
	    			 url:'user/findByUserLevel/'+userLevel,
	    			 dataType:'html',
	    			 success:function(htmlData){//要上一级角色用户的的信息
	    				 $("#parentCode").html(htmlData);
	    				form.render('select');
	    				 
	    	 }
	   })
	});
	  
	  table.on('tool()',function(obj){
			 var data = obj.data; //获得当前行数据
			 var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			 //var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
			 //通过data将要修改的数据的主键 取出
			 var rowId = data.rowId;
			
			switch (layEvent) {
			case 'user_edit':
				//打开通用的layer弹层
				var url = $('#hideURL').val()+'/form';
				var title = $('#hideTitle').val()+'修改';
				//调用通用的弹出层的方法，成功后会回调done方法
				openBaseLayer(url,title).done(function(){
					//用ajax的方式再根据id查询要修改的对象的数据
					$.ajax({
						type:'get',
						url:$('#hideURL').val()+'/'+rowId,
						success:function(obj){
							//给表单赋值 form_add_edit = <form lay-filter="form_add_edit">
							form.val("form_add_edit",obj);
							$("#userPass").attr('style','display:none');
							$("#parentCode").html('<option value="'+obj.parentCode+'">'+obj.parentName+'</option>');
							form.render('select');
							//为了唯一性的校验，修改的时候设置一个原来的数据
							//$.data('old')  = <input data-old=''/>
							//处理如果表单中如果有需要进行唯一性的校验
							$.each($('.check-unique'),function(index,item){
								var $item = $(item);
								var input_name = $item.attr('name');
								$item.data('old',obj[input_name]);
							});
							// 让form表单渲染一下。 form_add_edit = <form lay-filter="form_add_edit">
							form.render(null, 'form_add_edit');
						}
					});
				});
				break;
			}
			});
	  form.on('switch(switchTest)',function(data){
			  var isLock = data.value;
			  alert(isLock);
				  $.ajax({
					  type:"put",
					  url:"user/"+isLock,
					  success:function(result){
						  if(result){
							  layer.msg("用户状态设置成功");  
						  }
						  
					  }
				  });
			
			  
		
		  
	  });
	
	
	
	  
	
});