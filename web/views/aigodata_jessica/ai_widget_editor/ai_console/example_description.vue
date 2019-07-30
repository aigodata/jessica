a<template>
	<!-- 示例说明 -->
	<div class="ai_jessica_widget_console_example_description">
		<div>示例说明</div>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_console_example_description",
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
	.ai_jessica_widget_console_example_description {
		position: relative;
		height: 100%;
	}
</style>