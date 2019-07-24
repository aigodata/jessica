<template>
	<div id="app">
		<!-- 头部 -->
		<layout-header></layout-header>
		<!-- 侧边栏 -->
		<layout-sidebar></layout-sidebar>
		<!-- 内容 -->
		<layout-container>
			<!-- 面包屑 -->
			<layout-breadcrumb></layout-breadcrumb>
			<!-- 页面 -->
			<layout-content></layout-content>
		</layout-container>
	</div>
</template>
<script>
	import layoutHeader from "./../layout/header";
	import layoutSidebar from "./../layout/sidebar";
	import layoutContainer from "./../layout/container";
	import layoutBreadcrumb from "./../layout/breadcrumb";
	import layoutContent from "./../layout/content";

	export default {
		name: "app",
		components: {
			layoutHeader,
			layoutSidebar,
			layoutContainer,
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