import instance from '@/api/request'
import type { Dynamic } from '@/entity/dynamic'

// 查询目录下的所有动态， 返回类型DynamicDetail
export function queryDynamic(categoryId: number, currentPage: number, pageSize: number) {
  return instance({
    url: "/dynamic/" + categoryId,
    method: "get",
    params: {
      categoryId,
      currentPage,
      pageSize
    }

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






