<template>
	<div class="ai-layout-container-transition" :class="sidebarStatus ? 'ai-layout-container' : 'ai-layout-container-hide'">
		<slot></slot>
	</div>
</template>

<script>
	import { mapMutations, mapGetters } from 'vuex';
	export default {
		name: "layout-container",
		computed: {
			...mapGetters({
				sidebarStatus: 'sidebarStatus'
			})
		},
		data () {
			return {

			}
		},
		methods: {
		}
	};
</script>

<style>
	.ai-layout-container-transition {
		transition: all 0.6s;
		-moz-transition: all 0.6s; /* Firefox 4 */
		-webkit-transition: all 0.6s; /* Safari and Chrome */
		-o-transition: all 0.6s; /* Opera */
	}
</style>
