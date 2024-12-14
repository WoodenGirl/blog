<template>
  <div class="margin20">评论区</div>
  <!-- 发表评论 -->
  <comment-input @submit-comment="submitComment" class="margin10"></comment-input>

  <!-- 展示所有评论 -->
  <div v-for="commentRoot in commentRoots" :key="commentRoot.commentId" style="width: 100%;">
    <!-- 展示根评论 -->
    <comment-single :comment="commentRoot" class="margin20" @rerender="fetchComments"></comment-single>
    <!-- 展示根评论的所有子评论 -->
    <div v-for="comment in commentRoot.commentChildren" :key="comment.commentId" class="secondaryComments">
      <!-- 展示子评论 -->
      <comment-single :comment="comment" class="margin20" @rerender="fetchComments"></comment-single>
    </div>
  </div>

  <el-pagination
    v-model:current-page="currentPage"
    :page-size="pageSize"
    size="default"

    layout="total, prev, pager, next"
    :total="1000"
    @size-change="fetchComments"
    @current-change="fetchComments"
  />
</template>

<script setup lang="ts">

import { ref } from 'vue'
import CommentInput from '@/components/comment/Comment-Input.vue'
import { useUserStore } from '@/stores/user'
import { addComment, queryComment } from '@/api/comment'
import { ElMessage } from 'element-plus'
import CommentSingle from '@/components/comment/Comment-Single.vue'
import { type Comment, type CommentDetail } from '@/entity/comment'

const props = defineProps(['linkedId'])
const linkedId = props.linkedId

// 发表根评论
const submitComment = (content: string) => {
  const commentData = ref<Comment>({
    userId: useUserStore().user.userId,
    commentContent: content,
    linkedId: props.linkedId,
    commentParent: 0,
  })
  addComment(commentData.value).then(() => {
    ElMessage.success("发表成功！")
    fetchComments()
  })
}

// 分页
const currentPage = ref(1)
const pageSize = ref(10)

// 展示所有评论
const commentRoots = ref<CommentDetail[]>([])
const fetchComments = async () => {
  commentRoots.value = await queryComment(linkedId, currentPage.value, pageSize.value).then(res => res.data)
}
fetchComments()

</script>

<style scoped>
.secondaryComments {
  margin-left: 50px;
}
</style>
