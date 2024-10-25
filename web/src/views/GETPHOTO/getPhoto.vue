<script lang="ts" setup>
import { ref } from "vue";
import savePhoto from "./components/savePhoto.vue";
import showPhoto from './components/showPhoto.vue';
import { Search } from "@element-plus/icons-vue";
import { allData, searchData } from "./utils";

const searchs = ref<string>("");
const url = ref<string>("");
const id = ref<number>(0);

//搜索
function clickSearch() {
  console.log(allData.value);

  searchData.value = [];
  allData.value.forEach((data) => {
    if (data.searchText.includes(searchs.value)) {
      searchData.value.push(data);
    }
  });

  console.log(searchData.value.length);
}

//添加图片成功后传到显示组件
function getPath(urls: { url: string; id: number }) {
  url.value = urls.url;
  id.value = urls.id;
}
</script>

<template>
  <div class="container-search">
    <!-- 搜索框 -->
    <div class="searchContainer">
      <input
        placeholder="Enter a name for the image"
        class="input"
        name="email"
        type="text"
        required
        v-model="searchs"
      />
      <button class="button" @click="clickSearch">
        <el-icon><Search /></el-icon>
      </button>
    </div>

    <!-- 添加图片-->
    <div>
      <savePhoto @path="getPath"></savePhoto>
    </div>

    <!-- 显示图片 -->
    <showPhoto :url="url" :id="id"></showPhoto>
  </div>
</template>

<style scoped lang="scss">
@import url("./css/getPhoto.scss");
</style>
