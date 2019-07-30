<template>
	<!-- ai_jessica_data_source -->
	<div class="ai_jessica_data_source">数据源</div>
</template>

<script>
	export default {
		name: "ai_jessica_data_source",
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
	.ai_jessica_data_source {
		position: relative;
		height: 100%;
	}
</style>