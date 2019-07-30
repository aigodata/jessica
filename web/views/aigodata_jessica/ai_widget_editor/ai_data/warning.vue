<template>
	<div class="ai_jessica_widget_data_warning">
		<p class="data-warning-label">预警</p>
		<div class="data-warning-select-module">
			<el-select v-model="currentWarning" placeholder="请选择">
				<el-option
					v-for="item in warningList"
					:key="item.value"
					:label="item.label"
					:value="item.value"
				></el-option>
			</el-select>
		</div>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_data_warning",
		computed: {},
		watch: {},
		data() {
			return {
				warningList: [],
				currentWarning: ""
			};
		},
		methods: {
			initWarning() {
				this.warningList = [
					{
						value: "选项1",
						label: "预警 1"
					},
					{
						value: "选项2",
						label: "预警 2"
					},
					{
						value: "选项3",
						label: "预警 3"
					},
					{
						value: "选项4",
						label: "预警 4"
					}
				];
			},
			clear() {},
			load() {
				this.initWarning();
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
	.ai_jessica_widget_data_warning {
		position: relative;
		height: 100%;
		font-size: 12px;
		font-weight: 400;
		color: #313131;
		overflow: hidden;
	}
	.ai_jessica_widget_data_warning .data-warning-label {
		line-height: 28px;
	}
	.ai_jessica_widget_data_warning .data-warning-select-module {
		height: 28px;
    }
    .ai_jessica_widget_data_warning .data-warning-select-module .el-select
    .el-input__inner {
        height: 28px;
        line-height: 28px;
    }
</style>
