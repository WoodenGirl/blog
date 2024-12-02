import instance from '@/api/request'

export function queryDynamic(categoryId: string) {
  return instance({
    url: "/dynamic/" + categoryId,
    method: "get"
  })
}

export function addDynamic(dynamic: object) {
  return instance({
    url: "/dynamic",
    method: "post",
    data: dynamic
  })
}

export function deleteDynamic(dynamicId: string) {
  return instance({
    url: "/dynamic/" + dynamicId,
    method: "delete",
  })
}






