import {createWebHistory , createRouter} from 'vue-router'
import Home from '../pages/Home'
import Login from '../pages/Login'
import Cart from '../pages/Cart'
import Order from '../pages/Order'
import Orders from '../pages/Orders'
import CreateUser from '../pages/CreateUser'

const routes = [
    { 
        path: '/', 
        component: Home
    },
    {
        path:"/login",
        component: Login
    },
    {
        path:"/cart",
        component: Cart
    },
    {
        path:"/order",
        component: Order
    },
    {
        path:"/orders",
        component: Orders
    },
    {
        path:"/create-user",
        component: CreateUser
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes, 
})

export default router