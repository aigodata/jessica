import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const EMPTY_VIEW = { template: '<router-view />' };

export default new Router({
    routes: [{
        path: '/',
        meta: { name: '/' },
        component: EMPTY_VIEW,
        children: [
            {
                path: '/',
                redirect: '/bi'
            },
            // {
            //     path: 'single',
            //     meta: { name: 'single' },
            //     component: EMPTY_VIEW,
            //     children: [
            //         {
            //             path: 'bi-editor',
            //             meta: { name: 'bi-editor' },
            //             component: () => import('./views/bi/bi-editor'),
            //         }
            //     ]
            // },
            {
                path: 'bi',
                meta: { name: 'bi' },
                component: () => import('./views/bi/main'),
                children: [
                    {
                        path: '/',
                        component: () => import('./views/bi/bi'),
                    },
                    {
                        path: 'editor',
                        meta: { name: 'bi-editor' },
                        component: () => import('./views/bi/bi-editor'),
                    }
                ]
            },
            {
                path: 'setting',
                meta: { name: 'setting' },
                component: () => import('./views/data-source/main'),
            }
        ]
    }]
});
