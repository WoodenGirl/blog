import instance from '@/api/request'

export function queryCategory() {
  return instance({
    url: "/category",
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

export function deleteCategory(category: object) {
  return instance({
    url: "/category",
    method: "delete",
    data: category,
  })
}






