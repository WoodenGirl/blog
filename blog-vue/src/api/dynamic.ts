import instance from '@/api/request'
import type { Dynamic } from '@/entity/dynamic'

// 查询目录下的所有动态， 返回类型DynamicDetail
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
// 添加动态
// 待修改formdata
export function addDynamic(dynamic: Dynamic) {
  return instance({
    url: "/dynamic",
    method: "post",
    data: dynamic
  })
}
// 删除动态
// 删除图片，删除评论
export function deleteDynamic(dynamicId: string) {
  return instance({
    url: "/dynamic/" + dynamicId,
    method: "delete",
  })
}






