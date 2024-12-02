export interface Recreation {
  recreationId: string,
  recreationName: string, // 名称
  recreationRate: number, // 评分
  recreationIntro: string, // 简介
  recreationDirector: string, // 导演or作者
  recreationCover: string, // 封面
  recreationState: number, // 状态 '0': 连载中，'1': 已完结
  recreationTags: string,  // 标签

  recreationRemark?: string, // 其他信息补充
  recreationActor?: string, // 演员 or 声优
  recreationChapter?: number, // 章节


  categoryId: number,  // 分类id
  categoryDetail: string, // 分类名称
}
