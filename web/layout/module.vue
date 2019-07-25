<template>
	<div
		class="ai-layout-module ai-layout-module-transition"
		:class="{ 'ai-layout-module-hide' : !sidebarStatus }"
	>
		<slot></slot>
	</div>
</template>

<script>
	import { mapMutations, mapGetters } from "vuex";
	export default {
		name: "layout-module",
		computed: {
			...mapGetters({
				sidebarStatus: "sidebarStatus"
			})
		},
		data() {
			return {};
		},
		methods: {}
	};
</script>

<style>
</style>
