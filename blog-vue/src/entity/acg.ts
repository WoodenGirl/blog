export interface Acg {
  acgId: string,   // id
  acgName: string, // 名称
  acgAuthor: string, // 作者
  acgActor: string, // 演员
  acgYear: string,  // 年份
  acgRegion: string,  // 地区
  acgIntro: string, // 简介
  acgCover: string, // 封面
  acgState: number, // 状态 '0': 连载中，'1': 已完结
  acgChapter: number, // 章节
  acgAddition: string,  // 补充
  categoryId: string, // 类型
  acgTags: string,  // 标签 大女主等

}
// 类型
const novelTypes = ['男频', '女频', '出版']
const gameTypes = ['手游', '端游']
const acgTypes = (categoryName: string):any => {
  switch (categoryName) {
    case '小说': return novelTypes
    case '游戏': return gameTypes
    default: return null
  }
}
// 风格
const novelGenre_M = [ '军事历史', '玄幻奇幻', '修真仙侠', '武侠武术' , '网游世界', '科幻未来', '都市现实', '悬疑探险', '体育竞技', '同人']
const novelGenre_F = ['星际', '末世', '现言', '古言', '西幻', '无限流', '快穿', '双男主', '同人', '亲情向']
const novelGenre_P = ['畅销', '悬疑', '历史', '武侠', '科幻', '奇幻', '名著', '英文']
const comicGenre = ['运动', '音乐', '热血', '搞笑', '青春', '励志', '异世界', '治愈', '悬疑', '乙女', '日常', '奇幻']
const gameGenre = ['多人在线', '策略博弈', '角色扮演', '动作冒险', '飞行射击', '卡牌桌游', '模拟经营', '休闲益智']
const filmGenre = ['古装', '现代', '武侠', '科幻', '奇幻', '权谋', '悬疑', '警匪', '家庭', '战争', '喜剧', '犯罪', '正剧']
const varietyGenre = ['真人秀', '访谈', '娱乐', '人文', '科普']

const acgGenre = (categoryName: string ):string[] => {
  switch (categoryName) {
    case '小说': return novelGenre_P
    case '游戏': return gameGenre
    case '电视剧':
    case '电影': return filmGenre
    case '动漫':
    case '漫画': return comicGenre
    default: return varietyGenre
  }
}
const novelGenre = (acgType: string):string[] => {
  switch (acgType) {
    case '男频': return novelGenre_M
    case '女频': return novelGenre_F
    default: return novelGenre_P
  }
}
const novelTags_M = ['校园']
const novelTags_F = ['修仙', '大女主', '电竞', '娱乐圈', '玄学', '校园']
const novelTags_P = ['文学']
const comicTags = ['棒球']
const gameTags = ['类银河城', '养老']
const filmTags = ['开心麻花']
const varietyTags = ['选秀']
const acgTags = (categoryName: string ):string[] => {
  switch (categoryName) {
    case '小说': return novelTags_P
    case '游戏': return gameTags
    case '电视剧':
    case '电影': return filmTags
    case '动漫':
    case '漫画': return comicTags
    default: return varietyTags
  }
}
const novelTags = (acgType: string):string[] => {
  switch (acgType) {
    case '男频': return novelTags_M
    case '女频': return novelTags_F
    default: return novelTags_P
  }
}
// 地区
const acgRegions = ['大陆', '港台', '韩国', '日本', '美国', '英国', '其它']

// 年份
const acgYears = ['2024', '2023', '2022', '2020', '2019', '2018', '更早']

// 状态
const acgStates = [
  {label: '已完结', value: '1'},
  {label: '连载中', value: '0'}
]

// 标签
const acgLabel = []

export { acgTypes, acgGenre, novelGenre, acgRegions, acgYears, acgStates, acgTags, novelTags }
