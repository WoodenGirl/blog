<template>
  <div class="container">
    <!--  背景  -->
    <el-image :url="article.articleCover" fit="fill" />

    <!--  标头  -->
    <div class="bgContent">
      <!--  编辑  -->
      <el-row :gutter="20">
        <div class="flex gap-2">
          <a @click="edit()"> <Edit class="edit-icon"></Edit> </a>
          <a @click="remove()"> <Delete class="edit-icon"></Delete> </a>
        </div>
      </el-row>

      <!--  标题  -->
      <el-row :gutter="20">
        <h1 class="center">{{ article.articleTitle }}</h1>
      </el-row>
      <!--  作者 时间  -->
      <el-row :gutter="20">
        <div class="flex gap-2 center">
            <span>{{ article.nickname }}</span>
            <span><el-icon size="20"><clock/></el-icon>创建于{{ formatDate(article.createdTime) }}</span>
            <span><el-icon size="20"><clock/></el-icon>最近更新于{{ formatDate(article.updateTime) }}</span>
          </div>
      </el-row>
      <!--  分类 标签  -->
      <el-row :gutter="20">
        <div class="flex gap-2 center">
          <span>文章分类: {{ article.categoryDetail }}</span>
          <span>标签:
          <el-tag
            v-for="tag in article.articleTags.split(',')"
            :key="tag"
            size="default"
            :disable-transitions="false"
          >
            {{ tag }}
          </el-tag></span>
        </div>
      </el-row>
    </div>

    <!--  正文  -->
    <div class="w-e-text-container">
      <div v-html="article.articleContent" data-slate-editor></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'
import { formatDate } from '@/assets/ts/date'
import { Clock, Delete, Edit } from '@element-plus/icons-vue'
import { useArticlesStore } from '@/stores/articles'
import router from '@/router'
import { deleteArticle } from '@/api/article'
import { ElMessage } from 'element-plus'

const route = useRoute()
const article = JSON.parse(route.params.articleJson.toString())

const articlesStore = useArticlesStore()
const edit = () => {
  articlesStore.isEdit = true
  articlesStore.article = article
  router.push("/create")
}
const remove = () => {
  deleteArticle(article).then(() => {
    ElMessage.success("删除成功！")
  })
}
</script>

<style scoped>
.container {
  max-width: 1200px;
  padding: 0 20px;
  position: relative;
}
.el-image {
  opacity: 0.5;
  background-size: cover;
  width: 100%;
  height: 500px;
}
.bgContent {
  width: 100%;
  position: absolute;
  top: 150px;
}
.content {
  position: absolute;
}

.el-row {
  margin-bottom: 20px;
}
.edit-icon {
  width: 20px;
}
span {
  margin: 0 0.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
