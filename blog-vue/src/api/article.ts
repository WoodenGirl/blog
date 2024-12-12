import instance from '@/api/request'

export function queryArticle() {
  return instance({
    url: "/article",
    method: "get",
  })
}

export function queryArticleByCategoryId(categoryId: number) {
  return instance({
    url: "/article/categoryId/" + categoryId,
    method: "get",
  })
}

export function addArticle(article: object) {
  return instance({
    url: "/article",
    method: "post",
    data: article
  })
}

export function updateArticle(article: object) {
  return instance({
    url: "/article",
    method: "put",
    data: article,
  })
}

export function deleteArticle(articleId: string) {
  return instance({
    url: "/article/" + articleId,
    method: "delete",
  })
}






