<template>
	<div>
		我就是个列表
		<button @click="addTMP">添加模板</button>
	</div>
</template>

<script>
	export default {
		computed: {},
		watch: {},
		data() {
			return {};
		},
		methods: {
			addTMP() {
				this.$router.push("/single/bi-editor");
			},
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
