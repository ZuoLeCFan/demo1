import { createApp } from "vue";
import "./style.css";
import router from "./router";
import pinia from "./store";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import zhCn from "element-plus/es/locale/lang/zh-cn";
import App from "./App.vue";

const app = createApp(App);
app.use(ElementPlus, {
  locale: zhCn,
});
app.use(router).use(pinia).mount("#app");
