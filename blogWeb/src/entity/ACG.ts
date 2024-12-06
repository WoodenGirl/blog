interface Novel {
  novelId: string,   // id
  novelName: string, // 名称
  novelAuthor: string, // 作者
  novelYear: string,  // 年份
  novelIntro: string, // 简介
  novelCover: string, // 封面
  novelState: number, // 状态 '0': 连载中，'1': 已完结
  novelChapter: number, // 章节

  novelType: string,  // 类型 女频，男频，出版
  novelGenre: string, // 风格 古代，现代，修仙等
  novelTags: string,  // 标签 大女主等
}

interface cartoon {
  cartoonId: string,   // id
  cartoonName: string, // 名称
  cartoonAuthor: string, // 作者
  cartoonYear: string,  // 年份
  cartoonIntro: string, // 简介
  cartoonCover: string, // 封面
  cartoonState: number, // 状态 '0': 连载中，'1': 已完结
  cartoonEpisode: number, // 集数

  cartoonGenre: string, // 风格 少年，热血
  cartoonTags: string,  // 标签
}


interface game {
  gameId: string,   // id
  gameName: string, // 名称
  gameAuthor: string, // 作者
  gameYear: string,  // 年份
  gameIntro: string, // 简介
  gameCover: string, // 封面

  gameType: string,  // 类型 手游，端游
  gameGenre: string, // 风格 射击，类银河城
  gameDifficulty: string,  // 难度
}

