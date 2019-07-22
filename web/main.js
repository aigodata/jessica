import Vue from 'vue'
import App from './App.vue'
import router from './router'
import localStore from 'store' // 浏览器存储
import store from './store.js'
import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/zh-CN'

import './assets/styles/main.css'
import './assets/fonts/iconfont.css' // 图标字体
import './assets/fonts/iconfont.js'
import 'element-ui/lib/theme-chalk/index.css' // element-ui
Vue.use(ElementUI, { locale, size: 'small' });


// 添加实例方法, 使用本地存储插件
Vue.prototype.$localStore = localStore;

Vue.config.productionTip = false

let app = new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');

export default app;