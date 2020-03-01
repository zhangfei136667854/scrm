var form;
var rate;
var upload;
layui.use([ 'table','util', 'form','upload','rate' ], function() {
	// layui是基于jQuery的,启用$符号。
	var $ = layui.$;
	 form = layui.form;
	 upload = layui.upload;
	 rate = layui.rate;
	var table = layui.table;
	var util = layui.util;
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
		url : 'contract', // 数据接口 layui会自动封装成  role/{page}/{limit}
		where : $('#form_search').serialize(),// 模拟额外的参数#目前引入搜索表单的数据
		page : true,// 开启分页
		cols: [[ //表头
		      {field: 'rowId', title: 'ID', width:60, sort: true, fixed: 'left'}
		      ,{field: 'contCode', title: '跟单编号',width:100}
		      ,{field: 'cusName', title: '客户名称',width:150}
		      ,{field: 'userName', title: '跟单人',width:80 }
		      ,{field: 'contTypeVal', title: '跟单方式',width:100}
		      ,{field: 'contInfo', title: '跟单内容',width:150 }
		      ,{field: 'nextContDate', title: '下次联系时间',width:170 ,templet: function (d) {
                  return util.toDateString(d.createDate);
              },}
		      ,{field: 'isRemind', title: '提醒状态',width:120 ,templet:"#isRemindBtpl"}
		      
		      ,{title: '操作', width: 200,templet:'#contractBtnTpl'}
		    ]],done: function(res, curr, count){
		    	$("[data-field='rowId']").css("display","none");
		    }
	})
	;});
 