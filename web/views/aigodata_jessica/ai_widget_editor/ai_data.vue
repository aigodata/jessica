<template>
	<!-- ai_jessica_widget_data -->
	<div class="ai_jessica_widget_data">
		<!-- 维度 -->
		<div class="data-dimension-module">
			<ai-jessica-widget-data-dimension></ai-jessica-widget-data-dimension>
		</div>
		<!-- 指标 -->
		<div class="data-norm-module">
			<ai-jessica-widget-data-norm></ai-jessica-widget-data-norm>
		</div>
		<!-- 字段 -->
		<div class="data-field-module">
			<ai-jessica-widget-data-field></ai-jessica-widget-data-field>
		</div>
		<!-- 过滤条件 -->
		<div class="data-filter-module">
			<ai-jessica-widget-data-filter></ai-jessica-widget-data-filter>
		</div>
		<!-- 预警 -->
		<div class="data-warning-module">
			<ai-jessica-widget-data-warning></ai-jessica-widget-data-warning>
		</div>
	</div>
</template>

<script>
	import ai_data_dimension from "./ai_data/dimension";
	import ai_data_norm from "./ai_data/norm";
	import ai_data_field from "./ai_data/field";
	import ai_data_filter from "./ai_data/filter";
	import ai_data_warning from "./ai_data/warning";
	
	export default {
		name: "ai_jessica_widget_data",
		components: {
			"ai-jessica-widget-data-dimension": ai_data_dimension,
			"ai-jessica-widget-data-norm": ai_data_norm,
			"ai-jessica-widget-data-field": ai_data_field,
			"ai-jessica-widget-data-filter":ai_data_filter,
			"ai-jessica-widget-data-warning":ai_data_warning
		},
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
	.ai_jessica_widget_data {
		position: relative;
		height: 100%;
	}
	.ai_jessica_widget_data > div {
		position: relative;
		padding: 10px 15px;
		border-bottom: 1px solid #d3d3d3;
	}
	.ai_jessica_widget_data .data-dimension-module {
		/* height: 178px; */
	}
	.ai_jessica_widget_data .data-norm-module {
		/* height: 115px; */
	}
	.ai_jessica_widget_data .data-field-module {
		/* height: 147px; */
	}
	.ai_jessica_widget_data .data-filter-module {
		/* height: 82px; */
	}
	.ai_jessica_widget_data .data-warning-module {
		/* height: 88px; */
	}
</style>