export interface List {   // 添加
  listId?: string      // 书单id
  listTitle: string   // 书单标题
  categoryId: number
  listStatus: number  // 书单发布状态0未发布，1发布

  userId: string           // 用户id
  userRemarks: UserRemark[] // 图书评论数组
}

export interface UserRemark {
  acgId: string,       // acgId
  userRate: number,     // 用户评分
  userReview: string,   // 用户评语
}

export interface ListBrief { // 展示
  listId: string,
  listTitle: string,
  listStatus: number,

  userId: string
  nickname: string,
  avatar: string,

  acgCovers: string[], // acg封面列表
}

export interface ListDetail {
  listId: string
  listTitle: string

  userId: string
  nickname: string
  avatar: string

  userRemarkDetails: userRemarkDetail[] // 图书评论数组
}

export interface userRemarkDetail {
  acgId: string,
  userRate: number,
  userReview: string,

  acgName: string,
  acgAuthor: string,
  acgIntro: string,
  acgCover: string,
}
