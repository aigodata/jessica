<template>
	<!-- ai_jessica_widget_editor -->
	<div class="ai_jessica_widget_editor">
		<div class="ai-jessica-widget-editor-toolbar">
			<!-- toolbar (头) -->
			<ai-jessica-toolbar></ai-jessica-toolbar>
		</div>
		<div class="ai-jessica-widget-editor-content">
			<div class="left-part">
				<div class="library-sidebar-module">
					<!-- 手风琴侧边栏 (左) -->
					<ai-jessica-widget-display></ai-jessica-widget-display>
				</div>
			</div>
			<div class="right-part">
				<div class="top-content">
					<div class="left-content">
						<div class="library-module">
							<!-- 组件展示 (中-上) -->
							<ai-jessica-widget-library></ai-jessica-widget-library>
						</div>
						<div class="console-module">
							<!-- console 控制台 (中-下) -->
							<ai-jessica-widget-console></ai-jessica-widget-console>
						</div>
					</div>
					<div class="right-content">
						<div class="data-style-module">
							<!-- 数据|样式 选项卡 (右) -->
							<ul class="data-style-tab-header">
								<li
									v-for="item in dataStyleSelects"
									:key="item.value"
									:class="{ 'tab-active': item.value == currDataStyleSelect.value }"
									@click="dataStyleSelectChange(item)"
								>{{ item.label }}</li>
							</ul>
							<div class="data-style-tab-content">
								<component :is="dataStyleSelectCMM[currDataStyleSelect.value]"></component>
							</div>
						</div>
					</div>
				</div>
				<div class="bottom-content">
					<div class="tips-module">
						<!-- 页脚 (下) -->
						<ai-jessica-widget-editor-state></ai-jessica-widget-editor-state>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import ai_toolbar from "./ai_toolbar";
	import ai_display from "./ai_display";
	import ai_library from "./ai_library";
	import ai_console from "./ai_console";
	import ai_data from "./ai_data";
	import ai_style from "./ai_style";
	import ai_state from "./ai_state";

	export default {
		name: "ai_jessica_widget_editor",
		components: {
			"ai-jessica-toolbar": ai_toolbar,
			"ai-jessica-widget-display": ai_display,
			"ai-jessica-widget-library": ai_library,
			"ai-jessica-widget-console": ai_console,
			"ai-jessica-widget-editor-state": ai_state
		},
		computed: {},
		watch: {},
		data() {
			return {
				// 数据|样式 选项卡列表
				dataStyleSelects: [],
				// 当前选中 数据|样式 选项卡
				currDataStyleSelect: {},
				// 数据|样式 选项卡组件
				dataStyleSelectCMM: {}
			};
		},
		methods: {
			// 初始化 数据|样式 选项卡
			initDataStyleSelect() {
				this.dataStyleSelects = [
					{
						label: "数据",
						value: "data"
					},
					{
						label: "样式",
						value: "style"
					}
				];
				this.dataStyleSelectCMM = {
					data: ai_data,
					style: ai_style
				};
				this.currDataStyleSelect = this.dataStyleSelects[0];
			},
			// 数据|样式 选项卡切换
			dataStyleSelectChange(item) {
				this.currDataStyleSelect = item;
			},
			clear() {},
			load() {
				this.initDataStyleSelect();
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
	.ai_jessica_widget_editor {
		position: relative;
		height: 100%;
	}
	.ai-jessica-widget-editor-toolbar {
		height: 40px;
	}
	.ai-jessica-widget-editor-content {
		height: calc(100% - 40px);
		overflow: hidden;
	}
	.ai-jessica-widget-editor-content .left-part {
		width: 260px;
		height: 100%;
		float: left;
		border-right: 1px solid #d3d3d3;
	}
	.ai-jessica-widget-editor-content .right-part {
		position: relative;
		width: calc(100% - 260px);
		height: 100%;
		float: left;
	}
	.ai-jessica-widget-editor-content .left-part .library-sidebar-module {
		width: 100%;
		height: 100%;
	}
	.ai-jessica-widget-editor-content .right-part .top-content {
		width: 100%;
		height: calc(100% - 23px);
		overflow: hidden;
	}
	.ai-jessica-widget-editor-content .right-part .top-content .left-content {
		position: relative;
		width: calc(100% - 220px);
		height: 100%;
		float: left;
		border-right: 1px solid #d3d3d3;
	}
	.ai-jessica-widget-editor-content
		.right-part
		.top-content
		.left-content
		.library-module {
		width: 100%;
		height: calc(100% - 204px);
	}
	.ai-jessica-widget-editor-content
		.right-part
		.top-content
		.left-content
		.console-module {
		position: absolute;
		left: 0;
		bottom: 0;
		width: 100%;
	}
	.ai-jessica-widget-editor-content .right-part .top-content .right-content {
		width: 220px;
		height: 100%;
		float: left;
	}
	.ai-jessica-widget-editor-content
		.right-part
		.top-content
		.right-content
		.data-style-module {
		width: 100%;
		height: 100%;
	}
	.ai-jessica-widget-editor-content
		.right-part
		.top-content
		.right-content
		.data-style-module
		.data-style-tab-header {
		font-weight: 400;
		color: #313131;
		overflow: hidden;
		cursor: pointer;
	}
	.ai-jessica-widget-editor-content
		.right-part
		.top-content
		.right-content
		.data-style-module
		.data-style-tab-header
		li {
		width: 110px;
		height: 30px;
		line-height: 30px;
		font-size: 12px;
		text-align: center;
		border: 1px solid #d3d3d3;
		background-color: #f4f4f4;
		float: left;
	}
	.ai-jessica-widget-editor-content
		.right-part
		.top-content
		.right-content
		.data-style-module
		.data-style-tab-header
		.tab-active {
		border: 1px solid #fff;
		background-color: #fff;
		color: #4b9fe9;
	}
	.ai-jessica-widget-editor-content
		.right-part
		.top-content
		.right-content
		.data-style-module
		.data-style-tab-content {
		width: 100%;
		height: calc(100% - 30px);
		overflow: auto;
	}

	.ai-jessica-widget-editor-content .right-part .bottom-content {
		position: absolute;
		left: 0;
		bottom: 0;
		width: 100%;
		height: 23px;
		line-height: 23px;
		padding: 0 10px;
		overflow: hidden;
		border-top: 1px solid rgba(211, 211, 211, 1);
	}
	.ai-jessica-widget-editor-content .right-part .bottom-content .tips-module {
		float: left;
		font-size: 12px;
	}
</style>