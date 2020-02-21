$(document).ready(function(){
     $("#form_add_edit").on('change','#userLevel',function(){
    	 $("#parentCode option").remove();//id下面的标签清空
    	 var userLevel = $("#userLevel").val();
    	 $.ajax(
    			 url:'user/findByUserLevel/'+userLevel,
    	 dataType:'json',
    	 success:function(user){//要上一级角色用户的的信息
    		 if(user){
    			 option='<option value="'+user.parentCode+'">'+user.parentName+'</option>';
    			 
    		 }else{
    			 option='<option value="'+1+'">'+中享思途+'</option>';
    		 }
    		 
    		 $("#parentCode").append(option);
    	 }
    	 
    		 
    	 });
     });
});