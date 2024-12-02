import { createRouter, createWebHistory } from 'vue-router'
import ArticleList from '@/components/article/Article-List.vue'
import ArticleCreate from '@/components/article/Article-Create.vue'
import ArticleView from '@/views/ArticleView.vue'
import DynamicList from '@/components/dynamic/Dynamic-List.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'home', component: ArticleList},
    { path: '/create', name: 'create',component: ArticleCreate },
    { path: '/article', name: 'article',component: ArticleView },
    { path: '/dynamic', name: 'dynamic',component: DynamicList },
  ]
})

export default router
