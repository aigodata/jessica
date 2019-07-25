<template>
	<div class="ai-layout-container">
		<!-- 头部 -->
		<layout-header></layout-header>
		<!-- 侧边栏 -->
		<layout-sidebar>
			<div class="ai-layout-sidebar-title">设置</div>
			<el-menu @open="handleOpen" @close="handleClose" :collapse="isCollapse">
				<el-submenu
					:index="`${index}`"
					:default-active="activeIndex"
					v-for="(item,index) in permissionMenu"
				>
					<template slot="title">
						<i class="iconfont" :class="item.icon"></i>
						<span slot="title">{{item.name}}</span>
					</template>
					<el-menu-item
						v-if="isChildren(item)"
						@click="activeItem(index,secondItem)"
						v-for="(secondItem,ii) in item.children"
						:index="`${index}-${ii}`"
					>{{secondItem.name}}</el-menu-item>
				</el-submenu>
			</el-menu>
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
			return {
				isCollapse: false,
				activeIndex: "1",
				permissionMenu: [
					{
						icon: "iconzuzhi",
						name: "组织信息管理",
						routerName: "a",
						children: [
							{
								icon: "icon-caiji",
								name: "组织管理",
								routerName: "a1"
							},
							{
								icon: "icon-caiji",
								name: "班级管理",
								routerName: "a2"
							}
						]
					},
					{
						icon: "iconxinxiguanli",
						name: "用户信息管理",
						routerName: "b",
						children: [
							{
								icon: "icon-caiji",
								name: "用户管理",
								routerName: "b1"
							},
							{
								icon: "icon-caiji",
								name: "用户权限",
								routerName: "b2"
							}
						]
					}
				]
			};
		},
		methods: {
			handleOpen(key, keyPath) {
				this.activeItem(key);
			},
			activeItem(key, node) {
				this.activeIndex = key;
			},
			handleClose() {},
			// 是否有子菜单
			isChildren(item) {
				return item.children && item.children.length > 0;
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
	#app {
		height: 100%;
		position: relative;
	}
</style>