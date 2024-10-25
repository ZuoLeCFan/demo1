<script setup lang="ts">
// 创建四个数组，分别遍历
import { ElMessage, ElMessageBox } from "element-plus";
import { reactive, watch, onMounted } from "vue";
import { ShowPageType, allData } from "../utils";
import {
  API_URL_POST,
  deleteRequest,
  getRequest,
  loginRequest,
} from "../../../utils";
import { Delete, Edit } from "@element-plus/icons-vue";

const photo = reactive({
  reviseStatus: false as boolean,
  state: 1 as number, //用于判断接下来应该往哪个数组中添加数据了
  data1: [] as ShowPageType[],
  data2: [] as ShowPageType[],
  data3: [] as ShowPageType[],
  data4: [] as ShowPageType[],
  data5: [] as ShowPageType[],
  searchText: "" as string,
  photoIndex: 0 as number,
  photoArr: [] as ShowPageType[],
});

const props = defineProps<{ id: number; url: string }>();

onMounted(() => {
  get_data();
});

//监听添加
watch(
  () => props.url,
  (newVue) => {
    console.log(newVue);
    //添加并显示图片
    const id = props.id;
    addData(newVue, id, newVue);

    get_data()
  }
);

function get_data() {
  photo.data1 = []
  photo.data2 = []
  photo.data3 = []
  photo.data4 = []
  photo.data5 = []

  getRequest(`api/${API_URL_POST.GET_PHOTO}`, (res) => {
    const { list } = res;
    allData.value = list

    list.forEach(
      (item: {
        id: number;
        path: string;
        url: string;
        name: string;
        searchText: string;
      }) => {
        addData(item.name, item.id, item.searchText);
      }
    );
  });
}

//往数组中添加数据
function addData(name: string, id: number, text: string) {
  const msg = {
    id: id,
    url: `photo/${name}`,
    status: photo.state,
    searchText: text,
  };
  switch (photo.state) {
    case 1:
      photo.data1.push(msg);
      photo.state += 1;
      break;
    case 2:
      photo.data2.push(msg);
      photo.state += 1;
      break;
    case 3:
      photo.data3.push(msg);
      photo.state += 1;
      break;
    case 4:
      photo.data4.push(msg);
      photo.state += 1;
      break;
    case 5:
      photo.data5.push(msg);
      photo.state = 1;
      break;
  }
}

//双击删除图片
function image_delete1(index: number) {
  open(index, photo.data1);
}
function image_delete2(index: number) {
  open(index, photo.data2);
}
function image_delete3(index: number) {
  open(index, photo.data3);
}
function image_delete4(index: number) {
  open(index, photo.data4);
}
function image_delete5(index: number) {
  open(index, photo.data5);
}

const open = (index: number, arr: ShowPageType[]) => {
  photo.reviseStatus = true;
  photo.photoIndex = index;
  photo.photoArr = arr;
  photo.searchText = arr[index].searchText;
};

//
function editSearch() {
  if (photo.searchText == "") {
    ElMessage({
      message: "输入搜索参数",
      type: "warning",
    });
    return;
  }

  //请求
  const data = {
    id: photo.photoArr[photo.photoIndex].id,
    searchText: photo.searchText,
  };
  loginRequest(`api/${API_URL_POST.REVISE}`, data, (res) => {
    console.log(res);
    get_data()
  });
  photo.reviseStatus = false;
}

//删除图片
function deletePhoto() {
  //请求
  ElMessageBox.confirm("是否删除图片?", "Warning", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      deletePhotos(photo.photoIndex, photo.photoArr);
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消删除",
      });
    });
  photo.reviseStatus = false;
}

//确定删除
function deletePhotos(index: number, arr: ShowPageType[]) {
  //逻辑处理
  const id = {
    id: arr[index].id,
  };
  deleteRequest(`api/${API_URL_POST.DELETE_PHOTO}`, id, (res: string) => {
    console.log(res);
    get_data()
  });
}
</script>

<template>
  <!-- 修改 -->
  <el-affix :offset="500">
    <div v-show="photo.reviseStatus" class="revise">
      <div class="input">
        <el-input type="text" v-model="photo.searchText" style="width: 150px" />
      </div>
      <div style="margin-left: 70px">
        <el-button
          type="danger"
          :icon="Edit"
          style="width: 20px"
          @click="editSearch"
        ></el-button>
        <el-button
          type="warning"
          :icon="Delete"
          style="width: 20px"
          @click="deletePhoto"
        ></el-button>
      </div>
    </div>
  </el-affix>

  <div class="showContainer">
    <div class="photo1">
      <img
        v-for="(item, index) in photo.data1"
        :key="index"
        :src="item.url"
        alt=""
        class="image"
        @dblclick="image_delete1(index)"
      />
    </div>
    <div class="photo1">
      <img
        v-for="(item, index) in photo.data2"
        :key="index"
        :src="item.url"
        alt=""
        class="image"
        @click="image_delete2(index)"
      />
    </div>
    <div class="photo1">
      <img
        v-for="(item, index) in photo.data3"
        :key="index"
        :src="item.url"
        alt=""
        class="image"
        @click="image_delete3(index)"
      />
    </div>
    <div class="photo1">
      <img
        v-for="(item, index) in photo.data4"
        :key="index"
        :src="item.url"
        alt=""
        class="image"
        @click="image_delete4(index)"
      />
    </div>
    <div class="photo1">
      <img
        v-for="(item, index) in photo.data5"
        :key="index"
        :src="item.url"
        alt=""
        class="image"
        @click="image_delete5(index)"
      />
    </div>
  </div>
</template>

<style scoped lang="scss">
.showContainer {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  margin: 10px 200px;
  grid-gap: 20px;
  min-height: 48vh;
  overflow: auto;

  .photo1 {
    display: flex;
    flex-direction: column;
    justify-content: start;
    row-gap: 20px;

    // max-width: 150px;
  }
}

.image {
  border-radius: 10px;
  width: 100%;
}

.revise {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>
