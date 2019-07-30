<template>
	<!-- ai_jessica_data_dictionary -->
	<div class="ai_jessica_data_dictionary">字典</div>
</template>

<script>
	export default {
		name: "ai_jessica_data_dictionary",
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
	.ai_jessica_data_dictionary {
		position: relative;
		height: 100%;
	}
</style>