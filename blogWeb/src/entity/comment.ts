import { ref } from 'vue'
import { queryComment } from '@/api/comment'

export interface Comment {
  commentId: number,
  commentParent: number,
  linkedId: string,
  commentReply: number,
  commentContent: string,
  createdTime: string,
  replyName: string,
  children: Comment[],

  userId: string,
  nickname: string,
  avatar: string,
}

export async function fetchComments(linkedId: string): Promise<Comment[]> {
  const commentRoots = ref<Comment[]>([])
  // 获取数据
  const comments = ref<Comment[]>([])
  comments.value = await queryComment(linkedId).then(res => res.data);
  console.log(comments.value)
  // 寻找根节点
  commentRoots.value = comments.value.filter(item => item.commentParent == 0)
  console.log(commentRoots.value)
  // 为其添加子节点
  for (const commentRoot of commentRoots.value) {
    commentRoot.children = []
    for (const comment of comments.value) {
      if (comment.commentParent == commentRoot.commentId) commentRoot.children.push(comment)
    }
  }
  return commentRoots.value
}
