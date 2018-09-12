<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <title>测试</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
</head>
<body>
<div id="app">
    <el-button
            plain
            @click="open">
        可自动关闭
    </el-button>
    <el-button
            plain
            @click="open2">
        不会自动关闭
    </el-button>
    <el-button
            plain
            @click="closeAll">
        关闭所有的弹出框
    </el-button>
</div>
<!-- 引入组件库 -->
<script type="text/javascript" src="${ctx}/static/common/js/vue.js"></script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>

<script type="text/javascript">

    new Vue({
        el: "#app",
        data: {
            dialogArr : []
        },
        methods: {
            onClick(){
                console.log(this);
            },
            clickBtn(){
                console.log("按钮");
            },
            closeAll(){
                for(var i = 0; i < this.dialogArr.length; i++){
                    this.dialogArr[i].close();
                }
            },
            open() {
                this.dialogArr.push(this.$notify.info({
                    title: '提示',
                    message: '这是一条会自动关闭的消息'
                }));
            },
            open2() {
                const h = this.$createElement;
                this.dialogArr.push(this.$notify({
                    title: '标题名称',
                    message: h('p', null, [
                        h('span', null, '内容可以是 '),
                        h('a', {
                            on:{
                                click:this.onClick
                            }
                        }, "标签"),
                        h('button', {
                            on:{
                                click:this.clickBtn
                            }
                        }, "按钮")
                    ]),
                    duration: 0
                }));
            }
        }
    });
</script>

</body>

</html>