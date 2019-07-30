<template>
	<!-- aigodata_jessica -->
	<div class="aigodata_jessica">
		<div class="ai-jessica-page-tree" @click="pageTreeClick">
			<v-jstree
				class="directory-container"
				v-if="treeData.length"
				:data="treeData"
				draggable
				@item-click="itemClick"
				@item-drag-end="itemDragEnd"
			>
				<template scope="scope">
					<div class="directory-module">
						<div class="directory-node-content">
							<span class="icon-label">
								<i class="iconfont iconyidongshu directory-icon"></i>
							</span>
							<span
								class="icon-label icon-label-rotate-right"
								:class="{ 'icon-label-rotate-down': scope.model.opened }"
							>
								<i
									class="iconfont iconsanjiaoxing directory-arrow-icon"
									v-if="scope.model.type == 'directory'"
									@click="directoryChange(scope)"
								></i>
							</span>
							<span class="icon-label">
								<i
									class="iconfont iconwenjianjia directory-file-icon"
									v-if="scope.model.type == 'directory'"
								></i>
								<i class="iconfont iconwenjian directory-file-icon" v-else></i>
							</span>
							<span class="directory-name">{{ scope.model.label }}</span>
							<el-input
								v-if="scope.model.key == currNode.key && currNode.labelEditor"
								v-model="scope.model.label"
								:class="{ directory: scope.model.type == 'directory' }"
								placeholder="请输入内容"
								@blur="directoryOrFileBlur"
							></el-input>
							<span class="icon-label directory-setting" @click="settingDirectoryOrFile($event,scope)">···</span>
						</div>
					</div>
				</template>
			</v-jstree>
			<!-- 新增节点 -->
			<div class="directory-operate-container">
				<div class="directory-operate-add-item" @click="addDirectory">
					<span class="item-icon">
						<i class="iconfont iconxinzeng"></i>
					</span>
					<span class="item-label">添加菜单</span>
				</div>
			</div>
			<!-- 目录结构操作弹层 -->
			<div class="structure-editor-content" ref="structure-editor-content">
				<ul class="structure-editor-module">
					<li
						class="structure-editor-item"
						v-if="currNode.type == 'directory'"
						@click="settingOperateClick($event,'addDirectory')"
					>
						<span class="item-icon">
							<i class="iconfont iconwenjianjia"></i>
						</span>
						<span class="item-label">添加文件夹</span>
					</li>
					<li
						class="structure-editor-item"
						v-if="currNode.type == 'directory'"
						@click="settingOperateClick($event,'addFile')"
					>
						<span class="item-icon">
							<i class="iconfont iconwenjian"></i>
						</span>
						<span class="item-label">添加页面</span>
					</li>
					<li class="structure-editor-item" @click="settingOperateClick($event,'edit')">
						<span class="item-icon">
							<i class="iconfont iconxiugai"></i>
						</span>
						<span class="item-label">编辑</span>
					</li>
					<li class="structure-editor-item" @click="settingOperateClick($event,'copy')">
						<span class="item-icon">
							<i class="iconfont iconfuzhi"></i>
						</span>
						<span class="item-label">复制</span>
					</li>
					<li class="structure-editor-item" @click="settingOperateClick($event,'delete')">
						<span class="item-icon">
							<i class="iconfont iconshanchu"></i>
						</span>
						<span class="item-label">删除</span>
					</li>
				</ul>
			</div>
		</div>
		<div class="ai-jessica-page">
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
	import VJstree from "vue-jstree";
	export default {
		name: "aigodata_jessica",
		components: {
			VJstree
		},
		computed: {},
		watch: {},
		data() {
			return {
				//
				treeData: [],
				// 当前节点元素
				currNode: {},
				//  文件夹计数
				folderNum: 1,
				//  文件计数
				fileNum: 1
			};
		},
		methods: {
			initTreeData() {
				this.treeData = [
					{
						label: "测试模块页面",
						type: "directory",
						key: 1,
						children: [
							{
								label: "我是个目录",
								type: "directory",
								children: [
									{
										label: "index页面 1",
										type: "file"
									}
								]
							},
							{
								label: "index页面 2",
								type: "file"
							}
						]
					},
					{
						label: "测试模块页面 1",
						type: "directory",
						children: [
							{
								label: "测试模块页面 1-1",
								type: "directory",
								children: [
									{
										label: "测试模块页面 1-1-1",
										type: "directory",
										children: [
											{
												label: "测试模块页面 1-1-1-1",
												type: "directory",
												children: []
											}
										]
									}
								]
							}
						]
					}
				];
			},
			// 新增目录结构
			addDirectory() {
				this.treeData.push({
					label: "未命名文件夹",
					type: "directory",
					key: this.treeData.length,
					children: []
				});
			},
			pageTreeClick() {
				let d = this.$refs["structure-editor-content"];
				d.style.cssText = `display:none;top:0px`;
			},
			itemClick(node) {
				// console.log(node.model + " clicked !");
			},
			itemDragEnd() {
				console.info('itemDragEnd====>>' ,this.treeData)
				// 重新更新KEY ?
			},
			// 目前展开收起
			directoryChange(node) {
				node.model.opened = !node.model.opened;
			},
			// 设置 文件夹 或 文件
			settingDirectoryOrFile(event, item) {
				event.preventDefault();
				event.stopPropagation();
				this.currNode = item.model;
				let t = event.target;
				let p = t.parentNode.parentNode.parentNode.parentNode;
				let p_h = p.querySelector(".tree-anchor").offsetHeight;
				let p_y = p.offsetTop;
				let d = this.$refs["structure-editor-content"];
				d.style.cssText = `display:block;top:${p_y + p_h}px`;
			},
			settingOperateClick(event, type) {
				event.preventDefault();
				event.stopPropagation();
				let _this = this;
				// //  文件夹计数
				// folderNum: 1,
				// //  文件计数
				// fileNum: 1
				let t = {
					label: "测试模块页面",
					type: "directory",
					key: 1,
					children: []
				};
				let treeData = JSON.parse(JSON.stringify(this.treeData));
				let serviceController = {
					addDirectory() {
						let k = Array.isArray(_this.currNode.children)
							? _this.currNode.children.length
							: 0;
						_this.currNode.children.push({
							label: `未命名文件${_this.folderNum}`,
							type: "directory",
							key: `${_this.currNode.key}-${k}`
						});
						_this.folderNum++;
						this.updateDirectory();
					},
					addFile() {
						let k = Array.isArray(_this.currNode.children)
							? _this.currNode.children.length
							: 0;
						_this.currNode.children.push({
							label: `未命名文件${_this.fileNum}`,
							type: "file",
							key: `${_this.currNode.key}-${k}`
						});
						_this.fileNum++;
						this.updateDirectory();
					},
					edit() {
						_this.currNode.labelEditor = true;
						_this.$set(_this, "treeData", []);
						setTimeout(() => {
							_this.$set(_this, "treeData", treeData);
						});
					},
					copy() {},
					delete() {
						let key = String(_this.currNode.key).split("-");
						recursiveNode(key, treeData);
						function recursiveNode(keys, tree) {
							if (keys.length) {
								let k = keys.shift();
								if (!keys.length) {
									tree.children.forEach((item, index) => {
										if (item.key == _this.currNode.key) {
											tree.children.splice(index, 1);
										}
									});
								} else {
									recursiveNode(keys, tree[k]);
								}
							}
						}
						_this.$set(_this, "treeData", []);
						setTimeout(() => {
							_this.$set(_this, "treeData", treeData);
						});
					},
					updateDirectory() {
						let key = String(_this.currNode.key).split("-");
						recursiveNode(key, treeData);
						function recursiveNode(keys, tree) {
							if (keys.length) {
								let k = keys.shift();
								recursiveNode(keys, tree[k]);
							} else {
								tree.children = _this.currNode.children;
							}
						}
						_this.$set(_this, "treeData", []);
						setTimeout(() => {
							_this.$set(_this, "treeData", treeData);
						});
					}
				};
				typeof serviceController[type] == "function" &&
					serviceController[type]();
				this.pageTreeClick();
			},
			directoryOrFileBlur() {
				this.currNode.labelEditor = false;
				let treeData = JSON.parse(JSON.stringify(this.treeData));
				this.$set(this, "treeData", []);
				setTimeout(() => {
					this.$set(this, "treeData", treeData);
				});
			},
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
	.aigodata_jessica {
		position: relative;
		height: 100%;
	}
	.aigodata_jessica .ai-jessica-page-tree {
		position: absolute;
		left: 0;
		top: 0;
		width: 220px;
		height: 100%;
		overflow: auto;
		border-right: 1px solid rgba(211, 211, 211, 1);
	}
	.aigodata_jessica .ai-jessica-page-tree .directory-container {
		height: 100%;
		overflow: auto;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.tree-container-ul:first-child {
		height: 100%;
		overflow: auto;
		padding-bottom: 50px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.tree-container-ul
		li {
	}
	.aigodata_jessica .ai-jessica-page-tree .directory-container .directory-module {
		overflow: hidden;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.directory-module
		.directory-node-content {
		position: relative;
		height: 24px;
		line-height: 24px;
	}
	.aigodata_jessica .ai-jessica-page-tree .directory-container .icon-label {
		float: left;
		margin-right: 2px;
		cursor: pointer;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.icon-label-rotate-right {
		transform: rotate(-90deg);
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.icon-label-rotate-down {
		transform: rotate(0deg);
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.icon-label
		.iconfont {
		font-size: 14px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.icon-label
		.directory-arrow-icon {
		font-size: 12px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.directory-module
		.directory-name {
		float: left;
		width: 110px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		vertical-align: middle;
		font-size: 12px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.directory-module
		.el-input {
		position: absolute;
		top: 0;
		left: 30px;
		z-index: 2;
		width: 110px;
		height: 24px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.directory-module
		.el-input.directory {
		left: 45px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.directory-module
		.el-input
		input {
		height: 24px;
		line-height: 24px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.directory-module
		.directory-setting {
		font-size: 12px;
		float: right;
	}
	.aigodata_jessica .ai-jessica-page {
		width: 100%;
		height: 100%;
		padding-left: 220px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.tree-container-ul
		.tree-icon,
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.tree-container-ul
		.tree-node {
		background-image: none;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.tree-container-ul
		.tree-node
		.tree-selected {
		color: #fff;
		background: linear-gradient(
			-44deg,
			rgba(75, 159, 233, 1),
			rgba(90, 174, 248, 1)
		);
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.tree-container-ul
		.tree-icon {
		display: none;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-container
		.tree-container-ul
		.tree-node
		.tree-anchor {
		width: 100%;
		padding: 0 5px;
	}
	.aigodata_jessica .ai-jessica-page-tree .directory-operate-container {
		position: absolute;
		left: 0;
		bottom: 10px;
		width: 100%;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-operate-container
		.directory-operate-add-item {
		width: 199px;
		height: 32px;
		line-height: 32px;
		background: linear-gradient(
			0deg,
			rgba(30, 159, 242, 1),
			rgba(53, 176, 255, 1)
		);
		box-shadow: -1px 3px 8px 0px rgba(30, 159, 242, 0.3);
		border-radius: 3px;
		margin: 0 auto;
		text-align: center;
		cursor: pointer;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-operate-container
		.directory-operate-add-item
		.item-icon {
		margin-right: 10px;
		color: #ffffff;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-operate-container
		.directory-operate-add-item
		.item-icon
		i {
		font-size: 12px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.directory-operate-container
		.directory-operate-add-item
		.item-label {
		font-size: 12px;
		color: #ffffff;
	}
	.aigodata_jessica .ai-jessica-page-tree .structure-editor-content {
		position: absolute;
		right: 0;
		z-index: 2;
		width: 130px;
		background: #fff;
		border: 1px solid rgba(232, 232, 232, 1);
		box-shadow: 0px 8px 18px 0px rgba(126, 126, 126, 0.08),
			0px 1px 4px 0px rgba(13, 5, 9, 0.05);
		border-radius: 2px;
		display: none;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.structure-editor-content
		.structure-editor-module {
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.structure-editor-content
		.structure-editor-module
		.structure-editor-item {
		padding: 0 20px;
		line-height: 30px;
		font-size: 12px;
		color: #313131;
		cursor: pointer;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.structure-editor-content
		.structure-editor-module
		.structure-editor-item:hover {
		color: #fff;
		background: linear-gradient(
			0deg,
			rgba(42, 161, 239, 1),
			rgba(55, 176, 255, 1)
		);
		box-shadow: 0px 3px 12px 0px rgba(49, 167, 244, 0.38);
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.structure-editor-content
		.structure-editor-module
		.structure-editor-item
		.item-icon {
		margin-right: 10px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.structure-editor-content
		.structure-editor-module
		.structure-editor-item
		.item-icon
		.item-icon
		i {
		font-size: 12px;
	}
	.aigodata_jessica
		.ai-jessica-page-tree
		.structure-editor-content
		.structure-editor-module
		.structure-editor-item
		.item-label {
	}
</style>