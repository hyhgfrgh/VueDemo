import { createRouter,createWebHashHistory, createWebHistory} from "vue-router";
import Home from '@/pages/home.vue'
import Details from "@/pages/details.vue";
import Login from "@/pages/login.vue";
import Register from "@/pages/register.vue";

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
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/register',
        name: 'register',
        component: Register
    }

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router