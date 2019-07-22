<template>
	<div class="ai-layout-header">
		<div class="logo">
			<img class="logo-icon" src="./../assets/images/favicon.png">
			<span class="logo-text">高校大数据平台</span>
		</div>
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
	export default {
		name: "layout-header"
	};
</script>

<style>
</style>
