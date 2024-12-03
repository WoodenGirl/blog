export interface Article {
  articleId: string,
  articleTitle: string,
  articleTags: string,
  createdTime: string,
  updateTime: string,
  articleContent: string,
  articleCover: string,

  userId: string,
  nickname: string,

  categoryId: string,
  categoryDetail: string,
}
export interface ArticleForm {
  articleId: string
  articleTitle: string
  articleCover: string
  userId: string
  categoryId: string
  articleTags: string
  articleContent: string
  createdTime: string
  updateTime: string
}
