import instance from '@/api/request'

export  function queryUserById(userId: string) {
  return instance({
    url: "/user/" + userId,
    method: "get",
  })
}

