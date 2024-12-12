import { defineStore } from 'pinia'
import { ref } from 'vue'
import { queryArticleByCategoryId } from '@/api/article'
import type { Article } from '@/entity/article'

export const useArticlesStore = defineStore('articles', () => {
  const articles = ref<Article[]>([])
  const article = ref<Article>()
  const queryAll = ref<boolean>(true)
  const isEdit = ref<boolean>(false)
  const fetchArticles = async (id: number) => {
    // 获取数据
    articles.value = await queryArticleByCategoryId(id).then(res => res.data);
    // 对数据根据创建时间排序
    articles.value.sort((a, b) => new Date(b.createdTime).getTime() - new Date(a.createdTime).getTime() )
  }
  return { articles, article, queryAll, isEdit, fetchArticles }
  }, {
    persist: {
      storage: sessionStorage,
    }
})
