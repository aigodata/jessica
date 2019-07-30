<template>
	<!-- 字段 -->
	<div class="ai_jessica_widget_data_field">
		<p class="data-field-label">字段</p>
		<ul class="data-field-content">
			<li class="data-field-button-module">
				<el-checkbox>
					<span class="data-field-alias">设备品牌</span>
					<span class="data-field-field">device</span>
				</el-checkbox>
			</li>
			<li class="data-field-button-module">
				<el-checkbox>
					<span class="data-field-alias">设备品牌1</span>
					<span class="data-field-field">device</span>
				</el-checkbox>
			</li>
			<li class="data-field-button-module">
				<el-checkbox>
					<span class="data-field-alias">设备品牌2</span>
					<span class="data-field-field">device</span>
				</el-checkbox>
			</li>
			<li class="data-field-button-module">
				<el-checkbox>
					<span class="data-field-alias">设备品牌3</span>
					<span class="data-field-field">device</span>
				</el-checkbox>
			</li>
		</ul>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_data_field",
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
	.ai_jessica_widget_data_field {
		position: relative;
		height: 100%;
		font-size: 12px;
		font-weight: 400;
		color: #313131;
		overflow: hidden;
	}
	.ai_jessica_widget_data_field .data-field-label {
		line-height: 28px;
	}
	.ai_jessica_widget_data_field .data-field-button-add-module {
		line-height: 26px;
		font-size: 12px;
		color: #888888;
		border: 1px dashed #d6d6d6;
		text-align: center;
		margin-bottom: 5px;
		border-radius: 3px;
		cursor: pointer;
	}
	.ai_jessica_widget_data_field .data-field-content {
		/* height: 104px; */
		padding-bottom: 5px;
		overflow: auto;
	}
	.ai_jessica_widget_data_field .data-field-content .data-field-button-module {
		padding: 0 8px 0 14px;
		line-height: 28px;
		background-color: #d0e9ff;
		margin-bottom: 5px;
		border-radius: 3px;
	}
	.ai_jessica_widget_data_field
		.data-field-content
		.data-field-button-module
        .el-checkbox 
        .el-checkbox__input.is-checked
		.el-checkbox__inner {
		background-color: #459feb;
		border-color: #459feb;
	}
	.ai_jessica_widget_data_field
		.data-field-content
		.data-field-button-module
		.el-checkbox__label
		.data-field-alias {
		font-size: 12px;
		font-weight: 400;
		color: #313131;
		margin-right: 3px;
	}
	.ai_jessica_widget_data_field
		.data-field-content
		.data-field-button-module
		.el-checkbox__label
		.data-field-field {
		font-size: 12px;
		font-weight: 400;
		color: #888888;
	}
</style>
