import { createRouter, createWebHistory } from 'vue-router';
import mainComponent from '../components/MainComponent.vue';
import viewComponent from '../components/ViewComponent.vue';

const routes = [
    {
        path: '/',
        component: mainComponent,
    },
    {
        path: '/view',
        component: viewComponent,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;