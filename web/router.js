import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Main from './views/main.vue'

const EMPTY_VIEW = { template: '<router-view />' };

export default new Router({
    routes: [{
        path: '/',
        meta: { name: '/' },
        component: EMPTY_VIEW,
        children: [
            {
                path: '/',
                redirect: '/main'
            },
            {
                path: 'main',
                meta: { name: 'main' },
                component: Main,
                children: [
                    // {
                    //     path: 'test',
                    //     meta: { name: 'test' },
                    //     component: () => import('./views/test'),
                    // }
                ]
            }
        ]
    }]
});
