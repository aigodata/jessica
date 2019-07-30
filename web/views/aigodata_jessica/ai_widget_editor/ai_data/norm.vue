<template>
	<!-- 指标 -->
	<div class="ai_jessica_widget_data_norm">
		<p class="data-norm-label">指标</p>
		<div class="data-norm-button-add-module">
			<span class="data-norm-icon-add">
				<i class="iconfont iconxinzeng"></i>
			</span>
			<span class="data-norm-add-text">添加指标</span>
		</div>
		<ul class="data-norm-content">
			<li class="data-norm-button-module">
				<span class="data-norm-alias">设备品牌1</span>
				<span class="data-norm-field">device</span>
				<span class="data-norm-icon-delete">
					<i class="iconfont iconshanchu2"></i>
				</span>
			</li>
			<li class="data-norm-button-module">
				<span class="data-norm-alias">设备品牌2</span>
				<span class="data-norm-field">device</span>
				<span class="data-norm-icon-delete">
					<i class="iconfont iconshanchu2"></i>
				</span>
			</li>
			<li class="data-norm-button-module">
				<span class="data-norm-alias">设备品牌3</span>
				<span class="data-norm-field">device</span>
				<span class="data-norm-icon-delete">
					<i class="iconfont iconshanchu2"></i>
				</span>
			</li>
		</ul>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_data_norm",
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
	.ai_jessica_widget_data_norm {
		position: relative;
		height: 100%;
		font-size: 12px;
		font-weight: 400;
		color: #313131;
		overflow: hidden;
	}
	.ai_jessica_widget_data_norm .data-norm-label {
		line-height: 28px;
	}
	.ai_jessica_widget_data_norm .data-norm-button-add-module {
		line-height: 26px;
		font-size: 12px;
		color: #888888;
		border: 1px dashed #d6d6d6;
		text-align: center;
		margin-bottom: 5px;
		border-radius: 3px;
		cursor: pointer;
		cursor: pointer;
	}
	.ai_jessica_widget_data_norm .data-norm-button-add-module .data-norm-icon-add {
		font-size: 12px;
		color: #888888;
		margin-right: 9px;
	}
	.ai_jessica_widget_data_norm .data-norm-button-add-module .data-norm-add-text {
	}
	.ai_jessica_widget_data_norm .data-norm-button-add-module.active {
		color: #4b9fe9;
		border: 1px dashed #4b9fe9;
	}
	.ai_jessica_widget_data_norm
		.data-norm-button-add-module.active
		.data-norm-icon-add {
		color: #4b9fe9;
	}
	.ai_jessica_widget_data_norm .data-norm-content {
		/* height: 43px; */
		padding-bottom: 5px;
		overflow: auto;
	}
	.ai_jessica_widget_data_norm .data-norm-content .data-norm-button-module {
		padding: 0 8px 0 14px;
		line-height: 28px;
		background-color: #d0e9ff;
		margin-bottom: 5px;
		border-radius: 3px;
	}
	.ai_jessica_widget_data_norm
		.data-norm-content
		.data-norm-button-module
		.data-norm-alias {
		margin-right: 3px;
	}
	.ai_jessica_widget_data_norm
		.data-norm-content
		.data-norm-button-module
		.data-norm-field {
		color: #888888;
	}
	.ai_jessica_widget_data_norm
		.data-norm-content
		.data-norm-button-module
		.data-norm-icon-delete {
		float: right;
		color: #f45e5f;
		cursor: pointer;
	}
	.ai_jessica_widget_data_norm
		.data-norm-content
		.data-norm-button-module
		.data-norm-icon-delete
		i {
		font-size: 12px;
	}
</style>
