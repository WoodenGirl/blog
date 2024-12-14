export interface Article {
  articleId?: string
  articleTitle: string
  articleCover: string
  userId: string
  categoryId: string
  articleTags: string
  articleContent: string
  createdTime?: string
  updateTime?: string
  articleStatus: number
}

export interface ArticleBrief {
  articleId: string,
  articleTitle: string,
  articleTags: string,
  createdTime: string,
  updateTime: string,
  articleCover: string,

  articleStatus: number,

  nickname: string,

  categoryName: string,
}

export interface ArticleDetail {
  articleId: string,
  articleTitle: string,
  articleTags: string,
  createdTime: string,
  updateTime: string,
  articleCover: string,

  articleContent: string,

  userId: string,
  nickname: string,

  categoryId: string
  categoryName: string,
}



