import { createRouter, createWebHistory } from 'vue-router'
import ArticleCreate from '@/components/article/Article-Create.vue'
import DynamicList from '@/components/dynamic/Dynamic-List.vue'
import ArticlePreview from '@/components/article/Article-Preview.vue'
import AcgView from '@/views/template/AcgView.vue'
import InterestView from '@/views/template/InterestView.vue'
import TechniqueView from '@/views/template/TechniqueView.vue'
import LifeView from '@/views/template/LifeView.vue'
import IndexView from '@/views/template/IndexView.vue'
import TemplateView from '@/components/layout/TemplateView.vue'
import LoginView from '@/views/login/LoginView.vue'
import BlogLayout from '@/components/layout/Blog-Layout.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/layout', name: 'layout', component: BlogLayout, children: [
        { path: '/template', name: 'view', component: TemplateView, children: [
            { path: '/', name: 'index', component: IndexView},
            { path: '/acg', name: 'acg', component: AcgView},
            { path: '/interest', name: 'interest', component: InterestView},
            { path: '/technique', name: 'technique', component: TechniqueView},
            { path: '/life', name: 'life', component: LifeView},
          ]
        },
        { path: '/create', name: 'create',component: ArticleCreate },
        { path: '/article/:articleId', name: 'article',component: ArticlePreview },
        { path: '/dynamic', name: 'dynamic',component: DynamicList },
      ]
    },
    { path: '/login', name: 'login',component: LoginView },
  ]
})

export default router
