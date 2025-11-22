import { createRouter,createWebHashHistory, createWebHistory} from "vue-router";
import Home from '@/compents/home.vue'
import Details from "@/compents/details.vue";

const routes = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'home',
        component: Home
    },
    {
        path: '/details',
        name: 'details',
        component: Details
    }

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router