<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="layui-row layui-col-space15">
<div class="layui-col-md6">
<div class="layui-card">
<div class="layui-card-header">跟单方式柱状图</div>
<div class="layui-card-body">
<div  class="layui-carousel layadmin-carousel layadmin-dataView" >
<div class="wrapper"><canvas id="chart-1"></canvas></div>
</div>
</div>
</div>
</div>
</div>
<div class="layui-row layui-col-space15">
<div class="layui-col-md6">
<div class="layui-card">
<div class="layui-card-header">客户来源分别图</div>
<div class="layui-card-body">
<div  class="layui-carousel layadmin-carousel layadmin-dataView" >
<div class="wrapper"><canvas id="chart-2"></canvas></div>
</div>
</div>
</div>
</div>
</div>




<div  class="layui-input-inline" ><canvas id="myChart" width="400" height="400"></canvas></div>
<div  class="layui-input-inline" ><canvas id="myChart1" width="600" height="400"></canvas></div>
<script type="text/javascript" src="assert/pages/js/chartjs/Chart.min.js"></script>
<script type="text/javascript" src="assert/pages/js/chartjs/utils.js"></script>
<script type="text/javascript" src="assert/pages/js/chartjs/Chart.js"></script>

<script>
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    
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
});
</script>
<script>
var ctx = document.getElementById('myChart1').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'doughnut',//line线型图//pie饼图//
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
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
});
</script>

 