<template>
	<div class="ai-layout-container">
		<!-- 头部 -->
		<layout-header></layout-header>
		<!-- 侧边栏 -->
		<layout-sidebar>
			<div class="ai-layout-sidebar-title">分析</div>
			<div class="directory-container">
				<!-- <div class="directory-module">
					<div class="directory-node-content">
						<span class="icon-label">
							<i class="iconfont iconyidongshu directory-icon"></i>
						</span>
						<span class="icon-label">
							<i class="iconfont iconsanjiaoxing directory-arrow-icon"></i>
						</span>
						<span class="icon-label">
							<i class="iconfont iconwenjianjia directory-file-icon"></i>
						</span>
						<span class="directory-name">测试模块页面</span>
						<span class="icon-label directory-setting">...</span>
					</div>
					<ul class="directory-file-modle">
						<li class="file-content">
							<span class="icon-label">
								<i class="iconfont iconyidongshu directory-icon active"></i>
							</span>
							<span class="icon-label">
								<i class="iconfont iconwenjian directory-file-icon"></i>
							</span>
							<span class="file-name">index页面</span>
							<span class="icon-label directory-setting">···</span>

							<div class="directory-module">
								<div class="directory-node-content">
									<span class="icon-label">
										<i class="iconfont iconyidongshu directory-icon"></i>
									</span>
									<span class="icon-label">
										<i class="iconfont iconsanjiaoxing directory-arrow-icon"></i>
									</span>
									<span class="icon-label">
										<i class="iconfont iconwenjianjia directory-file-icon"></i>
									</span>
									<span class="directory-name">测试模块页面</span>
									<span class="icon-label directory-setting">...</span>
								</div>
								<ul class="directory-file-modle">
									<li class="file-content">
										<span class="icon-label">
											<i class="iconfont iconyidongshu directory-icon active"></i>
										</span>
										<span class="icon-label">
											<i class="iconfont iconwenjian directory-file-icon"></i>
										</span>
										<span class="file-name">index页面</span>
										<span class="icon-label directory-setting">···</span>
									</li>
								</ul>
							</div>
						</li>
					</ul>
				</div>-->
				<!-- <div>
					<span>
						<i class="iconfont iconxinzeng"></i>
					</span>
					<span>添加菜单</span>
				</div>-->
				<v-jstree :data="data" multiple allow-batch whole-row @item-click="itemClick">
					<template scope="scope">
						<div>{{ scope.model.text }}</div>
					</template>
				</v-jstree>
			</div>
		</layout-sidebar>
		<!-- 内容模块 -->
		<layout-module>
			<!-- 页面 -->
			<layout-content></layout-content>
		</layout-module>
	</div>
</template>
<script>
	import layoutHeader from "./../../layout/header";
	import layoutSidebar from "./../../layout/sidebar";
	import layoutModule from "./../../layout/module";
	import layoutBreadcrumb from "./../../layout/breadcrumb";
	import layoutContent from "./../../layout/content";
	import VJstree from "vue-jstree";

	export default {
		components: {
			layoutHeader,
			layoutSidebar,
			layoutModule,
			layoutBreadcrumb,
			layoutContent,
			VJstree
		},
		computed: {},
		watch: {},
		data() {
			return {
				data: [
					{
						text: "Same but with checkboxes",
						children: [
							{
								text: "initially selected",
								selected: true
							},
							{
								text: "custom icon",
								icon: "fa fa-warning icon-state-danger"
							},
							{
								text: "initially open",
								icon: "fa fa-folder icon-state-default",
								opened: true,
								children: [
									{
										text: "Another node"
									}
								]
							},
							{
								text: "custom icon",
								icon: "fa fa-warning icon-state-warning"
							},
							{
								text: "disabled node",
								icon: "fa fa-check icon-state-success",
								disabled: true
							}
						]
					},
					{
						text: "Same but with checkboxes",
						opened: true,
						children: [
							{
								text: "initially selected",
								selected: true
							},
							{
								text: "custom icon",
								icon: "fa fa-warning icon-state-danger"
							},
							{
								text: "initially open",
								icon: "fa fa-folder icon-state-default",
								opened: true,
								children: [
									{
										text: "Another node"
									}
								]
							},
							{
								text: "custom icon",
								icon: "fa fa-warning icon-state-warning"
							},
							{
								text: "disabled node",
								icon: "fa fa-check icon-state-success",
								disabled: true
							}
						]
					},
					{
						text: "And wholerow selection"
					}
				]
			};
		},
		methods: {
			itemClick(node) {
				console.log(node.model.text + " clicked !");
			},
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
	.ai-layout-sidebar .directory-container {
		width: 100%;
		height: 100%;
		overflow: auto;
	}
	.ai-layout-sidebar .directory-container .directory-module {
		padding: 0 10px;
	}
	.ai-layout-sidebar
		.directory-container
		.directory-module
		.directory-node-content {
		position: relative;
		height: 45px;
		line-height: 45px;
	}
	.ai-layout-sidebar .directory-container .icon-label {
		margin-right: 2px;
		cursor: pointer;
	}
	.ai-layout-sidebar .directory-container .directory-icon {
	}
	.ai-layout-sidebar .directory-container .directory-icon.active {
		color: #36a2f0;
	}
	.ai-layout-sidebar
		.directory-container
		.directory-module
		.directory-arrow-icon {
	}
	.ai-layout-sidebar .directory-container .directory-module .directory-file-icon {
	}
	.ai-layout-sidebar .directory-container .directory-module .directory-name {
		display: inline-block;
		width: 60%;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		vertical-align: middle;
		font-size: 12px;
	}
	.ai-layout-sidebar .directory-container .directory-module .directory-setting {
		position: absolute;
		right: 0;
		top: 50%;
		height: 100%;
		transform: translateY(-50%);
	}
	.ai-layout-sidebar
		.directory-container
		.directory-module
		.directory-file-modle
		.file-content {
		position: relative;
		height: 45px;
		line-height: 45px;
		padding-left: 40px;
	}
	.ai-layout-sidebar
		.directory-container
		.directory-module
		.directory-file-modle
		.file-content
		.file-name {
		display: inline-block;
		width: 80px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		vertical-align: middle;
		font-size: 12px;
	}
	.ai-layout-sidebar
		.directory-container
		.directory-module
		.directory-file-modle
		.file-content {
	}
</style>