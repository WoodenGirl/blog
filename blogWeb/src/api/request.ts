import axios from 'axios'

const instance = axios.create({
  baseURL: '/api',
  timeout: 3000,
})

// 请求拦截
instance.interceptors.request.use(config => {
  return config;
}, error => {
  return Promise.reject(error);
})

// 响应拦截
instance.interceptors.response.use(response => {
  /*if (response.data.statusCode === 200) {
    return response
  }*/
  return response.data.data;
}, error => {
  console.log(error.response)
})

export default instance
