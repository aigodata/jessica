<template>
	<div class="ai-layout-content">
		<router-view></router-view>
	</div>
</template>
<script>
	import managementModul from './contentInfo/organization-management';
	import classModul from './contentInfo/class-management';
	import teacherInfoModul from './contentInfo/teacher-info';
	import studentInfoModul from './contentInfo/student-info';
	export default {
		name: "layout-content",
		computed: {},
		watch: {},
		data() {
			return {};
		},
		components: {
			managementModul,
			classModul,
			teacherInfoModul,
			studentInfoModul
		},
		methods: {
			clear() {},
			load() {}
		},
		mounted() {
			this.load();
		},
		destroyed() {
			this.clear();
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
