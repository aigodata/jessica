<template>
	<div class="ai-layout-header">
		<div class="logo ai-layout-header-transition" :class="sidebarStatus?'ai-layout-header-show':'ai-layout-header-hide'">
			<img class="logo-icon" src="./../assets/images/favicon.png">
			<span v-show="sidebarStatus" class="logo-text">高校大数据平台</span>
		</div>
		<i class="iconfont icon_shouqi sidebar-show" @click="setSidebar"></i>
		<ul class="navbar-nav">
			<li>分析</li>
			<li>指标</li>
			<li class="active">数据源</li>
			<li>设置</li>
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
</template>

<script>
	import { mapMutations, mapGetters } from 'vuex';
	export default {
		name: "layout-header",
		computed: {
			...mapGetters({
				sidebarStatus: 'sidebarStatus'
			})
		},
		data () {
			return {
			}
		},
		methods: {
			...mapMutations({
				setSidebarStatus: 'setSidebarStatus'
			}),
			setSidebar () {
				let newStatus = !this.sidebarStatus;
				this.setSidebarStatus(newStatus);
			}
		},
		mounted () {
		}
	};
</script>

<style>
	.ai-layout-header .sidebar-show {
		margin: 0 10px;
		font-size: 20px;
		position: relative;
		top: 2px;
		cursor: pointer;
	}
	.ai-layout-header .logo.ai-layout-header-transition {
		transition: all .6s;
		-moz-transition: all .6s; /* Firefox 4 */
		-webkit-transition: all .6s; /* Safari and Chrome */
        -o-transition: all .6s; /* Opera */
        float: left;
        overflow: hidden;
	}
	.ai-layout-header .logo.ai-layout-header-show {
		width: 220px;
	}
	.ai-layout-header .logo.ai-layout-header-hide {
		width: 60px;
	}
</style>
