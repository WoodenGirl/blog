import instance from '@/api/request'

export  function loginByQQ(code: string) {
  return instance({
    url: "/qqLogin?code=" + code,
    method: "get",
  })
}

