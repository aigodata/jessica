<template>
	<div class="ai-layout-sidebar ai-layout-sidebar-transition" :class="sidebarStatus?'ai-layout-sidebar-show':'ai-layout-sidebar-hide'">
		<div v-show="sidebarStatus">
			<div class="ai-layout-sidebar-title">设置</div>
			<el-menu @open="handleOpen" @close="handleClose" :collapse="isCollapse">
				<el-submenu :index="`${index}`" :default-active="activeIndex" v-for="(item,index) in permissionMenu">
					<template slot="title">
						<i class="iconfont" :class="item.icon"></i>
						<span slot="title">{{item.name}}</span>
					</template>
					<el-menu-item v-if="isChildren(item)" @click="activeItem(index,secondItem)" v-for="(secondItem,ii) in item.children" :index="`${index}-${ii}`">
						{{secondItem.name}}
					</el-menu-item>
				</el-submenu>
			</el-menu>
		</div>
	</div>
</template>

<script>
	import { mapMutations, mapGetters } from 'vuex';
	export default {
		name: "layout-sidebar",
		computed: {
			...mapGetters({
				sidebarStatus: 'sidebarStatus'
			})
		},
		data () {
			return {
				isCollapse: false,
				activeIndex: '1',
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
					},
					{
						icon: "iconxinxiguanli",
						name: "角色管理",
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
					},
					{
						icon: "iconxinxiguanli",
						name: "权限管理",
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
				],

			}
		},
		methods: {
			handleOpen (key, keyPath) {
				this.activeItem(key)
			},
			activeItem (key, node) {
				this.activeIndex = key;
			},
			handleClose () { },
			// 是否有子菜单
			isChildren (item) {
				return item.children && item.children.length > 0;
			},

		},
		mounted () {
		}
	};
</script>

<style>
	.ai-layout-sidebar {
		position: relative;
		overflow: hidden;
	}
	.ai-layout-sidebar.ai-layout-sidebar-show {
		width: 220px;
	}
	.ai-layout-sidebar.ai-layout-sidebar-hide {
		width: 60px;
	}
	.ai-layout-sidebar.ai-layout-sidebar-transition {
		transition: all 0.6s;
		-moz-transition: all 0.6s; /* Firefox 4 */
		-webkit-transition: all 0.6s; /* Safari and Chrome */
		-o-transition: all 0.6s; /* Opera */
	}
	.ai-layout-sidebar .ai-layout-sidebar-title {
		height: 50px;
		padding: 18px;
		font-size: 14px;
		line-height: 14px;
		color: #484747;
		border-bottom: 1px solid #d3d3d3;
	}
	.ai-layout-sidebar .el-menu .el-submenu__title {
		height: 45px;
		line-height: 45px;
	}
	.ai-layout-sidebar .el-menu .el-submenu__title i {
		margin-right: 10px;
	}
	.ai-layout-sidebar .el-menu .is-active .el-submenu__title {
		background: linear-gradient(
			-44deg,
			rgba(75, 159, 233, 1),
			rgba(90, 174, 248, 1)
		);
		color: #fff;
	}
	.ai-layout-sidebar .el-menu .is-active .el-submenu__title i {
		color: #fff;
	}
	.ai-layout-sidebar .el-menu .el-submenu .el-menu-item:hover {
		background: #f0f0f0;
	}
</style>
