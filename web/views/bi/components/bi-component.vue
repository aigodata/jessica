<template>
	<div>
		<component :is="currentComponent" :data="data"></component>
	</div>
</template>

<script>
	const CUSTOM_COMPONENTS = {};
	const requireAll = context => context.keys().map(context);
	const install = module => {
		module.default &&
			module.default.name &&
			(CUSTOM_COMPONENTS[module.default.name] = module.default);
	};
	requireAll(require.context("./basic", true, /\.vue$/)).forEach(install);
	requireAll(require.context("./chart", true, /\.vue$/)).forEach(install);
	export default {
		name: "bi-component",
		props: {
			// 组件名称
			name: {
				type: String,
				default: ""
			},
			// 数据源
			data: {
				type: Object,
				default: function() {
					return {};
				}
			}
		},
		watch: {
			name: function() {
				this.load();
			}
		},
		data() {
			return {
				currentComponent: ""
			};
		},
		methods: {
			clear() {},
			load() {
				this.currentComponent = CUSTOM_COMPONENTS[this.name] || "";
			}
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
