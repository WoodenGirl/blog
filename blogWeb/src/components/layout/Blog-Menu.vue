<template>
  <el-tree
    style="max-width: 600px"
    :data="dataSource"
    node-key="id"
    accordion
    :expand-on-click-node="false"
  >
    <template #default="{ node, data }">
        <span class="custom-tree-node" @click="toList(data)">
          <span>{{ node.label }}</span>
          <span v-show="isEdit">
            <a @click="append(data)" > <Plus class="edit-icon"></Plus> </a>
            <a @click="remove(data)"> <Delete class="edit-icon"></Delete> </a>
            <a @click="update(data)"> <Edit class="edit-icon"></Edit> </a>
          </span>
        </span>
    </template>
  </el-tree>
  <Plus @click="append(null)" class="add-icon" ></Plus>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { addCategory, deleteCategories, queryCategory, updateCategory } from '@/api/category'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Delete, Edit, Plus } from '@element-plus/icons-vue'
import { useArticlesStore } from '@/stores/article'
import router from '@/router'
import { useCategoryStore } from '@/stores/category'

interface Tree {
  id: number
  label: string
  children?: Tree[]
}
// 是否对目录进行编辑
const isEdit = ref(true)

// 获取tree数据
const dataSource = ref<Tree[]>([])
const fetchTreeData = async () => {
  try {
    dataSource.value = await queryCategory().then(res => res.data);
  } catch (error) {
    console.error('Error fetching tree data:', error);
  }
};
fetchTreeData()

// 跳转list页面，渲染文章列表
const articlesStore = useArticlesStore()
const toList = (data: Tree) => {
  // 查看文章
    /*articlesStore.clickCategory(data.id)
    articlesStore.queryAll = false
    router.push('/')*/
  // 查看动态
    useCategoryStore().categoryId = data.id.toString()
    router.push('/dynamic')
}

// 添加tree节点
const append = (data: any) => {
  ElMessageBox.prompt('Please input category name', 'Add category', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
  })
    .then(({ value }) => {
      const newChild = {categoryName: value, categoryParent: data ? data.id : 0}
        addCategory(newChild).then((res) => {
          if (res.code === 200) {
            fetchTreeData()
          }
      });
    })
    .catch(() => {
      ElMessage.info('Input canceled')
    })
}
// 修改tree节点
const update = (data: Tree) => {
  ElMessageBox.prompt('Please input category name', 'Update category', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    inputValue: data.label
  })
    .then(({ value }) => {
      const newChild = {categoryId: data.id, categoryName: value}
      updateCategory(newChild).then((res) => {
        if (res.code === 200) {
          fetchTreeData()
        }
      });
    })
    .catch(() => {
      ElMessage.info('Input canceled')
    })
}

// 删除tree节点
const remove = (data: Tree) => {
  ElMessageBox.confirm(
    'Are you sure to delete it?',
    'Warning',
    {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning',
    }
  )
    .then(() => {
      const categoryIds = [data.id]
      // 若存在子目录，删除子目录
      if (data.children) {
        for (const tree of data.children) {
          categoryIds.push(tree.id)
        }
      }
      deleteCategories(categoryIds).then((res) => {
        if (res.code === 200) {
          fetchTreeData()
          ElMessage.success('Delete completed')
        }
      })
    })
    .catch(() => {
      ElMessage.info('Delete canceled')
    })
}

</script>

<style scoped>
.el-tree{
  background-color: #ffffff;
  --el-tree-node-content-height: 50px;
  --el-tree-node-hover-bg-color: #ECF5FF
}
.custom-tree-node{
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 18px;
  padding-right: 8px;
}

.edit-icon{
  width: 18px;
  height: 18px;
  margin: 0 5px;
}

.add-icon {
  width: 100%;
  height: 45px;
  color: white;
  padding: 10px;
}
.add-icon:hover {
  color: #c8daed;
  background-color: #F5F7FA;
  cursor: pointer;
}
</style>
