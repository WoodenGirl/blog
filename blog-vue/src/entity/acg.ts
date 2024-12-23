import type { Category } from '@/entity/category'

export interface Acg {
  acgId?: string,   // id
  acgName: string, // 名称
  acgAuthor: string, // 作者
  acgActor: string, // 主演
  acgYear: string,  // 年份
  acgRegion: string,  // 地区
  acgIntro: string, // 简介
  acgCover: string, // 封面
  acgAddition: string, // 补充信息
  acgState: number, // 状态 '0': 连载中，'1': 已完结
  acgChapter: number, // 章节
  acgTags: string[],  // 标签 大女主等

  categoryId: number, // 类型
}
export interface AcgBrief {
  acgId: string,   // id
  acgName: string, // 名称
  acgCover: string, // 封面
  acgState: number, // 状态 '0': 连载中，'1': 已完结
  acgChapter: number, // 章节
}

export interface AcgDetail {
  acg: Acg,
  categoryName: string, // 类型
}

// 风格
const novelGenre_M = [ '军事历史', '玄幻奇幻', '修真仙侠', '武侠武术' , '网游世界', '科幻未来', '都市现实', '悬疑探险', '体育竞技', '同人']
const novelGenre_F = ['星际', '末世', '现言', '古言', '西幻', '无限流', '快穿', '双男主', '同人', '亲情向']
const novelGenre_P = ['畅销', '悬疑', '历史', '武侠', '科幻', '奇幻', '名著', '英文']
const comicGenre = ['运动', '音乐', '热血', '搞笑', '青春', '励志', '异世界', '治愈', '悬疑', '乙女', '日常', '奇幻']
const gameGenre = ['多人在线', '策略博弈', '角色扮演', '动作冒险', '飞行射击', '卡牌桌游', '模拟经营', '休闲益智']
const filmGenre = ['古装', '现代', '武侠', '科幻', '奇幻', '权谋', '悬疑', '警匪', '家庭', '战争', '喜剧', '犯罪', '正剧']
const varietyGenre = ['真人秀', '访谈', '娱乐', '人文', '科普']


// 地区
const acgRegions = ['大陆', '港台', '韩国', '日本', '美国', '英国', '其它']
// 年份
const acgYears = ['2024', '2023', '2022', '2020', '2019', '2018', '更早']
// 状态
const acgStates = [
  {label: '已完结', value: '1'},
  {label: '连载中', value: '0'}
]

export { acgRegions, acgYears, acgStates}



