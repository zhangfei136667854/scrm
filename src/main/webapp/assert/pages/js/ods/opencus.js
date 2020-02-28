var form;
var rate;
var upload;
layui.use([ 'table', 'form','upload','rate' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	 form = layui.form;
	 upload = layui.upload;
	 rate = layui.rate;
	var table = layui.table;
	//自定义表单校验
	form.verify({
		checkCustomerName:function(value, item){ //value：表单的值、item：表单的DOM对象
			//调用通用的唯一性校验的方法 #调用这个方法 一定要前面带 return
			return checkUnique(value,item,'customer/checkname');
		}
	});
	// 通过render方法开始渲染 talbe的数据
	table.render({
		elem : '#list_table', // 要绑定的页面元素
		url : 'customer', // 数据接口 layui会自动封装成  role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols: [[ //表头
		      {field: 'rowId', title: 'ID', sort: true, fixed: 'left'}
		      ,{field: 'cusCode', title: '客户编号', width:100}
		      ,{field: 'cusName', title: '客户名称',width:90 }
		      ,{field: 'cusPhone', title: '手机号码',width:120 }
		      ,{field: 'cusContact', title: '联系人',width:90 }
		      ,{field: 'cusTypeVal', title: '客户类型',width:100}
		      ,{field: 'cusFromVal', title: '客户来源',width:100 }
		      ,{field: 'cusInudsVal', title: '客户行业',width:100}
		      ,{field: 'cusQq', title: '联系QQ',width:110 }
		      ,{field: 'cusEmail', title: '电子邮箱',width:140}
		      ,{title: '操作', width: 200,templet:'#customerBtnTpl'}
		    ]],done: function(res, curr, count){
		    	$("[data-field='rowId']").css("display","none");
		    }
	});
	
});