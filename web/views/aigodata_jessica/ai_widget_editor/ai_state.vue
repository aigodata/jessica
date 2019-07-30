<template>
	<!-- ai_jessica_widget_editor_state | 页脚 -->
	<div class="ai_jessica_widget_editor_state">
		<span class="tips-icon-compute">
			<i class="iconfont icondiannao"></i>
		</span>
		<span>当期那页面</span>
		<span class="editor-name">张三，李四</span>
		<span>正在编辑，</span>
		<span>上次保存时间:</span>
		<span class="editor-time">2019-07-24</span>
		<span>，</span>
		<span class="editor-auto-save-time">5S</span>
		<span>自动保存</span>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_editor_state",
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
	.ai_jessica_widget_editor_state {
		position: relative;
		height: 100%;
	}
	.ai_jessica_widget_editor_state .tips-icon-compute {
		margin-right: 5px;
	}
	.ai_jessica_widget_editor_state .tips-icon-compute i {
		font-size: 12px;
	}
	.ai_jessica_widget_editor_state .editor-name {
		color: #fd9334;
	}
	.ai_jessica_widget_editor_state .editor-time {
		color: #fd9334;
	}
	.ai_jessica_widget_editor_state .editor-auto-save-time {
		color: #fd9334;
	}
</style>