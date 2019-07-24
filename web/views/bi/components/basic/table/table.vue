<template>
	<div>我是个table</div>
</template>

<script>
	export default {
		name: "ai-table",
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
</style>
