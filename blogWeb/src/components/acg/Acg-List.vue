<template>
  <el-form
    :model="acgForm"
    label-width="auto"
    label-position="left"
    size="default"
  >
    <el-form-item label="类型:" v-if="types">
      <el-radio-group v-model="acgForm.acgType" >
        <el-radio label="全部" value="" class="radio"/>
        <el-radio v-for="type in types" :key="type" :label="type" :value="type" class="radio"/>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="风格:">
      <el-radio-group v-model="acgForm.acgGenre" >
        <el-radio label="全部" value="" class="radio"/>
        <el-radio v-for="genre in genres" :key="genre" :label="genre" :value="genre" class="radio"/>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="标签:">
      <el-radio-group v-model="acgForm.acgTag" >
        <el-radio label="全部" value="" class="radio"/>
        <el-radio v-for="tag in tags" :key="tag" :label="tag" :value="tag" class="radio" />
      </el-radio-group>
    </el-form-item>

    <el-form-item label="地区:" v-if="regionShow">
      <el-radio-group v-model="acgForm.acgRegion" >
        <el-radio label="全部" value="" class="radio"/>
        <el-radio v-for="region in acgRegions" :key="region" :label="region" :value="region" class="radio"/>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="状态:" v-if="stateShow">
      <el-radio-group v-model="acgForm.acgState">
        <el-radio label="全部" value="" class="radio"/>
        <el-radio v-for="state in acgStates" :key="state" :label="state.label" :value="state.value" class="radio"/>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="年份:" v-if="yearShow">
      <el-radio-group v-model="acgForm.acgYear">
        <el-radio label="全部" value="" class="radio"/>
        <el-radio v-for="region in acgYears" :key="region" :label="region" :value="region" class="radio"/>
      </el-radio-group>
    </el-form-item>

  </el-form>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useCategoryStore } from '@/stores/category'
import { acgGenre, acgRegions, acgStates, acgTags, acgTypes, acgYears, novelGenre, novelTags } from '@/entity/acg'

const {category} = storeToRefs(useCategoryStore())

const acgForm = reactive({
  acgType: '',
  acgGenre: '',
  acgRegion: '',
  acgState: '',
  acgYear: '',
  acgTag: '',
})

const clearForm = () => {
  acgForm.acgType = ''
  acgForm.acgGenre = ''
  acgForm.acgRegion = ''
  acgForm.acgState = ''
  acgForm.acgYear = ''
}
// 类型，风格，标签
const types = ref()
const genres = ref()
const tags = ref()
// 年份， 状态, 地区是否显示
const yearShow = ref(true)
const stateShow = ref(true)
const regionShow = ref(true)

watch(() => category.value, (newValue) => {
  types.value = acgTypes(newValue!.label)
  genres.value = acgGenre(newValue!.label)
  tags.value = acgTags(newValue!.label)
  if (newValue!.label == '小说' || newValue!.label == '游戏') yearShow.value = false
  else yearShow.value = true
  if (newValue!.label == '游戏') stateShow.value = false
  else stateShow.value = true
  /*if (newValue!.label == '小说') regionShow.value = false
  else regionShow.value = true*/
  clearForm()
})
watch(() => acgForm.acgType, (newValue) => {
  if (category.value!.label === '小说') {
    genres.value = novelGenre(newValue)
    tags.value = novelTags(newValue)
  }
})

</script>

<style scoped>
/deep/ .el-radio__input {
 display: none;
}
/deep/ .el-checkbox__input {
  display: none;
}
</style>
