import instance from '@/api/request'

export function queryCategory(categoryParent: number) {
  return instance({
    url: "/category/" + categoryParent,
    method: "get",
  })
}

export function addCategory(category: object) {
  return instance({
    url: "/category",
    method: "post",
    data: category,
  })
}

export function updateCategory(category: object) {
  return instance({
    url: "/category",
    method: "put",
    data: category,
  })
}

export function deleteCategories(categoryIds: number[]) {
  return instance({
    url: "/category/categories",
    method: "post",
    data: categoryIds,
  })
}






