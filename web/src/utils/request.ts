import axios from "axios";
import { ElMessage } from "element-plus";

export const postRequest = (
  url: string,
  data: any,
  callback: { (res: { id: number; status: string; }): void; (arg0: any): void; }
) => {
  axios
    .post(url, data, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((res) => {
      console.log(res);
      if (res.status == 200 && res.data.status == "success") {
        ElMessage({
          message: "添加成功",
          type: "success",
        });
      } else {
        ElMessage({
          message: "添加失败",
          type: "error",
        });
      }

      callback(res.data);
    })
    .catch((error) => {
      console.error("post请求出错", error);
      ElMessage({
        message: "添加失败",
        type: "error",
      });
    });
};


export const deleteRequest = (
  url: string,
  data: any,
  callback: { (res: string): void; (arg0: any): void; }
) => {
  axios
    .post(url, data, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((res) => {
      console.log(res);
      if (res.status == 200 && res.data == "success") {
        ElMessage({
          message: "删除成功",
          type: "success",
        });
      } else {
        ElMessage({
          message: "删除失败",
          type: "error",
        });
      }

      callback(res.data);
    })
    .catch((error) => {
      console.error("post请求出错", error);
      ElMessage({
        message: "删除失败",
        type: "error",
      });
    });
};


export const getRequest = (
  url: string,
  callback: { (res: string): void; (arg0: any): void; }
) => {
  axios
    .post(url, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((res) => {
      console.log(res);
      if (res.status == 200) {
        ElMessage({
          message: "请求数据成功",
          type: "success",
        });
      } else {
        ElMessage({
          message: "获取数据失败",
          type: "error",
        });
      }

      callback(res.data);
    })
    .catch((error) => {
      console.error("post请求出错", error);
      ElMessage({
        message: "获取数据失败",
        type: "error",
      });
    });
};



//登陆页面
export const loginRequest = (
  url: string,
  data: any,
  callback: { (res: { status: boolean; message: string; }): void; (res: { status: boolean; message: string; }): void; (arg0: any): void; }
) => {
  console.log(data)
  axios
    .post(url, data, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((res) => {
      console.log(res);
      if (res.status == 200 && res.data.status) {
        ElMessage({
          message: res.data.message,
          type: "success",
        });
      } else {
        ElMessage({
          message: res.data.message,
          type: "error",
        });
      }

      callback(res.data);
    })
    .catch((error) => {
      console.error("post请求出错", error);
      ElMessage({
        message: "请求失败",
        type: "error",
      });
    });
};
