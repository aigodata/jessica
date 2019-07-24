<template>
	<div>
		<div class="ai-layout-content-left">
			<!-- 搜索框 -->
			<el-input placeholder="请输入内容" v-model="inputValue">
				<i slot="prefix" class="el-input__icon el-icon-search"></i>
				<i class="el-input__icon iconfont iconhuiche" slot="suffix"></i>
			</el-input>
			<!-- 树形菜单 -->
			<div class="ai-layout-content-list-tree">
				<el-tree
					:data="data"
					node-key="id"
					default-expand-all
					:expand-on-click-node="false"
					:render-content="renderContent"
				></el-tree>
			</div>
		</div>
		<div class="ai-layout-content-right">right</div>
	</div>
</template>

<script>
	export default {
		computed: {},
		watch: {},
		data() {
			const data = [
				{
					id: 1,
					label: "铁道职业技术学院",
					children: [
						{
							id: 4,
							label: "校领导",
							children: [
								{
									id: 9,
									label: "学生处"
								},
								{
									id: 10,
									label: "招生办"
								}
							]
						}
					]
				}
			];
			return {
				inputValue: "",
				data: JSON.parse(JSON.stringify(data))
			};
		},
		methods: {
			renderContent(h, { node, data, store }) {
				return (
					<span class="custom-tree-node">
						<i class="iconfont iconbaohudi_wenjianjia ai-layout-content-tree-icon" />
						<span>{node.label}</span>
						<span class="tree_edit">
							<el-tooltip
								class="item"
								effect="dark"
								content="新增"
								placement="top"
							>
								<i
									class="iconfont iconxinzeng"
									on-click={() => this.appendTree(data)}
								/>
							</el-tooltip>
							<el-tooltip
								class="item"
								effect="dark"
								content="编辑"
								placement="top"
							>
								<i
									class="iconfont iconxiugai"
									on-click={() => this.editTree(data)}
								/>
							</el-tooltip>
							<el-tooltip
								class="item"
								effect="dark"
								content="删除"
								placement="top"
							>
								<i
									class="iconfont iconshanchu"
									on-click={() => this.deleteTree(node, data)}
								/>
							</el-tooltip>
						</span>
					</span>
				);
			},
			appendTree() {},
			editTree() {},
			deleteTree() {},
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
		width: 30%;
		height: 100%;
		float: left;
		border-top: 1px solid #d3d3d3;
		border-right: 1px solid #d3d3d3;
		padding: 20px;
	}
	.ai-layout-content .ai-layout-content-left .ai-layout-content-list-tree {
		margin-top: 20px;
		border-top: 1px solid #ededed;
		padding: 20px;
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
		margin-right: 13px;
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
		background: #4b9fe9 !important;
	}
	.el-tooltip__popper .popper__arrow::after {
		border-top-color: #4b9fe9 !important;
	}
	/* 右侧内容 */
	.ai-layout-content .ai-layout-content-right {
		width: 70%;
		height: 100%;
		float: left;
		border-top: 1px solid #d3d3d3;
	}
</style>
