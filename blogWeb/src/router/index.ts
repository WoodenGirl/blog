import { createRouter, createWebHistory } from 'vue-router'
import ArticleList from '@/components/article/Article-List.vue'
import ArticleCreate from '@/components/article/Article-Create.vue'
import DynamicList from '@/components/dynamic/Dynamic-List.vue'
import ArticlePreview from '@/components/article/Article-Preview.vue'
import AcgView from '@/views/AcgView.vue'
import InterestView from '@/views/InterestView.vue'
import TechniqueView from '@/views/TechniqueView.vue'
import LifeView from '@/views/LifeView.vue'
import IndexView from '@/views/IndexView.vue'
import TemplateView from '@/views/TemplateView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/template', name: 'view', component: TemplateView, children: [
        { path: '/', name: 'index', component: IndexView},
        { path: '/acg', name: 'acg', component: AcgView},
        { path: '/interest', name: 'interest', component: InterestView},
        { path: '/technique', name: 'technique', component: TechniqueView},
        { path: '/life', name: 'life', component: LifeView},
      ]
    },


    { path: '/create', name: 'create',component: ArticleCreate },
    { path: '/article', name: 'article',component: ArticlePreview },
    { path: '/dynamic', name: 'dynamic',component: DynamicList },
  ]
})

export default router
