<template>
  <!-- 时间线展示文章卡片 -->
  <el-timeline style="max-width: 70%">
    <el-timeline-item
      :timestamp="formatDate(article.updateTime)"
      placement="top"
      v-for="article in articles"
      :key="article.articleId"
    >
      <article-card :article="article" @click="router.push('/articlePreview/' + article.articleId)"></article-card>
    </el-timeline-item>
  </el-timeline>

  <!-- 分页 -->
  <el-pagination
    v-model:current-page="currentPage"
    :page-size="pageSize"
    size="default"

    layout="total, prev, pager, next"
    :total="total"
    @size-change="fetchArticles"
    @current-change="fetchArticles"
  />

</template>

<script setup lang="ts">
import ArticleCard from '@/components/article/Article-Card.vue'
import { formatDate } from '@/tool/time'
import router from '@/router'
import { storeToRefs } from 'pinia'
import type { ArticleBrief } from '@/entity/article'
import { useCategoryStore } from '@/stores/category'
import { ref, watch } from 'vue'
import { queryBriefArticle } from '@/api/article'

const {category} = storeToRefs(useCategoryStore())

watch(() => category.value, () => {
  fetchArticles()
})

// 获取数据
const articles = ref<ArticleBrief[]>([])
// 分页
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)

const fetchArticles = async () => {
  await queryBriefArticle(category.value.categoryId, currentPage.value, pageSize.value).then((res) => {
    articles.value = res.data.list
    total.value = res.data.total
  })

}
fetchArticles()

</script>

<style scoped>

</style>
