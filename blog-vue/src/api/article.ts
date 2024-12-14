import instance from '@/api/request'
import type { Article } from '@/entity/article'

// 查询简略文章，返回数据类型ArticleBreif以及totalCount
// 按修改时间排序
export function queryBriefArticle(categoryId: number, currentPage: number, pageSize: number) {
  return instance({
    url: "/article/categoryId/",
    method: "get",
    params: {
      categoryId,
      currentPage,
      pageSize
    }
  })
}
// 查询用户的所有文章，返回数据类型ArticleBreif以及totalCount
export function queryArticleByUser(userId: number, currentPage: number, pageSize: number) {
  return instance({
    url: "/article/userId/",
    method: "get",
    params: {
      userId,
      currentPage,
      pageSize
    }
  })
}
// 根据文章id查询详细文章，返回数据类型ArticleDetail
export function queryDetailArticle(articleId: string) {
  return instance({
    url: "/article/articleId/" + articleId,
    method: "get",
  })
}
// 添加文章
// formData.append("articleCover", fileList.value[0].raw)
// formData.append('articleJson', JSON.stringify(articleForm))
export function addArticle(formData: FormData) {
  return instance({
    url: "/article",
    method: "post",
    headers: {'Content-Type': 'multipart/form-data'},
    data: formData
  })
}
// 更新文章
// if (file.status != "success") { // 若图片不在服务器上，上传
//         formData.append("articleCover", fileList.value[0].raw)
//       } else {
//         formData.append("articleCover", null)
//       }
// 若articleCover为null,则无需重新上传图片，否则还需删除原来的，重新上传新的
export function updateArticle(formData: FormData) {
  return instance({
    url: "/article",
    method: "post",
    headers: {'Content-Type': 'multipart/form-data'},
    data: formData,
  })
}
// 删除文章
// 需要删除所有评论
export function deleteArticle(articleId: string) {
  return instance({
    url: "/article/" + articleId,
    method: "delete",
  })
}






