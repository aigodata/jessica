<template>
	<!-- 
        UI
        依靠 watched 数据联动
        1.左侧组件.vue
            组件 (表格,图表):
                1.内部绑定 name .. 动态切换
                2.根据数据过滤维度、指标、
        2.右侧
            2.1 顶部.vue
                数据源组件
                    库表选择、过滤查询...还没想好
            2.2 左侧.vue
                组件展示
            2.3 右侧.vue
                2.3.1 样式组件 (color,font-size,...)
                    根据左侧选择组件动态渲染不同选项 (暂定 2 种格式: table , chart 用类别区分)
                2.3.2 数据组件	(维度,指标,...)
                    根据左侧选择组件动态渲染不同选项 (暂定 2 种格式: table , chart 用类别区分)


        怎么做数据过滤(维度,指标,表格...)....怎么做样式组件过滤()

        table
            Data:
                datadeck.com 提供的设置
                1.Metric
                2.Category
                3.Filter

                
        line

        bar

        pie

        ...

        保存...

        let dataSource = [
            {
                date: "2016-05-01",
                name: "王小1",
                age: 11,
                address: "上海市普陀区金沙江路 51 弄"
            },
            {
                date: "2016-05-02",
                name: "王小2",
                age: 8,
                address: "上海市普陀区金沙江路 52 弄"
            },
            {
                date: "2016-05-03",
                name: "王小3",
                age: 4,
                address: "上海市普陀区金沙江路 53 弄"
            },
            {
                date: "2016-05-04",
                name: "王小4",
                age: 20,
                address: "上海市普陀区金沙江路 54 弄"
            }
        ];
	-->
	<div class="ai-layout-bi-container">
		<!-- 侧边栏 - 组件 -->
		<div class="ai-layout-sidebar-component">
			<div class="seach-module">
				<input type="text">
			</div>
			<el-collapse v-model="activeName" accordion>
				<el-collapse-item
					class="component-module"
					v-for="item in componentList"
					:key="item.type"
					:title="item.label"
					:name="item.type"
				>
					<ul
						class="component-list"
						v-if="item.components.length"
						v-for="childItem in item.components"
						:key="item.type + '=>' + childItem.valuel"
					>
						<li
							class="component-item"
							:class="{ active: funcAcitveName == childItem.value }"
							@click="change(childItem.value)"
						>{{ childItem.label }}</li>
					</ul>
				</el-collapse-item>
			</el-collapse>
		</div>
		<!-- 组件展示 -->
		<div class="ai-layout-component-display">
			<p>我是展示区域</p>
			<bi-component v-if="componentInfo.name" :name="componentInfo.name"></bi-component>
		</div>
		<!-- 组件设置 -->
		<div class="ai-layout-component-setting">
			<p>
				<button
					v-for="item in settingTypes"
					:key="item.value"
					:class="{ 'setting-active' : currentSettingType == item.value }"
					@click="settingChange(item.value)"
				>{{ item.label }}</button>
			</p>
			<bi-component v-if="transferSettingType" :name="transferSettingType"></bi-component>
		</div>
	</div>
</template>

<script>
	import BIComponent from "./components/bi-component";
	export default {
		components: {
			"bi-component": BIComponent
		},
		computed: {},
		watch: {},
		data() {
			return {
				// 组件列表
				componentList: [],
				// 组件类型
				activeName: "",
				// 组件名称
				funcAcitveName: "",
				// 组件信息
				componentInfo: {
					// 组件类型(PS: basic,chart)
					type: "",
					// 组件名称(PS: table,line,...)
					name: ""
				},
				// 配置类型(PS: display => 样式; data => 数据)
				settingTypes: [
					{
						label: "Data",
						value: "data"
					},
					{
						label: "Display",
						value: "display"
					}
				],
				// 当前选中的配置资源
				currentSettingType: "",
				// 组件传递的配置类型
				transferSettingType: ""
			};
		},
		methods: {
			initComponent() {
				this.componentList = [
					{
						label: "图表",
						type: "chart",
						components: [
							{
								label: "折线图 - Line",
								value: "ai-line"
							}
						]
					},
					{
						label: "组件",
						type: "basic",
						components: [
							{
								label: "表格 - Table",
								value: "ai-table"
							}
						]
					}
				];
			},
			// 初始化组件设置
			initSetting() {
				// 只选中不加载组件实例
				this.currentSettingType = this.settingTypes[0].value;
			},
			// 组件功能切换
			change(val) {
				this.funcAcitveName = val;
				this.componentInfo = {
					type: this.activeName,
					name: this.funcAcitveName
				};
				this.settingChange();
			},
			// 组件设置切换
			settingChange(name) {
				name = name || this.currentSettingType;
				this.currentSettingType = name;
				this.transferSettingType = "";
				this.$nextTick(() => {
					this.transferSettingType = `ai-${
						this.componentInfo.type
					}-${name}`;
				});
			},
			clear() {},
			load() {
				this.initComponent();
				this.initSetting();

				/***
				 * line
				 * 		xAxis
				 * 		yAxis
				 * pie
				 * 		legend
				 * 			1.可根据数值为数字的进行第一步过滤
				 * bar
				 */

				let dataSource = [
					{
						date: "2016-05-01",
						name: "王小1",
						age: 11,
						address: "上海市普陀区金沙江路 51 弄",
						city: "上海"
					},
					{
						date: "2016-05-02",
						name: "王小2",
						age: 8,
						address: "上海市普陀区金沙江路 52 弄",
						city: "上海"
					},
					{
						date: "2016-05-03",
						name: "王小3",
						age: 4,
						address: "上海市普陀区金沙江路 53 弄",
						city: "上海"
					},
					{
						date: "2016-05-04",
						name: "王小4",
						age: 20,
						address: "上海市普陀区金沙江路 54 弄",
						city: "上海"
					}
				];
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
	@import url(./assets/styles/main.css);
	.setting-active {
		background-color: lime;
	}
</style>
