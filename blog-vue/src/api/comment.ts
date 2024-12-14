import instance from '@/api/request'
import type { Comment } from '@/entity/comment'

// 查询所有评论，返回类型 CommentDetail
export function queryComment(linkedId: string, currentPage: number, pageSize: number) {
  return instance({
    url: "/comment/" + linkedId,
    method: "get",
    params: {
      linkedId,
      currentPage,
      pageSize
    }
  })
}
// 查询所有评论的总数
export function countComment(linkedId: string) {
  return instance({
    url: "/comment/count/" + linkedId,
    method: "get"
  })
}
// 添加评论
export function addComment(comment: Comment) {
  return instance({
    url: "/comment",
    method: "post",
    data: comment
  })
}
// 删除某条评论
// 删除该评论下的所有子评论
export function deleteComment(commentId: string) {
  return instance({
    url: "/comment/" + commentId,
    method: "delete",
  })
}






