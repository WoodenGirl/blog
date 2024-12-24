import instance from '@/api/request'
import type { Comment } from '@/entity/comment'

// 查询所有评论，返回类型 CommentDetail
export function queryComment(linkId: string, currentPage: number, pageSize: number) {
  return instance({
    url: "/comment",
    method: "get",
    params: {
      linkId,
      currentPage,
      pageSize
    }
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






