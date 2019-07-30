<template>
	<div class="ai-layout-container">
		<!-- 头部 -->
		<div class="ai-layout-header">
			<div
				class="logo ai-layout-header-transition"
				:class="sidebarStatus?'ai-layout-header-show':'ai-layout-header-hide'"
			>
				<img class="logo-icon" src="./../assets/images/favicon.png">
				<span v-show="sidebarStatus" class="logo-text">aigodata jessica</span>
			</div>
			<ul class="navbar-nav">
				<li>
					<i class="iconfont icon_shouqi sidebar-show" @click="setSidebar"></i>
				</li>
				<li @click="handleNavigationChange('/')" class="active">分析</li>
				<li @click="handleNavigationChange('/ai_data_indicator')">指标</li>
				<li @click="handleNavigationChange('/ai_data_source')">数据源</li>
				<li @click="handleNavigationChange('/ai_data_dictionary')">数据字典</li>
				<li @click="handleNavigationChange('')">设置</li>
			</ul>
			<ul class="quick-menu">
				<li>
					<i class="iconfont icon-mes notification-icon"></i>
					<div class="notification-content">
						<span class="total">3</span>
					</div>
				</li>
				<li>
					<i class="iconfont icon-danweizuzhi-yonghuguanli avatar-icon"></i>
					<span class="nick-name">admin</span>
					<div class="icon-item">
						<i class="iconfont iconarrfill_u-copy dropdown-caret"></i>
					</div>
				</li>
			</ul>
		</div>
		<!--  -->
		<div class="app-content">
			<router-view></router-view>
		</div>
	</div>
</template>
<script>
	import { mapMutations, mapGetters } from "vuex";
	export default {
		computed: {
			...mapGetters({
				sidebarStatus: "sidebarStatus"
			})
		},
		watch: {},
		data() {
			return {};
		},
		methods: {
			...mapMutations({
				setSidebarStatus: "setSidebarStatus"
			}),
			//
			setSidebar() {
				let newStatus = !this.sidebarStatus;
				this.setSidebarStatus(newStatus);
			},
			// 导航切换
			handleNavigationChange(path) {
				this.$router.push({
					path: path || "/"
				});
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
	/* 页面容器 */
	.ai-layout-container {
		height: 100%;
	}

	/* 头部 -- start */
	.ai-layout-header {
		width: 100%;
		height: 60px;
		line-height: 60px;
		border: 1px solid #ebebeb;
		overflow: hidden;
	}

	.ai-layout-header .logo {
		display: inline-block;
		width: 220px;
		height: 100%;
		font-size: 18px;
		text-align: center;
		background: rgba(47, 50, 60, 1);
		color: #fff;
		cursor: pointer;
	}

	.ai-layout-header .logo .logo-icon {
		width: 23px;
		height: 25px;
		vertical-align: text-bottom;
	}

	.ai-layout-header .logo .logo-text {
		margin-left: 7px;
	}

	.ai-layout-header .sidebar-show {
		margin: 0 10px;
		font-size: 20px;
		position: relative;
		top: 2px;
		cursor: pointer;
	}
	.ai-layout-header .logo.ai-layout-header-transition {
		transition: all 0.6s;
		float: left;
		overflow: hidden;
	}
	.ai-layout-header .logo.ai-layout-header-show {
		width: 220px;
	}
	.ai-layout-header .logo.ai-layout-header-hide {
		width: 60px;
	}
	.ai-layout-header .navbar-nav {
		display: inline-block;
		height: 100%;
		font-weight: bold;
	}

	.ai-layout-header .navbar-nav li {
		display: inline-block;
		width: 70px;
		height: 100%;
		text-align: center;
		cursor: pointer;
	}

	.ai-layout-header .navbar-nav li:hover {
		background-color: #f0f0f0;
	}

	.ai-layout-header .navbar-nav li.active {
		border-bottom: 4px solid #4b9fe9;
	}

	.ai-layout-header .quick-menu {
		display: inline-block;
		height: 100%;
		float: right;
		cursor: pointer;
	}

	.ai-layout-header .quick-menu li {
		position: relative;
		display: inline-block;
		padding: 0 16px;
	}

	.ai-layout-header .quick-menu li::after {
		content: "";
		position: absolute;
		right: 0;
		top: 50%;
		transform: translateY(-50%);
		width: 1px;
		height: 18px;
		background-color: #ededed;
		opacity: 0.3;
	}

	.ai-layout-header .quick-menu li:last-child::after {
		display: none;
	}

	.ai-layout-header .quick-menu li .notification-icon {
		color: #333;
	}

	.ai-layout-header .quick-menu li .notification-content {
		position: absolute;
		right: 11px;
		top: 15px;
		width: 14px;
		height: 14px;
		border-radius: 50%;
		background: #f45e5f;
		box-shadow: 0px 1px 7px 0px rgba(223, 104, 105, 0.5);
	}

	.ai-layout-header .quick-menu li .notification-content .total {
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		font-size: 8px;
		color: #fff;
	}

	.ai-layout-header .quick-menu li .avatar-icon {
		margin-right: 10px;
	}

	.ai-layout-header .quick-menu li .nick-name {
		margin-right: 6px;
		font-size: 12px;
	}

	.ai-layout-header .quick-menu li .icon-item {
		display: inline-block;
		transform: rotate(90deg);
		vertical-align: middle;
	}

	.ai-layout-header .quick-menu li .icon-item .dropdown-caret {
		font-size: 8px;
	}

	/* 头部 -- end */
	.app-content {
		height: calc(100% - 60px);
		border: 1px solid #D3D3D3;
		overflow: auto;
	}
</style>