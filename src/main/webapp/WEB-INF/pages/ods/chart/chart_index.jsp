<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- <div class="layui-row layui-col-space15"> -->
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
<!-- </div> -->

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

<script type="text/javascript" src="assert/pages/js/chartjs/Chart.min.js"></script>
<script type="text/javascript" src="assert/pages/js/chartjs/utils.js"></script>
<script type="text/javascript" src="assert/pages/js/chartjs/Chart.js"></script>

 