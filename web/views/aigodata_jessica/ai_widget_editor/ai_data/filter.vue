<template>
	<div class="ai_jessica_widget_data_filter">
		<p class="data-filter-label">过滤条件</p>
		<div class="data-filter-button-add-module">
			<span class="data-filter-icon-add">
				<i class="iconfont iconxinzeng"></i>
			</span>
			<span class="data-filter-add-text">添加过滤条件</span>
		</div>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_data_filter",
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
	.ai_jessica_widget_data_filter {
		position: relative;
		height: 100%;
		font-size: 12px;
		font-weight: 400;
		color: #313131;
		overflow: hidden;
	}
	.ai_jessica_widget_data_filter .data-filter-label {
		line-height: 28px;
	}
	.ai_jessica_widget_data_filter .data-filter-button-add-module {
		line-height: 26px;
		font-size: 12px;
		color: #888888;
		border: 1px dashed #d6d6d6;
		text-align: center;
		margin-bottom: 5px;
		border-radius: 3px;
		cursor: pointer;
	}
	.ai_jessica_widget_data_filter
		.data-filter-button-add-module
		.data-filter-icon-add {
		font-size: 12px;
		color: #888888;
		margin-right: 9px;
	}
	.ai_jessica_widget_data_filter .data-filter-button-add-module.active {
		color: #4b9fe9;
		border: 1px dashed #4b9fe9;
	}
	.ai_jessica_widget_data_filter
		.data-filter-button-add-module.active
		.data-filter-icon-add {
		color: #4b9fe9;
	}
</style>
