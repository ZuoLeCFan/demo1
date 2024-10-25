import { defineStore } from "pinia";

export const useStore = defineStore("config", {
  // other options...
  state: () => ({
    /**
     * 登陆后赋值
     * 退出清空
     * 登陆一段时间后清空，需重新登陆
     */
    login_token: '',
    TITLE_SHOW: true
  }),
  getters: {},
});
