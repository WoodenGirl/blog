export interface Article {
  articleId: string,
  nickname: string,
  categoryDetail: string,
  articleTags: string,
  createdTime: string,
  updateTime: string,
  articleContent: string
  articleCover: string
}

export interface User {
  userId: string,
  nickname: string,
  password: string,
  email: string,

}
