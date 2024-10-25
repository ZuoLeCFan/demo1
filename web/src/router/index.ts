import { createRouter, createWebHashHistory } from "vue-router";
//@ts-ignore
import login from '../views/LOGIN/login.vue'

export const routes = [
  
  {
    path: "/login",
    name: "login",
    component: login,
    meta: {
      KeepAlive: true,
      isShow: false
    },
  },
  {
    path: "/",
    name: "GET-PHOTO",
    //@ts-ignore
    component: () => import("../views/GETPHOTO/getPhoto.vue"),
    meta: {
      KeepAlive: true,
      isShow: true
    },
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes: routes,
});

export default router;
