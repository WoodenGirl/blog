<template>
  <div class="comment" style="width: 100%">
    <el-row>
      <!--  头像  -->
      <el-col :span="1">
        <el-avatar size="default" fit="cover" :src="comment.avatar"/>
      </el-col>
      <!--  昵称 内容  -->
      <el-col :span="21" style="margin-left: 1rem">
        <p class="nickname">{{ comment.nickname }}</p>
        <!--   一、二级     -->
        <p class="content" v-if="comment.commentReply == null">{{ comment.commentContent }}</p>
        <!--    三级    -->
        <p v-else>回复 <span style="color: #999999">{{ comment.replyName }}</span>: {{ comment.commentContent }}</p>
      </el-col>
      <!--  喜欢图标  -->
      <el-col :span="1" >
        <a class="icon element-icons" :class="{'el-icon-xihuan2': isLike, 'el-icon-xihuan1': !isLike}" @click="trigger"></a>
      </el-col>
    </el-row>
    <!--  创建时间 回复  -->
    <el-row class="createdTime">
      {{ formatDate(comment.createdTime) }}
      <a class="A" @click="isDisplay = true">回复</a>
      <a class="A cancelA" :class="{'show': isDisplay}" @click="isDisplay = false">取消</a>
      <a class="A" v-if="comment.userId == useUserStore().user.userId" @click="remove">删除</a>
    </el-row>

    <el-row class="comment-input margin20" :class="{'show': isDisplay}">
      <comment-input @submitComment="submitComment"></comment-input>
    </el-row>
  </div>
</template>

<script setup lang="ts">

import { ref } from 'vue'
import { formatDate } from '@/tool/time'
import CommentInput from '@/components/comment/Comment-Input.vue'
import { useUserStore } from '@/stores/user'
import { addComment, deleteComment } from '@/api/comment'
import { ElMessage } from 'element-plus'
import type { Comment } from '@/entity/comment'

// 接收一个评论
const props = defineProps(['comment'])
const emits = defineEmits(['rerender'])

// 喜欢
const isLike = ref(false)
const trigger = () => {
  isLike.value = !isLike.value
}

// 回复输入框与取消按钮 的展示与否
const isDisplay = ref(false)

// 回复评论
const submitComment = (content: string) => {
  // 评论
  const commentData = ref<Comment>({
    userId: useUserStore().user.userId,
    commentContent: content,
    linkedId: props.comment.linkedId,
    // 根据评论级别赋值
    commentParent: 0,
    commentReply: null
  })
  // 二级评论
  if (props.comment.commentParent == 0) {
    commentData.value.commentParent = props.comment.commentId
  }
  else { // 三级评论
    commentData.value.commentParent = props.comment.commentParent
    commentData.value.commentReply = props.comment.userId
  }
  addComment(commentData.value).then((res) => {
    ElMessage.success("发表成功！")
    // 重新渲染数据
    emits('rerender')
    // 不展示回复输入框
    isDisplay.value = false
  })
}
// 删除评论
const remove = () => {
  deleteComment(props.comment.commentId).then((res) => {
    ElMessage.success("删除成功！")
    // 重新渲染数据
    emits('rerender')
  })
}
</script>

<style scoped>
.icon {
  cursor: pointer;
}
.nickname {
  margin-bottom: 0.3rem;
}
.content{
  word-break: break-all;
  padding: 5px;
}
.createdTime {
  margin-top: 0.5rem;
}
.A {
  margin-left: 8px;
  color: #4290f7;
  cursor: pointer;
}
.comment-input, .cancelA{
  display: none;
}
.show{
  display: block;
}


</style>
