a<template>
	<!-- ai_jessica_widget_console -->
	<div class="ai_jessica_widget_console">
		<div class="console-select-content">
			<div class="console-select-content-header" v-if="Object.keys(selectActiveItem).length" @click="displayChange">
				<span class="console-select-content-label">{{ selectActiveItem.label }}</span>
				<span class="console-select-content-icon" :class="{ up: !isDisplay }">
					<i class="iconfont iconzhankai"></i>
				</span>
			</div>
			<component :is="currentComponent" :isDisplay="isDisplay"></component>
		</div>
		<ul class="console-select-header">
			<li
				class="console-select-module"
				v-for="item in selectList"
				:key="item.value"
				:class="{ active: item.value == selectActiveItem.value }"
				@click="selectChange(item)"
			>
				<span class="console-select-icon">
					<i class="iconfont" :class="[item.icon]"></i>
				</span>
				<span class="console-select-label">{{ item.label }}</span>
			</li>
		</ul>
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
	requireAll(require.context("./ai_console", true, /\.vue$/)).forEach(install);

	export default {
		name: "ai_jessica_widget_console",
		computed: {},
		watch: {},
		data() {
			return {
				selectList: [],
				selectActiveItem: {},
				currentComponent: "",
				isDisplay: true
			};
		},
		methods: {
			// 初始化选项卡
			initSelectLabel() {
				this.selectList = [
					{
						label: "事件下钻",
						icon: "icondrill-down",
						value: "ai_jessica_widget_console_event_drill"
					},
					{
						label: "示例说明",
						icon: "iconshuoming1",
						value: "ai_jessica_widget_console_example_description"
					}
				];
				this.selectActiveItem = this.selectList[0];
			},
			// 初始化组件模块
			initModule() {
				!this.selectActiveItem && this.initSelectLabel();
				this.currentComponent =
					CUSTOM_COMPONENTS[this.selectActiveItem.value];
			},
			// 选项切换
			selectChange(item) {
				this.selectActiveItem = item;
				this.currentComponent = CUSTOM_COMPONENTS[item.value];
			},
			// 是否显示选项卡信息
			displayChange(){
				this.isDisplay = !this.isDisplay;
			},
			clear() {},
			load() {
				this.initSelectLabel();
				this.initModule();
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
	.ai_jessica_widget_console {
		position: relative;
		height: 100%;
		font-size: 12px;
		color: #313131;
		background-color: #f4f4f4;
		border-top: 1px solid #d3d3d3;
		padding-bottom: 28px;
	}
	.ai_jessica_widget_console .console-select-header {
		position: absolute;
		left: 0;
		bottom: 0;
		width: 100%;
		height: 28px;
		line-height: 27px;
		border-top: 1px solid #d3d3d3;
	}
	.ai_jessica_widget_console .console-select-header .console-select-module {
		display: inline-block;
		padding: 0 18px;
		cursor: pointer;
	}
	.ai_jessica_widget_console
		.console-select-header
		.console-select-module
		.console-select-icon {
		margin-right: 8px;
	}
	.ai_jessica_widget_console
		.console-select-header
		.console-select-module
		.console-select-icon
		i {
		font-size: 12px;
	}
	.ai_jessica_widget_console
		.console-select-header
		.console-select-module
		.console-select-label {
		color: #313131;
	}

	.ai_jessica_widget_console
		.console-select-header
		.console-select-module.active {
		background-color: #797979;
	}
	.ai_jessica_widget_console
		.console-select-header
		.console-select-module.active
		.console-select-icon
		i {
		color: #f0f0f0;
	}
	.ai_jessica_widget_console
		.console-select-header
		.console-select-module.active
		.console-select-label {
		color: #f0f0f0;
	}
	.console-select-content {
	}
	.console-select-content .console-select-content-header {
		height: 28px;
		line-height: 27px;
		padding: 0 15px;
		border-bottom: 1px solid #d3d3d3;
		cursor: pointer;
	}
	.console-select-content
		.console-select-content-header
		.console-select-content-label {
	}
	.console-select-content
		.console-select-content-header
		.console-select-content-icon {
		float: right;
		font-size: 12px;
		color: #666666;
		transform: rotate(0deg);
		transition: transform 0.3s linear;
	}
	.console-select-content
		.console-select-content-header
		.console-select-content-icon.up {
		transform: rotate(180deg);
	}
</style>