<template>
	<div class="ai-layout-content">
		<router-view></router-view>
	</div>
</template>
<script>
	export default {
		name: "layout-content",
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
