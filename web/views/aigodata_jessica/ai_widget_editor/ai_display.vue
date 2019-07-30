<template>
	<!-- ai_jessica_widget_display | 组件展示 -->
	<div class="ai_jessica_widget_display" ref="ai_jessica_widget_display">
		<div class="display-search-module">
			<span class="display-search-icon-search">
				<i class="iconfont icon-search"></i>
			</span>
			<el-input v-model="searchParams" placeholder="搜索">
				<i slot="suffix" class="iconfont iconhuiche"></i>
			</el-input>
		</div>
		<div class="display-component-content">
			<el-collapse v-model="cmmType" accordion @change="handleChange">
				<el-collapse-item
					v-for="item in cmmList"
					:key="item.value"
					:title="item.label"
					:name="item.value"
				>
					<div class="display-component-module">
						<ul class="display-component-label-content">
							<li
								class="display-component-label-item"
								v-for="childItem in item.children"
								:key="childItem.value"
								:class="{ active: childItem.value == currCMMModule.value}"
								@click="componentTypeChange(childItem)"
							>{{ childItem.label }}</li>
						</ul>
						<ul class="display-component-label-info-content">
							<li
								class="display-component-label-info-item"
								v-for="childItem in currCMMModule.children"
								:key="childItem.value"
								:class="{ active: childItem.value == currCMMModuleShape.value}"
								@click="componentDispalyMethodChange(childItem)"
							>
								<img class="display-component-label-info-item-icon" :src="childItem.icon || ''">
								<span class="display-component-label-info-item-label">{{ childItem.label }}</span>
							</li>
						</ul>
					</div>
				</el-collapse-item>
			</el-collapse>
		</div>
	</div>
</template>

