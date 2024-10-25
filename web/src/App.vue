<script setup lang="ts">
import { routes } from "./router";
import { useStore } from "./store";
import { useRouter } from "vue-router";
import { SwitchButton } from "@element-plus/icons-vue";

const pinia = useStore();
const router = useRouter();

router.beforeEach((to, _from, next) => {
  //判断是否登陆
  if (!pinia.login_token) {
    pinia.TITLE_SHOW = false;
    if (to.path == "/login") {
      next();
    } else {
      next({ path: "/login" });
    }
  } else {
    pinia.TITLE_SHOW = true;
    next();
  }
});

function exitLogin() {
  pinia.login_token = "";
  pinia.TITLE_SHOW = false;
  router.push("/login");
}
</script>

<template>
  <div class="title" v-show="pinia.TITLE_SHOW">
    <div style="display: flex">
      <div class="css-dh"></div>
      <div
        style="padding: 10px 10px"
        v-for="(item, index) in routes"
        :key="index"
        v-show="item.meta.isShow"
      >
        <router-link :to="item.path"></router-link>
      </div>
    </div>

    <div style="display: flex;">
      <div style="line-height: 50px;">{{ pinia.login_token }}</div>
      <button class="Btn" @click="exitLogin">
        <el-icon class="sign" color="#fff">
          <SwitchButton />
        </el-icon>
        <div class="text">退出登陆</div>
      </button>
    </div>
  </div>

  <div class="container">
    <router-view v-slot="{ Component }">
      <keep-alive>
        <component :is="Component" />
      </keep-alive>
    </router-view>
  </div>
</template>

<style lang="scss">
html,
body,
#app {
  margin: 0px;
  padding: 0px;
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  color: #000;
}

.title {
  display: flex;
  height: 50px;
  justify-content: space-between;
  background-color: white;
}

.css-dh {
  --width: 100px;
  --timing: 2s;
  border: 0;
  width: var(--width);
  padding-block: 1em;
  color: #fff;
  font-weight: bold;
  font-size: 1em;
  background: rgb(64, 192, 87);
  transition: all 0.2s;
  border-radius: 3px;
  cursor: pointer;
  animation: myAnimation 5s infinite;
  animation-play-state: running;
}
.css-dh {
  background-image: linear-gradient(
    to right,
    rgb(250, 82, 82),
    rgb(250, 82, 82) 16.65%,
    rgb(190, 75, 219) 16.65%,
    rgb(190, 75, 219) 33.3%,
    rgb(76, 110, 245) 33.3%,
    rgb(76, 110, 245) 49.95%,
    rgb(64, 192, 87) 49.95%,
    rgb(64, 192, 87) 66.6%,
    rgb(250, 176, 5) 66.6%,
    rgb(250, 176, 5) 83.25%,
    rgb(253, 126, 20) 83.25%,
    rgb(253, 126, 20) 100%,
    rgb(250, 82, 82) 100%
  );
  animation: var(--timing) linear dance6123 infinite;
  transform: scale(1.1) translateY(-1px);
}

@keyframes dance6123 {
  to {
    background-position: var(--width);
  }
}

.Btn {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 40px;
  height: 40px;
  margin: 5px;
  border: none;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition-duration: 0.3s;
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.199);
  background-color: rgb(255, 65, 65);
}

.sign {
  width: 100%;
  font-weight: bold;
  transition-duration: 0.3s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.text {
  position: absolute;
  right: 0%;
  width: 0%;
  opacity: 0;
  color: white;
  font-size: 1.2em;
  font-weight: 600;
  transition-duration: 0.3s;
}

.Btn:hover {
  width: 125px;
  border-radius: 40px;
  transition-duration: 0.3s;
}

.Btn:hover .sign {
  width: 20%;
  transition-duration: 0.3s;
  padding-left: 10px;
}

.Btn:hover .text {
  opacity: 1;
  width: 70%;
  transition-duration: 0.3s;
  padding-right: 10px;
}

.Btn:active {
  transform: translate(2px, 2px);
}

.container {
  height: 100%;
  width: 100%;
  background: linear-gradient(to bottom, #d4f2f5, #fff);
}

// 滚动条样式
::webkit-scrollbar-thumb {
  border-radius: 10px;
  background: #4b5563;
  margin: 2px;
}

::-webkit-scrollbar {
  width: 11px;
  height: 11px;
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-color: #4b5563;
  border: 2px solid transparent;
  background-clip: padding-box;
}

::-webkit-scrollbar {
  width: 2px;
  height: 4px;
}

::-webkit-scrollbar:hover {
  width: 2px;
  height: 7px;
}
</style>
