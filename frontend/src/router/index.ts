import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import HireView from '../views/HireView.vue'
import GetJobView from '../views/GetJobView.vue'


const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/hire',
    name: 'hire',
    component: HireView
  },
  {
    path: '/get-job',
    name: 'get-job',
    component: GetJobView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
