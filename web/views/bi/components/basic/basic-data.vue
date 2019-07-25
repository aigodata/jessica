<template>
	<div class="b">
		<div class="1">
			<span>维度</span>
			<ul>
				<li>name</li>
			</ul>
		</div>
		<div>
			<el-form>
				<el-form-item label="活动形式">
					<el-input type="textarea" v-model="desc"></el-input>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	export default {
		name: "ai-basic-data",
		computed: {},
		watch: {},
		data() {
			return {
				desc: 123
			};
		},
		methods: {
			clear() {},
			load() {
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
				// var a = 'appId=468475&st=1561305600000&et=1563811200000&offset=0&pageSize=100&graph=0&customFilter=null&userGroup=0&order=new_user_ratio%2Cdesc&indexes=new_visitor%2Cchannel_id&indicators=new_user_ratio%2Cold_user_ratio&reportId=60&method=analytics%2Frealtime%2Fa&validateToken=aa';
				// var b = a.split('&');
				// var c = {};
				// b.forEach(function(item){
				// 	var d = item.split('=');
				// 	c[d[0]]
				// })
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
