<template>
	<!-- ai_jessica_data_indicator -->
	<div class="ai_jessica_data_indicator">指标</div>
</template>

<script>
	export default {
		name: "ai_jessica_data_indicator",
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
	.ai_jessica_data_indicator {
		position: relative;
		height: 100%;
	}
</style>