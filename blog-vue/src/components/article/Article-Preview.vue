<template>
  <div  class="container">
    <!--  标头  -->
    <div class="articleHead">
      <!--  背景  -->
      <el-image :src="'/images/' + article.articleCover" fit="fill" />
      <div class="headText">
        <!--  标题  -->
        <el-row>
          <h1 class="center">{{ article.articleTitle }}</h1>
        </el-row>
        <!--  作者 时间  -->
        <el-row>
          <div class="flex gap-2 center">
            <span>{{ article.nickname }}</span>
            <span><el-icon size="20"><clock/></el-icon>创建于{{ formatDate(article.createTime) }}</span>
            <span><el-icon size="20"><clock/></el-icon>最近更新于{{ formatDate(article.updateTime) }}</span>
          </div>
        </el-row>
        <!--  分类 标签  -->
        <el-row>
          <div class="flex gap-2 center">
            <span>文章分类: {{ article.categoryName }}</span>
            <span>标签:
          <el-tag
            v-for="tag in article.articleTags"
            :key="tag"
            size="default"
            :disable-transitions="false"
          >
            {{ tag }}
          </el-tag>
        </span>
          </div>
        </el-row>
      </div>
    </div>

    <el-divider />

    <!--  正文  -->
    <div class="w-e-text-container article-content-container">
      <div v-html="article.articleContent" data-slate-editor></div>
    </div>

    <el-divider />
    <!--  评论  -->
    <comment-all :link-id="article.articleId"></comment-all>
  </div>

</template>

<script setup lang="ts">
import { formatDate } from '@/tool/time'
import { Clock } from '@element-plus/icons-vue'
import { queryDetailArticle } from '@/api/article'
import CommentAll from '@/components/comment/Comment-All.vue'
import { useRoute } from 'vue-router'
import type { ArticleDetail } from '@/entity/article'
import { ref } from 'vue'

// 接收参数
const articleId = useRoute().params.articleId.toString()

// 获取article
const article = ref<ArticleDetail>({
  articleId: articleId,
  articleTitle: '',
  articleTags: [],
  createTime: '2024-12-19',
  updateTime: '2024-12-19',
  articleCover: '',

  articleContent: '',

  userId: '',
  nickname: '',

  categoryId: '',
  categoryName: '',
})
const fetchArticle = async () => {
  article.value = await queryDetailArticle(articleId).then(res => res.data)
}
fetchArticle()
</script>

<style scoped>
.articleHead {
  position: relative;
}
.el-image {
  opacity: 0.3;
  background-size: cover;
  width: 100%;
  height: 15rem;
}
.headText {
  position: absolute;
  top: 2rem;
  left: 20rem;
}
.edit {
  position: absolute;
  top: -500px;
  right: 20px;
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
.article-content-container {
  margin: 30px 0;
}
</style>
