<template>
	<div class="ai-layout-sidebar">
		<slot></slot>
	</div>
</template>

<script>
	export default {
		name: "layout-sidebar"
	};
</script>

<style>
	.ai-layout-sidebar {
		position: relative;
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
