import instance from '@/api/request'

export function queryComment(linkedId: string) {
  return instance({
    url: "/comment/" + linkedId,
    method: "get"
  })
}

export function addComment(comment: object) {
  return instance({
    url: "/comment",
    method: "post",
    data: comment
  })
}

export function deleteComment(commentId: string) {
  return instance({
    url: "/comment/" + commentId,
    method: "delete",
  })
}






