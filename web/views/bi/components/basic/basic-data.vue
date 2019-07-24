<template>
	<div>我是 basic data</div>
</template>

<script>
	export default {
		name: "ai-basic-data",
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
