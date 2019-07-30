<template>
	<!-- ai_jessica_widget_library -->
	<div class="ai_jessica_widget_library">
		<div class="library-header">
			<span class="library-tips">当前页面 :</span>
			<span class="library-page-name">页面名称</span>
		</div>
		<div class="library-title">
			<el-input v-model="searchParams" placeholder="默认标题名称">
				<i slot="suffix" class="iconfont iconxiugai"></i>
			</el-input>
		</div>
		<div class>展示</div>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_library",
		computed: {},
		watch: {},
		data() {
			return {
				searchParams: ""
			};
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
	.ai_jessica_widget_library {
		position: relative;
		height: 100%;
		padding: 10px 20px;
		font-family: MicrosoftYaHei;
		font-size: 12px;
		font-weight: 400;
		color: #313131;
	}
	.ai_jessica_widget_library .library-header {
		line-height: 26px;
	}
	.ai_jessica_widget_library .library-header .library-tips {
		margin-right: 5px;
	}
	.ai_jessica_widget_library .library-header .library-page-name {
		color: #fd9334;
	}
	.ai_jessica_widget_library .library-title i {
		color: #4b9fe9;
		cursor: pointer;
	}
	.ai_jessica_widget_library .library-title .el-input {
		width: 150px;
	}
	.ai_jessica_widget_library .library-title .el-input input {
		padding-left: 0;
		border: none;
		border-bottom: 1px solid #dbdbdb;
		border-radius: 0;
	}
	.ai_jessica_widget_library
		.library-title
		.el-input
		.el-input__suffix-inner {
		position: absolute;
		top: 50%;
		right: 0;
		transform: translateY(-50%);
	}
</style>