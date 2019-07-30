a<template>
	<!-- 事件下钻 -->
	<div class="ai_jessica_widget_console_event_drill" :class="{ hide: !isDisplay}">
		<ul class="console-event-drill-select">
			<li
				class="console-event-drill-select-modlue"
				v-for="item in selectList"
				:key="item.value"
				:class="{ active: item.value == currentActiveItem.value }"
				@click="selectChange(item)"
			>{{ item.label }}</li>
		</ul>
		<div class="console-event-drill-content"></div>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_console_event_drill",
		props: {
			isDisplay: {
				type: Boolean,
				default: true
			}
		},
		computed: {},
		watch: {},
		data() {
			return {
				// 类型列表
				selectList: [],
				// 当前选中类型
				currentActiveItem: {}
			};
		},
		methods: {
			initSelectLabel() {
				this.selectList = [
					{
						label: "下钻类型A",
						value: 1
					},
					{
						label: "下钻类型B",
						value: 2
					},
					{
						label: "下钻类型C",
						value: 3
					}
				];
				this.currentActiveItem = this.selectList[0];
			},
			// 选项切换
			selectChange(item) {
				this.currentActiveItem = item;
			},
			clear() {},
			load() {
				this.initSelectLabel();
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
	.ai_jessica_widget_console_event_drill {
		position: relative;
		width: 100%;
		height: 100%;
		overflow: hidden;
		transition: height .2s linear;
	}
	.ai_jessica_widget_console_event_drill.hide {
		height: 0;
	}
	.ai_jessica_widget_console_event_drill .console-event-drill-select {
        height: 28px;
		line-height: 27px;
		border-bottom: 1px solid #d3d3d3;
	}
	.ai_jessica_widget_console_event_drill
		.console-event-drill-select
		.console-event-drill-select-modlue {
		display: inline-block;
		padding: 0 24px;
		border-right: 1px solid #d3d3d3;
		cursor: pointer;
	}
	.ai_jessica_widget_console_event_drill
		.console-event-drill-select
		.console-event-drill-select-modlue.active {
		background-color: #fff;
		border-bottom: 1px solid #fff;
	}
	.ai_jessica_widget_console_event_drill .console-event-drill-content {
		height: 122px;
		background-color: #fff;
	}
</style>