<template>
  <!-- 查询条件 -->
  <el-form
    :ref="acgQueryRef"
    :model="acgQuery"
    label-position="left"
    size="default"
    :inline="true"
    class="acgQuery"
  >
    <!--   作品类型     -->
    <el-form-item prop="category">
      <el-cascader
        v-model="acgQuery.categoryId"
        :options="categories"
        :props="props"
        placeholder="作品类型"
        @change="handleCascadeChange"
      />
    </el-form-item>
    <!--   标签     -->
    <el-form-item prop="tag">
      <el-input v-model="acgQuery.acgTag" placeholder="作品标签" />
    </el-form-item>
    <!--   名称     -->
    <el-form-item prop="name">
      <el-input v-model="acgQuery.acgName" placeholder="作品名称" />
    </el-form-item>
    <!--   作者     -->
    <el-form-item prop="author">
      <el-input v-model="acgQuery.acgAuthor" placeholder="作品作者" />
    </el-form-item>
    <!--   作品年份     -->
    <el-form-item prop="year">
      <el-date-picker v-model="acgQuery.acgYear" type="year" placeholder="作品年份">
      </el-date-picker>
    </el-form-item>
    <!--   作品地区     -->
    <el-form-item prop="region">
      <el-select v-model="acgQuery.acgRegion" placeholder="作品地区">
        <el-option v-for="item in acgRegions" :key="item" :label="item" :value="item" />
      </el-select>
    </el-form-item>
    <!--   作品状态    -->
    <el-form-item prop="region">
      <el-select v-model="acgQuery.acgState" placeholder="作品状态">
        <el-option v-for="item in acgStates" :key="item.value" :label="item.label" :value="item.value" />
      </el-select>
    </el-form-item>
    <!--  查询清空  -->
    <el-form-item>
      <el-button type="primary" @click="submitForm(acgQueryRef)">查询</el-button>
      <el-button @click="reset(acgQueryRef)">清空</el-button>
    </el-form-item>
  </el-form>
  <!-- 添加acg -->
  <a class="addAcg" @click="displayAddAcg = true">找不到想要的？点击添加</a>
  <el-dialog v-model="displayAddAcg" title="添加Acg" width="800">
    <acg-create></acg-create>
  </el-dialog>
  <!-- 展示acg -->
  <div class="acgContainer" >
    <acg-card class="acgCard" :acg="acg" v-for="acg in acgList" @click="router.push({path:'/acgPreview/' + acg.acgId})"></acg-card>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useCategoryStore } from '@/stores/category'
import AcgCard from '@/components/acg/source/Acg-Card.vue'
import { type AcgBrief, acgRegions, acgStates, acgYears } from '@/entity/acg'
import test from '@/assets/images/test.jpeg'
import router from '@/router'
import type { ElInput, FormInstance } from 'element-plus'
import { queryCategory } from '@/api/category'
import AcgCreate from '@/components/acg/source/Acg-Create.vue'
import { putObject } from '@/tool/obs'

const {category} = storeToRefs(useCategoryStore())
watch(() => category.value, () => {

})
// 查询
// 类别
const categories = ref()
const fetchTreeData = async () => {
  try {
    categories.value = await queryCategory(useCategoryStore().category.categoryId).then(res => res.data);
  } catch (error) {
    console.error('Error fetching tree data:', error);
  }
};
fetchTreeData()
const props = {
  expandTrigger: 'hover' as const,
  checkStrictly: true,
  value: 'categoryId',
  label: 'categoryName',
  children: 'categoryChildren',
}
const handleCascadeChange = (value: string) => {
  acgQuery.categoryId = value[value.length - 1]
}
// 查询表单
const acgQueryRef = ref<FormInstance>()
const acgQuery = reactive({
  acgName: '',
  acgAuthor: '',
  categoryId: '',
  acgRegion: '',
  acgState: '',
  acgYear: '',
  acgTag: '',
})
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
    } else {
      console.log('error submit!', fields)
    }
  })
}
const reset = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
// 添加
const displayAddAcg = ref(false)
// 展示
const acgList = ref<AcgBrief[]>([
  {
  acgId: '123',   // id
  acgName: '夏目友人帐', // 名称
  acgCover: test, // 封面
  acgState: 0, // 状态 '0': 连载中，'1': 已完结
  acgChapter: 12, // 章节
},
  {
    acgId: '123',   // id
    acgName: '夏目友人帐', // 名称
    acgCover: test, // 封面
    acgState: 0, // 状态 '0': 连载中，'1': 已完结
    acgChapter: 12, // 章节
  },
  {
    acgId: '123',   // id
    acgName: '夏目友人帐', // 名称
    acgCover: test, // 封面
    acgState: 0, // 状态 '0': 连载中，'1': 已完结
    acgChapter: 12, // 章节
  },
  {
    acgId: '123',   // id
    acgName: '夏目友人帐', // 名称
    acgCover: test, // 封面
    acgState: 0, // 状态 '0': 连载中，'1': 已完结
    acgChapter: 12, // 章节
  },
  {
    acgId: '123',   // id
    acgName: '夏目友人帐', // 名称
    acgCover: test, // 封面
    acgState: 0, // 状态 '0': 连载中，'1': 已完结
    acgChapter: 12, // 章节
  },
  {
    acgId: '123',   // id
    acgName: '夏目友人帐', // 名称
    acgCover: test, // 封面
    acgState: 0, // 状态 '0': 连载中，'1': 已完结
    acgChapter: 12, // 章节
  },
  {
    acgId: '123',   // id
    acgName: '夏目友人帐', // 名称
    acgCover: test, // 封面
    acgState: 0, // 状态 '0': 连载中，'1': 已完结
    acgChapter: 12, // 章节
  }])

</script>

<style scoped>
.el-form--inline {
  .el-form-item {
    .el-input, .el-cascader, .el-select, .el-autocomplete {
      width: 240px;
    }
  }
}
.addAcg {
  display: inline-block;
  margin-bottom: 1rem;
  color: #4290f7;
  text-decoration: underline;
  cursor: pointer;
}
</style>
