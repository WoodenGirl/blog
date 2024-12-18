<template>
  <div class="container">
    <el-form
      ref="listFormRef"
      :model="listForm"
      :rules="listRules"
      label-width="auto"
      label-position="left"
      size="default"
      status-icon
    >
      <!-- list title -->
      <el-form-item>
        <el-input v-model="listForm.listTitle" placeholder="acg list 标题"/>
      </el-form-item>

      <!--   acgRemark   -->
      <el-card v-for="(userRemark,index) in userRemarks" class="margin10">
        <el-row>
          <el-col :span="4">
            <el-image :scr="acgs[index].acgCover">
              <template #error>
                <div class="image-slot" @click="openDialog(index)">
                  <el-icon ><Plus /></el-icon>
                </div>
              </template>
            </el-image>
          </el-col>
          <el-col :span="19">
            <el-form-item label="名称：">
              <span>{{ acgs[index].acgName }}</span>
            </el-form-item>
            <el-form-item label="评分：">
              <el-rate v-model="userRemark.userRate" allow-half></el-rate>
            </el-form-item>
            <el-form-item label="评论：">
              <el-input type="textarea" v-model="userRemark.userReview" :rows="6"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1" >
            <el-icon @click="deleteCard(index)" class="deleteCard"><Delete/></el-icon>
          </el-col>
        </el-row>
      </el-card>

      <div class="addCard" @click="addCard"><el-icon><Plus/></el-icon></div>

      <!--  提交取消  -->
      <el-form-item>
        <el-button type="primary" @click="submitForm(listFormRef)">提交</el-button>
        <el-button @click="reset(listFormRef)">取消</el-button>
      </el-form-item>
    </el-form>


    <!--  对话框，选择acg  -->
    <el-dialog v-model="displaySelectAcg" width="800">
      <el-form inline>
        <el-form-item>
          <el-input placeholder="名称模糊查询"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button>查询</el-button>
        </el-form-item>
      </el-form>
      <acg-card class="acgCard" :acg="acg" v-for="acg in acgList" @click="selectAcg(acg)"></acg-card>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import type { ElInput, FormInstance, FormRules } from 'element-plus'
import type { List, UserRemark } from '@/entity/list'
import { reactive, ref } from 'vue'
import { useCategoryStore } from '@/stores/category'
import AcgCard from '@/components/acg/source/Acg-Card.vue'
import type { AcgBrief } from '@/entity/acg'
import test from '@/assets/images/test.jpeg'
import { Delete, Plus } from '@element-plus/icons-vue'

const displaySelectAcg = ref(false)
const currentIndex = ref(0)

// 打开对话框选择acg
const openDialog = (index: number) => {
  displaySelectAcg.value = true
  currentIndex.value = index
}
// 获取全部acg
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
// 选择一个acg
const selectAcg = (acg: AcgBrief) => {
  userRemarks.value[currentIndex.value].acgId = acg.acgId
  acgs.value[currentIndex.value] = acg
  displaySelectAcg.value = false
}
// 添加新的acgRemark
const addCard = () => {
  userRemarks.value.push({
    acgId: '',       // acgId
    userRate: 0,     // 用户评分
    userReview: '',   // 用户评语
  })
  acgs.value.push({
    acgId: '',   // id
    acgName: '', // 名称
    acgCover: '', // 封面
    acgState: 0, // 状态 '0': 连载中，'1': 已完结
    acgChapter: 0, // 章节
  })
}
// 删除acgRemark
const deleteCard = (index:number) => {
  userRemarks.value.splice(index, 1)
  acgs.value.splice(index, 1)
}

// 表单
const listFormRef = ref<FormInstance>()
// 表单数据
const listForm = reactive<List>({
  listTitle: '',   // 书单标题
  categoryId: useCategoryStore().category.categoryId,
  listStatus: 1 , // 书单发布状态0未发布，1发布

  userId: '',           // 用户id
  userRemarks: [] // 图书评论数组
})
const userRemarks = ref<UserRemark[]>([{
  acgId: '',       // acgId
  userRate: 0,     // 用户评分
  userReview: '',   // 用户评语
}])
const acgs = ref<AcgBrief[]>([{
  acgId: '',   // id
  acgName: '', // 名称
  acgCover: '', // 封面
  acgState: 0, // 状态 '0': 连载中，'1': 已完结
  acgChapter: 0, // 章节
}])
// 校验规则
const listRules = reactive<FormRules<List>>({})
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      // 添加

    } else {
      console.log('error submit!', fields)
    }
  })
}
const reset = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

</script>

<style scoped>
.acgCard {
  cursor: pointer;
}

.el-image {
  width: 12rem;
  height: 16rem;
}
.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
  cursor: pointer;
}
.image-slot .el-icon {
  font-size: 30px;
}

.addCard {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
  cursor: pointer;
  margin: 1.2rem 0;
}
.deleteCard {
  cursor: pointer;
}
</style>
