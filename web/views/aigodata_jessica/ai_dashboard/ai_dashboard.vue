<template>
	<!-- ai_jessica_dashboard -->
	<div class="ai_jessica_dashboard">仪表盘</div>
</template>

<script>
	export default {
		name: "ai_jessica_dashboard",
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
	.ai_jessica_dashboard {
		position: relative;
		height: 100%;
	}
</style>