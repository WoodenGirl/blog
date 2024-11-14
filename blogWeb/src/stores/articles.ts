import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { Article } from '@/assets/ts/interface'
import { queryArticle, queryArticleByCategoryId } from '@/api/article'

export const useArticlesStore = defineStore('articles', () => {
  const articles = ref<Article[]>([])
  const queryAll = ref<boolean>(true)
  const fetchArticles = async () => {
    // 获取数据
    articles.value = await queryArticle().then(res => res.data);
    // 对数据根据创建时间排序
    articles.value.sort((a, b) => new Date(b.createdTime).getTime() - new Date(a.createdTime).getTime())
  }
  const clickCategory = async (id: number) => {
    // 获取数据
    articles.value = await queryArticleByCategoryId(id).then(res => res.data);
    // 对数据根据创建时间排序
    articles.value.sort((a, b) => new Date(b.createdTime).getTime() - new Date(a.createdTime).getTime() )
  }
  return { articles, queryAll, fetchArticles, clickCategory }
})
