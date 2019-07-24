<template>
	<div class="ai-layout-content">
		<div class="ai-layout-content-left">
			<!-- 搜索框 -->
			<div class="ai-layout-content-left-search">
				<el-input placeholder="请输入内容" v-model="inputValue">
					<i slot="prefix" class="el-input__icon el-icon-search"></i>
					<i class="el-input__icon iconfont iconhuiche" slot="suffix">
					</i>
				</el-input>
			</div>
			<!-- 树形菜单 -->
			<div class="ai-layout-content-list-tree">
				<el-tree :data="data" node-key="id" default-expand-all :expand-on-click-node="false" :render-content="renderContent">
				</el-tree>
			</div>
		</div>
		<div class="ai-layout-content-right">
			<!-- 组织管理 -->
			<managementModul />
			<!-- 班级管理 -->
			<!-- <classModul /> -->
			<!-- 教师信息 -->
			<!-- <teacherInfoModul /> -->
			<!-- 学生信息 -->
			<!-- <studentInfoModul /> -->
		</div>
	</div>
</template>

<script>
	import managementModul from './contentInfo/organization-management';
	import classModul from './contentInfo/class-management';
	import teacherInfoModul from './contentInfo/teacher-info';
	import studentInfoModul from './contentInfo/student-info';
	export default {
		name: "layout-content",
		data () {
			const data = [
				{
					id: 1,
					label: '铁道职业技术学院',
					children: [{
						id: 1 - 1,
						label: '校领导',
						children: []
					}, {
						id: 1 - 2,
						label: '学生处',
						children: []
					}, {
						id: 1 - 3,
						label: '招生办',
						children: []
					}, {
						id: 1 - 4,
						label: '教导处',
						children: []
					}, {
						id: 1 - 5,
						label: '办公室',
						children: []
					}, {
						id: 1 - 6,
						label: '总务处',
						children: []
					}, {
						id: 1 - 7,
						label: '后勤部',
						children: []
					}]
				}];
			return {
				inputValue: "",
				data: JSON.parse(JSON.stringify(data))
			}
		},
		components: {
			managementModul,
			classModul,
			teacherInfoModul,
			studentInfoModul
		},
		methods: {
			renderContent (h, { node, data, store }) {
				return (
					<span class="custom-tree-node">
						<i class="iconfont iconbaohudi_wenjianjia ai-layout-content-tree-icon"></i>
						<span>{node.label}</span>
						<span class="tree_edit">
							<el-tooltip class="item" effect="dark" content="新增" placement="top">
								<i class="iconfont iconxinzeng" on-click={() => this.appendTree(data)}></i>
							</el-tooltip>
							<el-tooltip class="item" effect="dark" content="编辑" placement="top">
								<i class="iconfont iconxiugai" on-click={() => this.editTree(data)}></i>
							</el-tooltip>
							<el-tooltip class="item" effect="dark" content="删除" placement="top">
								<i class="iconfont iconshanchu" on-click={() => this.deleteTree(node, data)}></i>
							</el-tooltip>
						</span>
					</span>
				);
			},
			appendTree () { },
			editTree () { },
			deleteTree () { }
		}
	};
</script>

<style>
	.ai-layout-content::after {
		content: "";
		display: block;
		clear: both;
	}
	/* 左侧目录 */
	.ai-layout-content .ai-layout-content-left {
		width: 220px;
		height: 100%;
		float: left;
		border-top: 1px solid #d3d3d3;
		border-right: 1px solid #d3d3d3;
		padding: 20px 0;
	}
	.ai-layout-content .ai-layout-content-left .ai-layout-content-left-search {
		padding: 0 20px;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-left-search
		.el-input__suffix {
		width: 32px;
	}
	.ai-layout-content .ai-layout-content-left .ai-layout-content-list-tree {
		margin-top: 20px;
		border-top: 1px solid #ededed;
		padding: 20px 0;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-list-tree
		.ai-layout-content-tree-icon {
		margin-right: 5px;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-list-tree
		.el-tree
		.custom-tree-node
		.tree_edit {
		display: none;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-list-tree
		.el-tree
		.el-tree-node__content:hover
		.tree_edit {
		display: inline-block;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-list-tree
		.el-tree
		.custom-tree-node {
		width: 100%;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-list-tree
		.el-tree
		.el-tree-node {
		line-height: 32px;
		font-size: 12px;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-list-tree
		.el-tree
		.el-tree-node__content {
		height: 32px !important;
		line-height: 32px !important;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-list-tree
		.el-tree
		.tree_edit {
		float: right;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-list-tree
		.el-tree
		.tree_edit
		i {
		width: 14px;
		height: 14px;
		font-size: 14px;
		margin-right: 10px;
	}
	.ai-layout-content
		.ai-layout-content-left
		.ai-layout-content-list-tree
		.el-tree
		.tree_edit
		i:hover {
		color: #4b9fe9;
	}
	.el-tooltip__popper.is-dark {
		background: linear-gradient(
			-44deg,
			rgba(75, 159, 233, 1),
			rgba(90, 174, 248, 1)
		) !important;
	}
	.el-tooltip__popper .popper__arrow::after {
		border-top-color: #4b9fe9 !important;
	}
	/* 右侧内容 */
	.ai-layout-content .ai-layout-content-right {
		width: calc(100% - 220px);
		overflow-y: auto;
		height: 100%;
		float: left;
		border-top: 1px solid #d3d3d3;
	}
</style>
