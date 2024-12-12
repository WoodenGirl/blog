import axios from 'axios'
import { ElNotification } from 'element-plus'

const instance = axios.create({
  baseURL: '/api',
  timeout: 3000,
})

declare module 'axios' {
  export interface AxiosResponse {
    code?: number; // 假设你要添加的自定义属性
  }
}

// 请求拦截
instance.interceptors.request.use(config => {
  return config;
}, error => {
  return Promise.reject(error);
})

// 响应拦截
instance.interceptors.response.use(response => {
  if (response.data.statusCode === 200) {
    if (response.data.data.code === 200) {
      return response.data.data;
    } else {
      ElNotification.error(response.data.data.message)
    }
  }
}, error => {
  console.log(error.response)
})

export default instance
