<template>
	<div
		class="ai-layout-sidebar ai-layout-sidebar-transition"
		:class="sidebarStatus?'ai-layout-sidebar-show':'ai-layout-sidebar-hide'"
	>
		<slot></slot>
	</div>
</template>

<script>
	import { mapMutations, mapGetters } from "vuex";
	export default {
		name: "layout-sidebar",
		computed: {
			...mapGetters({
				sidebarStatus: "sidebarStatus"
			})
		},
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
