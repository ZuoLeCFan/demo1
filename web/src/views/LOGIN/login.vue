<script setup lang="ts">
import { ElMessage } from "element-plus";
import { useStore } from "../../store";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import { loginRequest, API_URL_POST } from "../../utils";

const router = useRouter();

const token = useStore();
const dynamicValidateForm = reactive<{
  domains: string;
  email: string;
}>({
  domains: "",
  email: "",
});

//登陆
const submitForm = () => {
  console.log(dynamicValidateForm)
  if (dynamicValidateForm.email == "" || dynamicValidateForm.domains == "") {
    ElMessage({
      message: "先输入账号与密码",
      type: "warning",
    });

    return;
  }

  console.log(dynamicValidateForm.email)
  const msg = {
    name: dynamicValidateForm.email,
    password: dynamicValidateForm.domains,
  };

  loginRequest(
    `api/${API_URL_POST.LOGIN}`,
    msg,
    (res: { status: boolean; message: string }) => {
      if (res.status) {
        token.login_token = dynamicValidateForm.email;
        token.TITLE_SHOW = true;
        router.push("/");
      }
    }
  );
};

// 注册
const enroll = () => {
  //先注册，再登陆
  if (dynamicValidateForm.email == "" || dynamicValidateForm.domains == "") {
    ElMessage({
      message: "先输入账号与密码",
      type: "warning",
    });

    return;
  }

  const msg = {
    name: dynamicValidateForm.email,
    password: dynamicValidateForm.domains,
  };

  loginRequest(
    `api/${API_URL_POST.ENROLL}`,
    msg,
    (res: { status: boolean; message: string }) => {
      if (res.status) {
        ElMessage({
          message: "请再次登陆",
          type: "success",
        });
      }
    }
  );
};
</script>

<template>
  <div class="logins">
    <div class="login">
      <div class="imgs">
        <img class="image" src="../../assets/icon2.png" alt="" />
      </div>
      <div class="login-text">
        <div style="font-size: 25px; margin: auto">登录</div>
        <div class="login-input">
          <div class="inputs">
            <input
              type="text"
              required
              v-model="dynamicValidateForm.email"
              placeholder="邮箱"
            />
            <input
              type="text"
              required
              v-model="dynamicValidateForm.domains"
              placeholder="密码"
            />
          </div>

          <div class="forgetPassword"></div>
          <el-button
            style="width: 300px"
            round
            size="large"
            type="success"
            @click="submitForm"
            >登录</el-button
          >
          <div class="enrollClass" @click="enroll">没有账号？立即注册</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.logins {
  height: 100%;
  width: 100%;
  display: flex;
  align-items: flex-end;
  justify-content: center;

  .login {
    height: 90%;
    width: 80%;
    display: grid;
    grid-template-columns: 1fr 1fr;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    background-color: #fff;

    .imgs {
      width: 200px;
      height: 200px;
      display: flex;
      align-items: center;
      justify-content: center;
      margin: auto;

      .image {
        width: 400px;
      }
    }

    .login-text {
      height: 100%;
      width: 100%;
      display: grid;
      grid-template-rows: 0.8fr 1fr;
      margin: auto;

      .login-input {
        display: flex;
        flex-direction: column;
        row-gap: 10px;
        align-items: center;
        justify-content: right;

        .inputs {
          display: flex;
          flex-direction: column;
          gap: 20px;
          align-items: center;

          input {
            height: 40px;
            width: 300px;
            border-radius: 18px;
            background-color: #eee;
          }
        }

        .forgetPassword {
          display: flex;
          justify-content: right;
          font-size: 10px;
        }

        .enrollClass {
          display: flex;
          justify-content: center;
          font-size: 12px;
          cursor: pointer;
        }
      }
    }
  }
}
</style>
