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
	table.on('tool()',function(obj){
		var $tr = $(obj.tr);//获得当前行 tr 的 DOM 对象（如果有的话）
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		//通过data将要修改的数据的主键 取出
		var rowId = data.rowId;
		switch (layEvent) {
		case 'contract_add':
			//尝试取出修改时需要进行回调的方法名称,不是所有的页面都有。
			var callback4Edit = $tr.find('a[lay-event="contract_add"]').data('callback');
			//打开通用的layer弹层
			var url = $('#hideURL').val()+'/form';
			var title = $('#hideTitle').val()+'新增';
			//调用通用的弹出层的方法，成功后会回调done方法
			openBaseLayer(url,title).done(function(){
				//给表单赋值 form_add_edit = <form lay-filter="form_add_edit">
				//form.val("form_add_edit",data);
				$("#cusName").val(data.cusName);
				$("#cusCode").val(data.cusCode);
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
function handerEdit(){
	$.ajax({
		success : function(){
			upload.render({
				elem : '#contUpload',
				url : 'upload', //上传接口
				accept : 'file', //普通文件
				field: 'uploadFile', //设定文件域的字段名默认值是file
				data: {"uploadType":2},//
				done : function(res) {
					$('#contFile').val(res.data);
					
				}
			});

		}
	});
}
function initForm(){
	upload.render({
		elem : '#contUpload',
		url : 'upload', //上传接口
		accept : 'file', //普通文件
		field: 'uploadFile', //设定文件域的字段名默认值是file
		data: {"uploadType":2},//
		done : function(res) {
			$('#contFile').val(res.data);
			
		}
	});
}