<script>
	export default {
		name: "ai_jessica_widget_display",
		computed: {},
		watch: {},
		data() {
			return {
				// 搜索内容
				searchParams: "",
				// 组件大类(PS: chart,component)
				cmmType: "chart",
				// 组件列表
				cmmList: [],
				// 当前选中组件模块
				currCMMModule: {},
				// 当前选中组件模块展示形式
				currCMMModuleShape: {}
			};
		},
		methods: {
			initComponent() {
				this.cmmList = [
					{
						label: "图表",
						value: "chart",
						children: [
							{
								label: "折线图",
								value: "ai-line",
								children: [
									{
										label: "折线图",
										icon: require("./ai_display/assets/images/chart.png"),
										value: "ai-line-1"
									},
									{
										label: "折线图",
										value: "ai-line-2"
									},
									{
										label: "折线图",
										value: "ai-line-3"
									},
									{
										label: "折线图",
										value: "ai-line-4"
									},
									{
										label: "折线图",
										value: "ai-line-5"
									}
								]
							},
							{
								label: "柱状图",
								value: "ai-line1",
								children: [
									{
										label: "柱状图",
										icon: require("./ai_display/assets/images/chart.png"),
										value: "ai-line-1-1"
									}
								]
							},
							{
								label: "饼图",
								value: "ai-line2"
							},
							{
								label: "条形图",
								value: "ai-line3"
							},
							{
								label: "散点图",
								value: "ai-line4"
							},
							{
								label: "面积图",
								value: "ai-line5"
							},
							{
								label: "箱型图",
								value: "ai-line6"
							},
							{
								label: "烛型图",
								value: "ai-line7"
							},
							{
								label: "热力图",
								value: "ai-line8"
							},
							{
								label: "仪表盘",
								value: "ai-line9"
							},
							{
								label: "漏斗图",
								value: "ai-line10"
							},
							{
								label: "地图",
								value: "ai-line11"
							},
							{
								label: "雷达图",
								value: "ai-line12"
							},
							{
								label: "分面",
								value: "ai-line13"
							},
							{
								label: "关系图",
								value: "ai-line14"
							},
							{
								label: "其他",
								value: "ai-line99"
							},
							{
								label: "雷达图",
								value: "ai-line15"
							},
							{
								label: "分面",
								value: "ai-line16"
							},
							{
								label: "关系图",
								value: "ai-line17"
							},
							{
								label: "其他",
								value: "ai-line18"
							}
						]
					},
					{
						label: "组件",
						value: "basic",
						children: [
							{
								label: "表格 - Table",
								value: "ai-table"
							}
						]
					},
					{
						label: "其他",
						value: "other"
					}
				];
				this.currCMMModule = this.cmmList[0].children[0];
			},
			handleChange(val) {
				let t = this.cmmList.filter(item => item.value == val);
				this.currCMMModule = Array.isArray(t[0].children) ? t[0].children[0] : [];
			},
			// 组件类型切换
			componentTypeChange(item) {
				this.currCMMModule = item;
			},
			// 组件展示方式切换
			componentDispalyMethodChange(item) {
				this.currCMMModuleShape = item;
			},
			// 窗口改变
			resize() {
				let t = this.$refs.ai_jessica_widget_display;
				let ct = [].slice.call(
					t.querySelectorAll(".display-component-module")
				);
				let h = t.offsetHeight - 50 - ct.length * 30;
				ct.forEach(item => {
					item.style.cssText = `height:${h}px;`;
				});
			},
			clear() {
				window.removeEventListener("resize", this.resize, false);
			},
			load() {
				window.addEventListener("resize", this.resize, false);
				this.initComponent();
				this.$nextTick(() => {
					this.resize();
				});
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
	.ai_jessica_widget_display {
		position: relative;
		width: 100%;
		height: 100%;
		color: #313131;
	}
	.ai_jessica_widget_display .display-search-module {
		position: relative;
		padding: 10px;
	}
	.ai_jessica_widget_display .display-search-module i {
		font-size: 12px;
	}
	.ai_jessica_widget_display .display-search-module .display-search-icon-search {
		position: absolute;
		left: 21px;
		top: 50%;
		transform: translateY(-50%);
		z-index: 1;
		color: #666666;
		cursor: pointer;
	}
	.ai_jessica_widget_display .display-search-module .el-input {
		width: 238px;
		height: 30px;
	}
	.ai_jessica_widget_display .display-search-module .el-input input {
		padding-left: 31px;
	}
	.ai_jessica_widget_display
		.display-search-module
		.el-input
		.el-input__suffix-inner {
		position: absolute;
		top: 50%;
		right: 5px;
		transform: translateY(-50%);
		color: #999999;
		cursor: pointer;
	}
	.ai_jessica_widget_display .display-component-content {
	}
	.ai_jessica_widget_display
		.display-component-content
		.el-collapse-item__header {
		height: 30px;
		line-height: 30px;
		padding: 0 12px;
		background-color: #f4f4f4;
		border-bottom: 1px solid #d3d3d3;
	}
	.ai_jessica_widget_display
		.display-component-content
		.el-collapse-item__content {
		width: 100%;
		height: 100%;
		padding-bottom: 0;
		font-size: 12px;
	}
	.display-component-module {
		width: 100%;
		height: 100%;
		overflow: hidden;
	}
	.display-component-module .display-component-label-content {
		float: left;
		width: 76px;
		height: 100%;
		border-right: 1px solid #d3d3d3;
		font-size: 12px;
		overflow: auto;
	}
	.display-component-module
		.display-component-label-content
		.display-component-label-item {
		position: relative;
		padding: 1px 18px;
		text-align: right;
		cursor: pointer;
	}
	.display-component-module
		.display-component-label-content
		.display-component-label-item.active {
		color: #2f9fe9;
		border-right: 2px solid #2f9fe9;
	}
	.display-component-module .display-component-label-info-content {
		float: left;
		width: calc(100% - 76px);
		height: 100%;
		padding: 1px 3px;
		overflow: auto;
	}
	.display-component-module
		.display-component-label-info-content
		.display-component-label-info-item {
		position: relative;
		width: 75px;
		height: 84px;
		padding: 6px;
		margin: 5px;
		float: left;
		cursor: pointer;
	}
	.display-component-module
		.display-component-label-info-content
		.display-component-label-info-item.active {
		border: 1px solid #2f9fe9;
	}
	.display-component-module
		.display-component-label-info-content
		.display-component-label-info-item
		.display-component-label-info-item-icon {
		display: block;
		width: 100%;
		margin-bottom: 5px;
	}
	.display-component-module
		.display-component-label-info-content
		.display-component-label-info-item
		.display-component-label-info-item-label {
		display: block;
		line-height: 20px;
		font-size: 10px;
		text-align: center;
	}
</style>