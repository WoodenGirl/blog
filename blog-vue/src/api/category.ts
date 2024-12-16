import instance from '@/api/request'
import type { Category } from '@/entity/category'

// 页面为目录，根据页面id查询目录, 返回数据类型CategoryTree
export function queryCategory(categoryParent: number) {
  return instance({
    url: "/category/" + categoryParent,
    method: "get",
  })
}
// 添加目录
export function addCategory(category: Category) {
  return instance({
    url: "/category",
    method: "post",
    data: category,
  })
}
// 修改目录 参数category只能修改两项{categoryName,isInterrupt}
export function updateCategory(category: Category) {
  return instance({
    url: "/category",
    method: "put",
    data: category,
  })
}
// 删除目录
// 删除目录下的所有子目录，以及目录下的所有内容（考虑重新建表，caetgoryId，linkedId）
export function deleteCategory(categoryId: number) {
  return instance({
    url: "/category/categoryId/" + categoryId,
    method: "delete",
  })
}






