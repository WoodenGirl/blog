import instance from '@/api/request'
import type { Dynamic } from '@/entity/dynamic'

// 查询目录下的所有动态， 返回数据类型DynamicDetail
export function queryDynamic(categoryId: number, currentPage: number, pageSize: number) {
  return instance({
    url: "/dynamic",
    method: "get",
    params: {
      categoryId,
      currentPage,
      pageSize
    }
  })
}
// 查询动态总数total
export function countDynamic(categoryId: number) {
  return instance({
    url: "/dynamic/count/" + categoryId,
    method: "get",
  })
}

// 查询用户所有动态
export function queryDynamicByUser(userId: string, currentPage: number, pageSize: number) {
  return instance({
    url: "/dynamic/user",
    method: "get",
    params: {
      userId,
      currentPage,
      pageSize
    }
  })
}
// 查询用户评论总数total
export function countCommentByUser(userId: string) {
  return instance({
    url: "/dynamic/userCount/" + userId,
    method: "get",
  })
}

// 添加动态
export function addDynamic(dynamic: Dynamic) {
  return instance({
    url: "/dynamic",
    method: "post",
    data: dynamic
  })
}
// 删除动态
export function deleteDynamic(dynamicId: string) {
  return instance({
    url: "/dynamic/" + dynamicId,
    method: "delete",
  })
}






