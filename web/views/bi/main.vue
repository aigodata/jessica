<template>
	<div class="ai-layout-container">
		<!-- 头部 -->
		<layout-header></layout-header>
		<!-- 侧边栏 -->
		<layout-sidebar>
			<div class="ai-layout-sidebar-title">分析</div>
			<div class="directory-container">
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
								<i class="iconfont iconyidongshu directory-icon"></i>
							</span>
							<span class="icon-label">
								<i class="iconfont iconwenjian directory-file-icon"></i>
							</span>
							<span class="file-name">index页面</span>
							<span class="icon-label directory-setting">...</span>
						</li>
					</ul>
				</div>
				<div>
					<span>
						<i class="iconfont iconxinzeng"></i>
					</span>
					<span>添加菜单</span>
				</div>
			</div>
		</layout-sidebar>
		<!-- 内容模块 -->
		<layout-module>
			<!-- 面包屑 -->
			<layout-breadcrumb></layout-breadcrumb>
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

	export default {
		components: {
			layoutHeader,
			layoutSidebar,
			layoutModule,
			layoutBreadcrumb,
			layoutContent
		},
		computed: {},
		watch: {},
		data() {
			return {};
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
	.ai-layout-sidebar .directory-container {
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
		padding: 10px 0;
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
	}
	.ai-layout-sidebar .directory-container .directory-module .directory-setting {
		/* line-height: 35px; */
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
		padding: 10px 0 10px 40px;
	}
	.ai-layout-sidebar
		.directory-container
		.directory-module
		.directory-file-modle
		.file-content
		.directory-file-name {
	}
</style>