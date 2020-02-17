<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="assert/layui/css/layui.css">
    <style>
        .hide {
            display: none;
        }
    </style>
    <script src="assert/layui/layui.js"></script>
</head>
<body style="padding: 18px;">
    <div class="layui-form">
        <div class="layui-form-item">
            <label for="" class="layui-form-label">默认图标</label>
            <div class="layui-input-block">
                <input type="text" id="iconPicker" lay-filter="iconPicker" class="hide">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">自定义图标</label>
            <div class="layui-input-block">
                <input type="text" id="iconPicker2" value="layui-icon-face-smile-fine" lay-filter="iconPicker2" class="hide">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">弹窗选择</label>
            <div class="layui-input-block">
                <button id="layer" class="layui-btn">点击打开弹窗</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="" class="layui-form-label">从JSON读取值</label>
            <div class="layui-input-block">
                <input type="text" id="iconPicker4" lay-filter="iconPicker4" class="hide">
            </div>
        </div>
    </div>

    <div id="layer-modal" style="display: none;">
        <div style="padding: 18px;">
            <input type="text" id="iconPicker3" lay-filter="iconPicker3" value="layui-icon-face-smile-fine" class="layui-input">  
            <br>
            <input type="text" id="iconPicker3-2" lay-filter="iconPicker3-2" class="layui-input">  
            <button id="btnSubmit3-2" class="layui-btn">获取当前值</button>
        </div>
    </div>
    <script>
  //此处引入treeTable的layui插件 次配置应该在项目中运行一次即可，不可以重复加载。
    layui.config({
    	base : 'assert/layui/'//你存放新模块的目录，注意，不是layui的模块目录
    }).extend({
    	iconPicker: 'iconPicker/iconPicker'
    });
        layui.use(['iconPicker', 'form', 'layer'], function () {
            var iconPicker = layui.iconPicker,
                form = layui.form,
                layer = layui.layer,
                $ = layui.$;
            
            iconPicker.render({
                // 选择器，推荐使用input
                elem: '#iconPicker',
                // 数据类型：fontClass/unicode，推荐使用fontClass
                type: 'fontClass',
                // 是否开启搜索：true/false，默认true
                search: true,
                // 是否开启分页：true/false，默认true
                page: true,
                // 每页显示数量，默认12
                limit: 12,
                // 点击回调
                click: function (data) {
                    console.log(data);
                },
                // 渲染成功后的回调
                success: function(d) {
                    console.log(d);
                }
            });


// ********************** 示例2 - 默认值 *****************************************
            /* iconPicker.render({
                // 选择器，推荐使用input
                elem: '#iconPicker2',
                // 数据类型：fontClass/unicode，推荐使用fontClass
                type: 'fontClass',
                // 是否开启搜索：true/false
                search: true,
                // 是否开启分页
                page: true,
                // 每页显示数量，默认12
                limit: 12,
                // 点击回调
                click: function (data) {
                    console.log(data);
                },
                // 渲染成功后的回调
                success: function(d) {
                    console.log(d);
                }
            }); */

// ********************** 示例2 END *****************************************


// ********************* 示例3 - 弹窗中打开 **********************************
            iconPicker.render({
                elem: '#iconPicker3',
                type: 'fontClass',
                search: true,
                page: true,
                limit: 12,
                click: function (data) {
                    console.log(data);
                },
                success: function(d) {
                    console.log(d);
                }
            });

            iconPicker.render({
                elem: '#iconPicker3-2',
                type: 'fontClass',
                search: true,
                page: true,
                limit: 12,
                click: function (data) {
                    console.log(data);
                },
                success: function(d) {
                    console.log(d);
                }
            });

            $('#layer').click(function(){
                layer.open({
                    type: 1,
                    area: ['500px', '400px'],
                    content: $('#layer-modal'),
                    success: function(layero){
                        iconPicker.checkIcon('iconPicker3-2', 'layui-icon-cellphone');
                    }
                });
            });

            $('#btnSubmit3-2').click(function(){
                alert($('#iconPicker3-2').val());
            });

            

// ********************* 示例3 END **********************************

// ********************* 示例4 - 异步设置值 **********************************
          /*   iconPicker.render({
                elem: '#iconPicker4',
                type: 'fontClass'
            }); */

           /*  $.getJSON("json/example.json",function(result){
                console.log(result);
                var icon = result.data[0].icon;
                console.log(icon);
                iconPicker.checkIcon('iconPicker4', icon);
            }); */

// ********************* 示例4 END **********************************


        });
    </script>
</body>
</html>