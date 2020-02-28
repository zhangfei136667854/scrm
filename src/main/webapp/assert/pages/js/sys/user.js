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
	
	
});