import Vue from 'vue'
import App from './App.vue'
import router from './router'
import localStore from 'store'								// 浏览器存储

import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/zh-CN'

import './assets/styles/main.css'

Vue.use(ElementUI, { locale, size: 'small' });


// 添加实例方法, 使用本地存储插件
Vue.prototype.$localStore = localStore;

Vue.config.productionTip = false

let app = new Vue({
    router,
    render: h => h(App),
}).$mount('#app');

export default app;
