<template>
	<!-- ai_jessica_toolbar -->
	<div class="ai_jessica_toolbar">
		<ul class="toolbar-item toolbar-item-left">
			<li class="item-back-module">
				<i class="iconfont iconfanhui toolbar-icon-back"></i>
				<span>返回</span>
			</li>
			<li>
				<i class="iconfont iconbaocun toolbar-icon-save"></i>
				<span>保存</span>
			</li>
			<li>
				<i class="iconfont iconyulan toolbar-icon-preview"></i>
				<span>预览</span>
			</li>
			<li>
				<i class="iconfont icondaochu toolbar-icon-export"></i>
				<span>导出</span>
			</li>
			<li class="abc">
				<div class="data-analysis-model">
					<i class="iconfont iconfenxi toolbar-icon-analysis"></i>
					<span>开始分析</span>
				</div>
			</li>
		</ul>
		<div class="toolbar-item toolbar-item-right">
			<div class="setting-data-model">
				<i class="iconfont iconshezhi toolbar-icon-data-module"></i>
				<span>设置数据模型</span>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_toolbar",
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
	.ai_jessica_toolbar {
		position: relative;
		height: 40px;
		line-height: 40px;
		border-bottom: 1px solid #d3d3d3;
	}
	.ai_jessica_toolbar .toolbar-item {
		position: relative;
		display: inline-block;
		height: 100%;
		cursor: pointer;
		overflow: hidden;
	}
	.ai_jessica_toolbar .toolbar-item li {
		position: relative;
		float: left;
		padding: 0 10px;
		cursor: pointer;
		color: #313131;
		font-size: 12px;
	}
	.ai_jessica_toolbar .toolbar-item li i {
		font-size: 12px;
	}
	.ai_jessica_toolbar .toolbar-item-left {
		float: left;
	}
	.ai_jessica_toolbar .toolbar-item-right {
		float: right;
	}
	.ai_jessica_toolbar .toolbar-item i {
		margin-right: 7px;
	}
	.ai_jessica_toolbar .toolbar-item .item-back-module::after {
		content: "";
		position: absolute;
		right: 0;
		top: 50%;
		transform: translateY(-50%);
		width: 1px;
		height: 18px;
		background-color: #d9d9d9;
	}
	.ai_jessica_toolbar .toolbar-item .abc {
		width: 98px;
		height: 100%;
		padding: 0;
	}
	.ai_jessica_toolbar .toolbar-item .data-analysis-model {
		position: absolute;
		width: 100%;
		height: 32px;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
		line-height: 32px;
		padding: 0 10px;
		background: rgba(255, 255, 255, 1);
		border: 1px solid rgba(240, 240, 240, 1);
		box-shadow: 0px 2px 7px 0px rgba(0, 0, 0, 0.1);
		border-radius: 3px;
	}
	.ai_jessica_toolbar .toolbar-item .setting-data-model {
		width: 124px;
		height: 32px;
		line-height: 32px;
		padding: 0 10px;
		background: linear-gradient(
			0deg,
			rgba(253, 136, 36, 1),
			rgba(253, 154, 68, 1)
		);
		box-shadow: 0px 3px 8px 0px rgba(253, 147, 52, 0.3);
		border-radius: 3px;
		color: #fff;
		font-size: 12px;
	}
	.ai_jessica_toolbar .toolbar-item .toolbar-icon-back {
	}
	.ai_jessica_toolbar .toolbar-item .toolbar-icon-save {
		color: #4b9fe9;
	}
	.ai_jessica_toolbar .toolbar-item .toolbar-icon-preview {
		color: #4b9fe9;
	}
	.ai_jessica_toolbar .toolbar-item .toolbar-icon-export {
		color: #fd9334;
	}
	.ai_jessica_toolbar .toolbar-item .toolbar-icon-analysis {
		color: #4b9fe9;
	}
	.ai_jessica_toolbar .toolbar-item .toolbar-icon-data-module {
		color: #fff;
	}
</style>