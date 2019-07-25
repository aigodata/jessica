<template>
	<div class="ai-layout-container">
		<!-- 头部 -->
		<layout-header></layout-header>
		<!-- 侧边栏 -->
		<layout-sidebar></layout-sidebar>
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
	import layoutHeader from "./../layout/header";
	import layoutSidebar from "./../layout/sidebar";
	import layoutModule from "./../layout/module";
	import layoutBreadcrumb from "./../layout/breadcrumb";
	import layoutContent from "./../layout/content";

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
	#app {
		height: 100%;
		position: relative;
	}
</style>