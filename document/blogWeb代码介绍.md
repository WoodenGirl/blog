## 文件夹

![1733883465735](image/blogWeb代码介绍/1733883465735.png)

## 源代码

### api 接口后端

设置base url '/api'， 地址为http://localhost:port/api

![1733883638381](image/blogWeb代码介绍/1733883638381.png)

拼接地址，为http://localhost:port/api/acticle

![1733883699729](image/blogWeb代码介绍/1733883699729.png)

设置代理，将含'/api'的地址转发为target, 地址为‘target/api/article’，解决跨域问题

'/images'同理，且用rewrite 函数去除‘/images’字符串

![1733883592978](image/blogWeb代码介绍/1733883592978.png)

### router 路由

App.vue主页面，定义的路由页面会显示在router-view中

在此页面中写好导航栏，底部

![1733884153256](image/blogWeb代码介绍/1733884153256.png)

路由文件， path为地址，name为名称，component为组件页面

'/template'的路由页面会直接显示在App.vue中

'/template'下的‘/’页面会显示在'/template'的路由页面中

![1733884281561](image/blogWeb代码介绍/1733884281561.png)

![1733884468185](image/blogWeb代码介绍/1733884468185.png)

### views 页面

普通页面，在其中引入组件

### components 组件页面

实现一些小的组件，如导航栏，在views的页面中直接引入

![1733884662810](image/blogWeb代码介绍/1733884662810.png)

##### article组件

![1733884803574](image/blogWeb代码介绍/1733884803574.png)

###### article-brief 文章简略信息

![1733884857847](image/blogWeb代码介绍/1733884857847.png)

###### article-create 文章创建

![1733884905151](image/blogWeb代码介绍/1733884905151.png)

###### article-list 文章列表

![1733884981776](image/blogWeb代码介绍/1733884981776.png)

![1733884947900](image/blogWeb代码介绍/1733884947900.png)

###### article-preview 文章详情 集成文章-以及评论

![1733885064664](image/blogWeb代码介绍/1733885064664.png)

![1733885179348](image/blogWeb代码介绍/1733885179348.png)

###### wang-editor 编辑器

### stores 存储

对数据进行状态管理，监听数据变化（方便组件间传递数据）

如 对当前目录，目录是否能够编辑，当前目录的父目录三个数据进行保存，并且存储到session中

![1733885494831](image/blogWeb代码介绍/1733885494831.png)

在目录组件中，点击目录即修改store中的值

![1733885816509](image/blogWeb代码介绍/1733885816509.png)

在文章组件中，获取存储的值并进行监听，从而修改文章列表

![1733885713519](image/blogWeb代码介绍/1733885713519.png)

### entity 定义接口

![1733885921725](image/blogWeb代码介绍/1733885921725.png)

### asserts 静态资源

![1733886003592](image/blogWeb代码介绍/1733886003592.png)
