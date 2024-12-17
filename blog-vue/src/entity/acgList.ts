export interface AcgList {   // 添加
  listId: string      // 书单id
  listTitle: string   // 书单标题
  listStatus: number  // 书单发布状态0未发布，1发布

  userId: string           // 用户id
  acgRemarks: AcgRemark[] // 图书评论数组
}

export interface AcgRemark {
  acgId: string,       // acgId
  acgRate: number,     // 用户评分
  acgRemark: string,   // 用户评语
}

export interface AcgListDetail { // 展示
  listId: string,
  listTitle: string,
  listStatus: number,

  userId: string
  nickname: string,
  avatar: string,

  acgDetailRemarks: AcgDetailRemark[],
}

export interface AcgDetailRemark {
  acgId: string,
  acgName: string,
  acgAuthor: string,
  acgCover: string,
  acgRate: number,
  acgRemark: string,
}
