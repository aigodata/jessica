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
                redirect: 'main'
            },
            {
                path: 'main',
                meta: { name: 'main' },
                component: () => import('./views/main'),
                children: [
                    {
                        path: '/',
                        redirect: 'aigodata_jessica'
                    },
                    {
                        path: 'aigodata_jessica',
                        meta: { name: '商业BI' },
                        component: () => import('./views/aigodata_jessica/aigodata_jessica'),
                        children: [
                            {
                                path: '/',
                                redirect: 'ai_dashboard'
                            },
                            {
                                path: 'ai_dashboard',
                                meta: { name: '仪表盘' },
                                component: () => import('./views/aigodata_jessica/ai_dashboard/ai_dashboard')
                            },
                            {
                                path: 'ai_widget_editor',
                                meta: { name: '数据编辑器' },
                                component: () => import('./views/aigodata_jessica/ai_widget_editor/ai_widget_editor')
                            }
                        ]
                    },
                    {
                        path: 'ai_data_indicator',
                        meta: { name: '数据指标' },
                        component: () => import('./views/aigodata_jessica/ai_data_indicator/ai_data_indicator')
                    },
                    {
                        path: 'ai_data_source',
                        meta: { name: '数据源' },
                        component: () => import('./views/aigodata_jessica/ai_data_source/ai_data_source')
                    },
                    {
                        path: 'ai_data_dictionary',
                        meta: { name: '数据字典' },
                        component: () => import('./views/aigodata_jessica/ai_data_dictionary/ai_data_dictionary')
                    }
                ]
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
        ]
    }]
});
