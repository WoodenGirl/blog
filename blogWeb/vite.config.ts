import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      '/api': {
        target: 'https://localhost:44342/',
        //target: 'https://api.aprilsxz.fun/', // 目标服务器地址
        "secure": false, // 接受运行在https上，默认不接受
        "changeOrigin": true, // 如果设置为true,那么本地会虚拟一个服务器接收你的请求并代你发送该请求，这样就不会有跨域问题（只适合开发环境）
      },
      '/images': {
        target: 'http://obs.aprilsxz.fun/', // 图片资源服务器地址
        changeOrigin: true,
        "secure": false,
        rewrite: (p: string) => p.replace(/^\/images/, ""),
      }
    }
  }
})

export const module = {

};
