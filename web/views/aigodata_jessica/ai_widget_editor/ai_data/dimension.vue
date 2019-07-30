<template>
	<!-- 维度 -->
	<div class="ai_jessica_widget_data_dimension">
		<p class="data-dimension-label">维度</p>
		<div class="data-dimension-button-add-module active">
			<span class="data-dimension-icon-add">
				<i class="iconfont iconxinzeng"></i>
			</span>
			<span class="data-dimension-add-text">添加维度</span>
		</div>
		<ul class="data-dimension-content">
			<li class="data-dimension-button-module">
				<span class="data-dimension-alias">设备品牌</span>
				<span class="data-dimension-field">device</span>
				<span class="data-dimension-icon-delete">
					<i class="iconfont iconshanchu2"></i>
				</span>
			</li>
			<li class="data-dimension-button-module">
				<span class="data-dimension-alias">设备品牌</span>
				<span class="data-dimension-field">device</span>
				<span class="data-dimension-icon-delete">
					<i class="iconfont iconshanchu2"></i>
				</span>
			</li>
			<li class="data-dimension-button-module">
				<span class="data-dimension-alias">设备品牌</span>
				<span class="data-dimension-field">device</span>
				<span class="data-dimension-icon-delete">
					<i class="iconfont iconshanchu2"></i>
				</span>
			</li>
			<li class="data-dimension-button-module">
				<span class="data-dimension-alias">设备品牌</span>
				<span class="data-dimension-field">device</span>
				<span class="data-dimension-icon-delete">
					<i class="iconfont iconshanchu2"></i>
				</span>
			</li>
		</ul>
		<div>
			<div>
				<input type="text">
			</div>
			<div>
				<span>数据模型表名称</span>
				<ul>
					<li>
						<span>设备品牌</span>
						<span>device</span>
					</li>
                    <li>
						<span>设备品牌 1</span>
						<span>device</span>
					</li>
                    <li>
						<span>设备品牌 2</span>
						<span>device</span>
					</li>
				</ul>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_data_dimension",
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
	.ai_jessica_widget_data_dimension {
		position: relative;
		height: 100%;
		font-size: 12px;
		font-weight: 400;
		color: #313131;
		overflow: hidden;
	}
	.ai_jessica_widget_data_dimension .data-dimension-label {
		line-height: 28px;
	}
	.ai_jessica_widget_data_dimension .data-dimension-button-add-module {
		line-height: 26px;
		font-size: 12px;
		color: #888888;
		border: 1px dashed #d6d6d6;
		text-align: center;
		margin-bottom: 5px;
		border-radius: 3px;
		cursor: pointer;
	}
	.ai_jessica_widget_data_dimension
		.data-dimension-button-add-module
		.data-dimension-icon-add {
		font-size: 12px;
		color: #888888;
		margin-right: 9px;
	}
	.ai_jessica_widget_data_dimension
		.data-dimension-button-add-module
		.data-dimension-add-text {
	}
	.ai_jessica_widget_data_dimension .data-dimension-button-add-module.active {
		color: #4b9fe9;
		border: 1px dashed #4b9fe9;
	}
	.ai_jessica_widget_data_dimension
		.data-dimension-button-add-module.active
		.data-dimension-icon-add {
		color: #4b9fe9;
	}
	.ai_jessica_widget_data_dimension .data-dimension-content {
		/* height: 104px; */
		padding-bottom: 5px;
		overflow: auto;
	}
	.ai_jessica_widget_data_dimension
		.data-dimension-content
		.data-dimension-button-module {
		padding: 0 8px 0 14px;
		line-height: 28px;
		background-color: #d0e9ff;
		margin-bottom: 5px;
		border-radius: 3px;
	}
	.ai_jessica_widget_data_dimension
		.data-dimension-content
		.data-dimension-button-module
		.data-dimension-alias {
		margin-right: 3px;
	}
	.ai_jessica_widget_data_dimension
		.data-dimension-content
		.data-dimension-button-module
		.data-dimension-field {
		color: #888888;
	}
	.ai_jessica_widget_data_dimension
		.data-dimension-content
		.data-dimension-button-module
		.data-dimension-icon-delete {
		float: right;
		color: #f45e5f;
		cursor: pointer;
	}
	.ai_jessica_widget_data_dimension
		.data-dimension-content
		.data-dimension-button-module
		.data-dimension-icon-delete
		i {
		font-size: 12px;
	}
</style>
