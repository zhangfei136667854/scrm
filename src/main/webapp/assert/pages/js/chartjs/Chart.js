var $ ;
layui.use('layer',function(){
	$=layui.$
initBarData();
initPieData();
	
});
function initBarData(){
	var config = {
			type: 'bar',
		    data: {
		       // labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple',
				// 'Orange'],
		        datasets: [{
		            label: '跟单方式',
		           // data: [12, 19, 3, 5, 2, 3],
		            backgroundColor: [
		                'rgba(255, 99, 132, 0.2)',
		                'rgba(54, 162, 235, 0.2)',
		                'rgba(255, 206, 86, 0.2)',
		                'rgba(75, 192, 192, 0.2)',
		                'rgba(153, 102, 255, 0.2)',
		                'rgba(255, 159, 64, 0.2)'
		            ],
		            borderColor: [
		                'rgba(255, 99, 132, 1)',
		                'rgba(54, 162, 235, 1)',
		                'rgba(255, 206, 86, 1)',
		                'rgba(75, 192, 192, 1)',
		                'rgba(153, 102, 255, 1)',
		                'rgba(255, 159, 64, 1)'
		            ],
		            borderWidth: 1
		        }]
	},  
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
};
	var contType_chart = new Chart('chart-1',config);
	$.ajax({
	url:"chart/contType",
	success:function(typeList){
			if(typeList){
				var _data=contType_chart.data;
				var _datasets =_data.datasets[0];
				$.each(typeList,function(index,type){
					_datasets.data[index]=type.dataCount;
					_data.labels[index]=type.keyVal;
				});
			}
		contType_chart.update();
	}
	});
	}
	

function initPieData(){
	var config = {
			 type: 'doughnut',//line线型图//pie饼图//
			    data: {
			       // labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
			        datasets: [{
			            label: '客户来源',
			           // data: [12, 19, 3, 5, 2, 3],
			            backgroundColor: [
			                'rgba(255, 99, 132, 0.2)',
			                'rgba(54, 162, 235, 0.2)',
			                'rgba(255, 206, 86, 0.2)',
			                'rgba(75, 192, 192, 0.2)',
			                'rgba(153, 102, 255, 0.2)',
			                'rgba(255, 159, 64, 0.2)'
			            ],
			            borderColor: [
			                'rgba(255, 99, 132, 1)',
			                'rgba(54, 162, 235, 1)',
			                'rgba(255, 206, 86, 1)',
			                'rgba(75, 192, 192, 1)',
			                'rgba(153, 102, 255, 1)',
			                'rgba(255, 159, 64, 1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero: true
			                }
			            }]
			        }
			    }	
	};
	var cusFrom_chart = new Chart('chart-2',config);
	$.ajax({
	url:"chart/cusForm",
	success:function(fromList){
			if(fromList){
				var _data=cusFrom_chart.data;
				var _datasets =_data.datasets[0];
				$.each(fromList,function(index,from){
					_datasets.data[index]=from.dataCount;
					_data.labels[index]=from.keyVal;
				});
			}
			cusFrom_chart.update();
	}
	});
	
	
	
}