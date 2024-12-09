<template>
  <el-timeline style="max-width: 70%">
    <el-timeline-item
      :timestamp="formatDate(article.updateTime)"
      placement="top"
      v-for="article in articles"
      :key="article.articleId"
    >
      <article-brief :article="article" @click="toPreview(article)"></article-brief>
    </el-timeline-item>
  </el-timeline>
</template>

<script setup lang="ts">
import ArticleBrief from '@/components/article/Article-Brief.vue'
import { formatDate } from '@/assets/ts/tool'
import router from '@/router'
import { useArticlesStore } from '@/stores/article'
import { storeToRefs } from 'pinia'
import type { Article } from '@/entity/article'
import { useCategoryStore } from '@/stores/category'
import { watch } from 'vue'

const {category} = storeToRefs(useCategoryStore())

watch(() => category.value, (newValue) => {
  articlesStore.fetchArticles(newValue!.id)
})

// 获取数据
const articlesStore = useArticlesStore()
const {articles, article} = storeToRefs(articlesStore)
articlesStore.fetchArticles(category.value!.id)




// 点击查看文章
const toPreview = (data: Article) => {
  article.value = data
  router.push('/article');
}
</script>

<style scoped>

</style>
