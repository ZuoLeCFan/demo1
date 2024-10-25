<script setup lang="ts">
import { ref } from "vue";
import { Delete, Download, Plus, ZoomIn } from "@element-plus/icons-vue";
import type { UploadFile, UploadRawFile } from "element-plus";
import { ReturnDataType, searchData } from "../utils";
import { postRequest, API_URL_POST } from "../../../utils";

const dialogImageUrl = ref("");
const dialogVisible = ref(false);
const disabled = ref(false);
const imageUrl = ref("params.png");

//子组件往父组件传值
const emits = defineEmits();

const handleRemove = (file: UploadFile) => {
  console.log(file);
};

const handlePictureCardPreview = (file: UploadFile) => {
  dialogImageUrl.value = file.url!;
  dialogVisible.value = true;
};

const handleDownload = (file: UploadFile) => {
  console.log(file);
};

const handleSuccess = (response: ReturnDataType) => {
  console.log(response);

  imageUrl.value = response.fileName;
  const data = {
    name: response.fileName,
    path: response.path,
    url: response.url,
    searchText: response.fileName
  };

  postRequest(
    `api/${API_URL_POST.SET_PHOTO}`,
    data,
    (res: { id: number; status: string }) => {
      if (res.status == "success") {
        //往父组件传值
        const msg = {
          url: imageUrl.value,
          id: res.id,
        };
        emits("path", msg);
      }
    }
  );
};

const handleBeforeUpload = (rawFile: UploadRawFile) => {
  console.log(rawFile);
};
</script>

<template>
  <div class="savePhotoContainer">
    <div class="plusContainer">
      <el-upload
        action="/api/SAVE/PHOTO"
        :show-file-list="false"
        :on-success="handleSuccess"
        :before-upload="handleBeforeUpload"
        :auto-upload="true"
      >
        <el-icon :size="30"><Plus /></el-icon>

        <template #file="{ file }">
          <div>
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url"
              alt=""
            />
            <span class="el-upload-list__item-actions">
              <span
                class="el-upload-list__item-preview"
                @click="handlePictureCardPreview(file)"
              >
                <el-icon><zoom-in /></el-icon>
              </span>
              <span
                v-if="!disabled"
                class="el-upload-list__item-delete"
                @click="handleDownload(file)"
              >
                <el-icon><Download /></el-icon>
              </span>
              <span
                v-if="!disabled"
                class="el-upload-list__item-delete"
                @click="handleRemove(file)"
              >
                <el-icon><Delete /></el-icon>
              </span>
            </span>
          </div>
        </template>
      </el-upload>

      <el-dialog v-model="dialogVisible">
        <img w-full :src="dialogImageUrl" alt="Preview Image" />
      </el-dialog>
    </div>
    <div class="show-photo">
      <img v-for="(img,index) in searchData" :key="index" class="images" :src="`photo/${img.name}`" alt="" />
    </div>
  </div>
</template>

<style scoped lang="scss">
@import url("../css/savePhoto.scss");
</style>
