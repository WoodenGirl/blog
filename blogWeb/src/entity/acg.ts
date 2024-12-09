import { ref } from 'vue'

interface Acg {
  acgId: string,   // id
  acgName: string, // 名称
  acgAuthor: string, // 作者
  acgActor: string, // 演员
  acgYear: string,  // 年份
  acgIntro: string, // 简介
  acgCover: string, // 封面
  acgState: number, // 状态 '0': 连载中，'1': 已完结
  acgChapter: number, // 章节
  acgAddition: string,  // 补充

  acgType: string,  // 类型 女频，男频，出版
  acgGenre: string, // 风格 古代，现代，修仙等
  acgTags: string,  // 标签 大女主等
}

const novelTypes = ["男频", "女频", "出版"]
const gameTypes = ["手游", "端游"]
const acgTypes = (categoryName: string):string[] => {
  switch (categoryName) {
    case '小说': return novelTypes
    case '游戏': return gameTypes
    default: return []
  }
}

const novelGenre_F = [ "修真", "修仙", "玄幻", "武侠"]
const novelGenre_M = ["星际", "末世", "现言", "古言", "西幻", "无限流", "快穿", "双男主", "同人", "亲情向"]
const novelGenre_P = ["畅销", "悬疑", "历史", "武侠", "科幻", "奇幻", "名著", "英文"]

const comicGenre = ["运动", "音乐", "热血", "搞笑", "青春", "励志", "异世界", "治愈", "悬疑", "乙女", "日常", "奇幻"]

const gameGenre = ["多人在线", "策略博弈", "角色扮演", "动作冒险", "飞行射击", "卡牌桌游", "模拟经营", "休闲益智"]

const filmGenre = ["古装", "现代", "武侠", "科幻", "奇幻", "权谋", "悬疑", "警匪", "家庭", "战争", "喜剧", "犯罪", "正剧"]
const varietyGenre = ["真人秀", "访谈", "娱乐", "人文", "科普", "其它"]

const acgGenre = (categoryName: string):string[] => {
  switch (categoryName) {
    case '小说': return novelTypes
    case '游戏': return gameTypes
    default: return []
  }
}

export { acgTypes }
