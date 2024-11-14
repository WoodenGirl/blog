import { createRouter, createWebHistory } from 'vue-router'
import ArticleList from '@/views/article/Article-List.vue'
import ArticleCreate from '@/views/article/Article-Create.vue'
import ArticlePreview from '@/views/article/Article-Preview.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'home', component: ArticleList},
    { path: '/create', name: 'create',component: ArticleCreate },
    { path: '/preview/:articleJson', name: 'preview',component: ArticlePreview },
  ]
})

export default router
