<template>
  <div class="margin20">评论区</div>
  <!-- 发表评论 -->
  <comment-input @submit-comment="submitComment" class="margin10"></comment-input>

  <!-- 展示所有评论 -->
  <div v-for="commentRoot in commentRoots" :key="commentRoot.commentId" style="width: 100%;">
    <!-- 展示根评论 -->
    <comment-single :comment="commentRoot" class="margin20" @rerender="queryComments"></comment-single>
    <!-- 展示根评论的所有子评论 -->
    <div v-for="comment in commentRoot.children" :key="comment.commentId" class="secondaryComments">
      <!-- 展示子评论 -->
      <comment-single :comment="comment" class="margin20" @rerender="queryComments"></comment-single>
    </div>
  </div>

</template>

<script setup lang="ts">

import { ref } from 'vue'
import CommentInput from '@/components/comment/Comment-Input.vue'
import { useUserStore } from '@/stores/user'
import { addComment } from '@/api/comment'
import { ElMessage } from 'element-plus'
import CommentSingle from '@/components/comment/Comment-Single.vue'
import { getNow } from '@/assets/ts/tool'
import { type Comment, fetchComments } from '@/entity/comment'

const props = defineProps(['linkedId'])
const linkedId = props.linkedId

// 发表根评论
const submitComment = (content: string) => {
  const commentData = ref({
    userId: useUserStore().user.userId,
    commentContent: content,
    linkedId: props.linkedId,
    createdTime: getNow(),
    commentParent: 0,
  })
  addComment(commentData.value).then((res) => {
    ElMessage.success("发表成功！")
    queryComments()
  })
}

// 展示所有评论
const commentRoots = ref<Comment[]>([])
const queryComments = () => {
  fetchComments(linkedId).then(res => {
    commentRoots.value = res
  })
}
queryComments()


</script>

<style scoped>
.secondaryComments {
  margin-left: 50px;
}
</style>
