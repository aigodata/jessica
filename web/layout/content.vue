<template>
	<div class="ai-layout-content">
		<div class="ai-layout-content-left">
			<!-- 搜索框 -->
			<el-input placeholder="请输入内容" v-model="inputValue">
				<i slot="prefix" class="el-input__icon el-icon-search"></i>
				<i class="el-input__icon iconfont iconhuiche" slot="suffix">
				</i>
			</el-input>
			<!-- 树形菜单 -->
			<div>
				<el-tree :data="data" show-checkbox node-key="id" default-expand-all :expand-on-click-node="false" :render-content="renderContent">
				</el-tree>
			</div>
		</div>
		<div class="ai-layout-content-right">right</div>
	</div>
</template>

<script>
	export default {
		name: "layout-content",
		data () {
			return {
                inputValue: "",
                data:[]
			}
        },
        methods: {
            renderContent(){}
        }
	};
</script>

<style>
	.ai-layout-content {
		position: relative;
		height: 100%;
	}
	.ai-layout-content div {
		box-sizing: border-box;
	}
	.ai-layout-content::after {
		content: "";
		display: block;
		clear: both;
	}
	/* 左侧目录 */
	.ai-layout-content .ai-layout-content-left {
		width: 30%;
		height: 100%;
		float: left;
		border-top: 1px solid #d3d3d3;
		border-right: 1px solid #d3d3d3;
		padding: 20px;
	}
	/* 右侧内容 */
	.ai-layout-content .ai-layout-content-right {
		width: 70%;
		height: 100%;
		float: left;
		border-top: 1px solid #d3d3d3;
	}
</style>
